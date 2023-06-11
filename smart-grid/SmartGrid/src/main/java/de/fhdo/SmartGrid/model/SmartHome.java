package de.fhdo.SmartGrid.model;

import jakarta.persistence.*;

@Entity
public class SmartHome {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private double currentPowerConsumption;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCurrentPowerConsumption() {
        return currentPowerConsumption;
    }

    public void setCurrentPowerConsumption(double currentPowerConsumption) {
        this.currentPowerConsumption = currentPowerConsumption;
    }
}
