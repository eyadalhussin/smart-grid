package de.fhdo.SmartGrid.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class EnergyConsumerService {

    private double currentEnergyConsumption;

    public void setCurrentEnergyConsumption(double currentEnergyConsumption) {
        this.currentEnergyConsumption = currentEnergyConsumption;
    }

    public double getCurrentEnergyConsumption() {
        return currentEnergyConsumption;
    }

    public double calculateCurrentEnergyConsumption() {
        return 0;
    }
}
