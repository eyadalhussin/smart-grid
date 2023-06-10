package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Components.TimeObserver;
import de.fhdo.SmartGrid.Components.TimeSimulationComponent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class EnergyService implements TimeObserver {
    private final EnergyProducerService energyProducerService;
    private final EnergyConsumerService energyConsumerService;
    private final EnergyStorageService energyStorageService;
    private final TimeSimulationComponent timeSimulationComponent;

    @Autowired
    public EnergyService(TimeSimulationComponent timeSimulationComponent, EnergyProducerService energyProducerService, EnergyConsumerService energyConsumerService, EnergyStorageService energyStorageService) {
        this.energyProducerService = energyProducerService;
        this.energyConsumerService = energyConsumerService;
        this.energyStorageService = energyStorageService;
        this.timeSimulationComponent = timeSimulationComponent;
    }

    @Override
    public void timeUpdated() {
        double currentEnergyGeneration = energyProducerService.calculateCurrentEnergyGeneration();
        double currentEnergyConsumption = energyConsumerService.calculateCurrentEnergyConsumption();

        energyStorageService.distributeEnergy(currentEnergyGeneration - currentEnergyConsumption);
    }

    @PostConstruct
    public void init() {
        timeSimulationComponent.registerObserver(this);
    }
}
