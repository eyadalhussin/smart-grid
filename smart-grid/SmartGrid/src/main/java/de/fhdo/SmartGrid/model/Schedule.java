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
    private double powerConsumption;
    private ScheduleState classState;
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
        this.powerConsumption = powerConsumption;
        this.classState = classState;
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

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public ScheduleState getClassState() {
        return classState;
    }

    public void setClassState(ScheduleState classState) {
        this.classState = classState;
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
