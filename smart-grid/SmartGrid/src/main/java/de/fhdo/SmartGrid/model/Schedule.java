package de.fhdo.SmartGrid.model;

import de.fhdo.SmartGrid.enums.ScheduleState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.Duration;
import java.time.Instant;

@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private long id;

    private long deviceID;
    @OneToOne
    private SmartHome smartHome;
    private double powerConsumptionKWH;
    private ScheduleState scheduleState;
    private Duration duration;
    private Instant startTime;
    private Instant endTime;
    private double desiredPrice;
    private boolean canExceedPrice;
    private boolean shouldStopDevice;

    public Schedule(long deviceID, SmartHome smartHome,
                    double powerConsumption,
                    ScheduleState classState,
                    Duration duration,
                    Instant startTime,
                    Instant endTime,
                    double desiredPrice,
                    boolean canExceedPrice,
                    boolean shouldStopDevice) {
        this.deviceID = deviceID;
        this.smartHome = smartHome;
        this.powerConsumptionKWH = powerConsumption;
        this.scheduleState = classState;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = endTime;
        this.desiredPrice = desiredPrice;
        this.canExceedPrice = canExceedPrice;
        this.shouldStopDevice = shouldStopDevice;
    }

    public Schedule() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(long deviceID) {
        this.deviceID = deviceID;
    }

    public SmartHome getSmartHome() {
        return smartHome;
    }

    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public double getPowerConsumptionKWH() {
        return powerConsumptionKWH;
    }

    public void setPowerConsumptionKWH(double powerConsumptionKWH) {
        this.powerConsumptionKWH = powerConsumptionKWH;
    }

    public ScheduleState getScheduleState() {
        return scheduleState;
    }

    public void setScheduleState(ScheduleState scheduleState) {
        this.scheduleState = scheduleState;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public double getDesiredPrice() {
        return desiredPrice;
    }

    public void setDesiredPrice(double desiredPrice) {
        this.desiredPrice = desiredPrice;
    }

    public boolean isCanExceedPrice() {
        return canExceedPrice;
    }

    public void setCanExceedPrice(boolean canExceedPrice) {
        this.canExceedPrice = canExceedPrice;
    }

    public boolean isShouldStopDevice() {
        return shouldStopDevice;
    }

    public void setShouldStopDevice(boolean shouldStopDevice) {
        this.shouldStopDevice = shouldStopDevice;
    }
}
