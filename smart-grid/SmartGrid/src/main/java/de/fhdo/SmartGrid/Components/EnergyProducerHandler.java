package de.fhdo.SmartGrid.Components;

import de.fhdo.SmartGrid.model.EnergyProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EnergyProducerHandler implements TimeObserver{

    private double currentEnergyGeneration;
    private List<EnergyProducer> energyProducers = new ArrayList<>();

    @PostConstruct
    public void init() {
        //Get database entries for energy producers

        TimeSimulation.registerObserver(this);
    }

    public void setCurrentEnergyGeneration(double currentEnergyGeneration) {
        this.currentEnergyGeneration = currentEnergyGeneration;
    }

    @Override
    public void timeUpdated() {
        currentEnergyGeneration = 0;
        for (EnergyProducer energyProducer : energyProducers) {
            energyProducer.calculateCurrentPowerGeneration();
            currentEnergyGeneration += energyProducer.getCurrentPowerGeneration();
        }
        System.out.printf("Current Energy generation: %.2f kWh%n", currentEnergyGeneration);
    }
}

