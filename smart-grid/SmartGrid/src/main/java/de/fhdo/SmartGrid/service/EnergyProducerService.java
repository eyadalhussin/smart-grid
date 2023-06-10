package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Components.TimeObserver;
import de.fhdo.SmartGrid.Components.TimeSimulationComponent;
import de.fhdo.SmartGrid.model.EnergyProducer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class EnergyProducerService implements TimeObserver {

    private final TimeSimulationComponent timeSimulationComponent;
    private final WeatherService weatherService;
    private double currentEnergyGeneration;
    private List<EnergyProducer> energyProducers = new ArrayList<>();

    @Autowired
    public EnergyProducerService(TimeSimulationComponent timeSimulationComponent, WeatherService weatherService) {
        this.timeSimulationComponent = timeSimulationComponent;
        this.weatherService = weatherService;
    }
    
    @PostConstruct
    public void init() {
        timeSimulationComponent.registerObserver(this);
    }

    public void setCurrentEnergyGeneration(double currentEnergyGeneration) {
        this.currentEnergyGeneration = currentEnergyGeneration;
    }

    @Override
    public void timeUpdated() {
        currentEnergyGeneration = 0;
        for (EnergyProducer energyProducer : energyProducers) {
            energyProducer.calculateCurrentPowerGeneration(weatherService);
            currentEnergyGeneration += energyProducer.getCurrentPowerGeneration();
        }
        System.out.printf("Current Energy generation: %.2f kWh%n", currentEnergyGeneration);
    }
}

