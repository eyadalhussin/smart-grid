package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Observer.TimeObserver;
import de.fhdo.SmartGrid.enums.EmergencyLevel;
import de.fhdo.SmartGrid.enums.ScheduleState;
import de.fhdo.SmartGrid.model.Schedule;
import de.fhdo.SmartGrid.repository.ScheduleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService implements TimeObserver {

    private final TimeSimulationService timeSimulationService;
    private final SmartHomeService smartHomeService;
    private final EmergencyService emergencyService;
    private final TarifService tarifService;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(TimeSimulationService timeSimulationService, ScheduleRepository scheduleRepository, SmartHomeService smartHomeService, EmergencyService emergencyService, TarifService tarifService) {
        this.timeSimulationService = timeSimulationService;
        this.scheduleRepository = scheduleRepository;
        this.smartHomeService = smartHomeService;
        this.emergencyService = emergencyService;
        this.tarifService = tarifService;
    }

    @PostConstruct
    public void init() {
        timeSimulationService.registerObserver(this);
    }


    @Override
    public void timeUpdated() {
        //ScheduleService is disabled if an emergency is active, running schedules will be stopped by the emergency service
        if(emergencyService.getEmergencyLevel() != EmergencyLevel.NONE) return;

        updateSchedules();
    }

    private void updateSchedules() {
        updateWaitingSchedules();
        updateRunningSchedules();
    }

    private void updateWaitingSchedules() {
        updateAwaitingStartSchedules();
        updateWaitingForPriceSchedules();
    }

    private void updateAwaitingStartSchedules() {
        List<Schedule> schedules = scheduleRepository.findScheduleByScheduleState(ScheduleState.WAITING_FOR_START);
        schedules.forEach(schedule -> {
            if(schedule.getStartTime().isBefore(timeSimulationService.getCurrentTime())) {
                schedule.setScheduleState(ScheduleState.WAITING_FOR_PRICE);
                scheduleRepository.save(schedule);
            }
        });
    }

    private void updateWaitingForPriceSchedules() {
        List<Schedule> schedules = scheduleRepository.findScheduleByScheduleState(ScheduleState.WAITING_FOR_PRICE);
        schedules.forEach(schedule -> {
            if(schedule.getDesiredPrice() < tarifService.getCurrentTarif().getPrice() ||
            schedule.isCanExceedPrice()) {
                schedule.setScheduleState(ScheduleState.RUNNING);
                schedule.setEndTime(timeSimulationService.getCurrentTime().plus(schedule.getDuration()));
                scheduleRepository.save(schedule);
                smartHomeService.startDevice(schedule.getDeviceID());
            }

            if(schedule.getEndTime().isAfter(timeSimulationService.getCurrentTime())) {
                stopSchedulePrice(schedule);
                scheduleRepository.save(schedule);
            }

        });
    }

    private void updateRunningSchedules() {
        List<Schedule> schedules = scheduleRepository.findScheduleByScheduleState(ScheduleState.RUNNING);
        schedules.forEach(schedule -> {
            if(schedule.getEndTime().isAfter(timeSimulationService.getCurrentTime())) {
                schedule.setScheduleState(ScheduleState.FINISHED);
                scheduleRepository.save(schedule);
                stopDevice(schedule);
            }
        });
    }

    public void stopScheduleUser(Schedule schedule) {
        //A schedule can only be stopped if the device is not running
        //TODO: Throw an exception?
        if(schedule.getScheduleState() == ScheduleState.RUNNING)
            return;

        schedule.setScheduleState(ScheduleState.CANCELLED_BY_USER);
        scheduleRepository.save(schedule);
    }

    public void stopScheduleEmergency(Schedule schedule) {
        if(schedule.getScheduleState() == ScheduleState.RUNNING)
            forceStopDevice(schedule.getDeviceID());

        schedule.setScheduleState(ScheduleState.CANCELLED_BY_EMERGENCY);
        scheduleRepository.save(schedule);
    }

    public void stopSchedulePrice(Schedule schedule) {
        //A schedule can only be stopped if the device is not running
        //TODO: Throw an exception?
        if(schedule.getScheduleState() == ScheduleState.RUNNING)
            return;

        schedule.setScheduleState(ScheduleState.CANCELLED_BY_PRICE);
        scheduleRepository.save(schedule);
    }

    private void stopDevice(Schedule schedule) {
        if(schedule.isShouldStopDevice()) smartHomeService.stopDevice(schedule.getDeviceID());
    }

    public void forceStopDevice(long deviceID) {
        smartHomeService.stopDevice(deviceID);
    }

    public void save(Schedule schedule) {
        schedule.setScheduleState(ScheduleState.WAITING_FOR_START);
        scheduleRepository.save(schedule);
    }


}
