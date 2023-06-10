package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Components.TimeObserver;
import de.fhdo.SmartGrid.Components.TimeSimulationComponent;
import de.fhdo.SmartGrid.model.ConventionalPowerPlant;
import de.fhdo.SmartGrid.model.EnergyProducer;
import de.fhdo.SmartGrid.repository.EnergyProducerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class EnergyProducerService {

    private final TimeSimulationComponent timeSimulationComponent;
    private final WeatherService weatherService;
    private final List<EnergyProducer> energyProducers = new ArrayList<>();
    private final EnergyProducerRepository energyProducerRepository;
    private double currentEnergyGeneration;

    @Autowired
    public EnergyProducerService(TimeSimulationComponent timeSimulationComponent, WeatherService weatherService, EnergyProducerRepository energyProducerRepository) {
        this.timeSimulationComponent = timeSimulationComponent;
        this.weatherService = weatherService;
        this.energyProducerRepository = energyProducerRepository;
    }
    
    @PostConstruct
    public void init() {
        energyProducers.addAll(energyProducerRepository.findAll());
    }

    public double calculateCurrentEnergyGeneration() {
        currentEnergyGeneration = 0;
        for (EnergyProducer energyProducer : energyProducers) {
            energyProducer.calculateCurrentPowerGeneration(weatherService);
            currentEnergyGeneration += energyProducer.getCurrentPowerGeneration();
        }
        return currentEnergyGeneration;
    }

    public double getCurrentEnergyGeneration() {
        return currentEnergyGeneration;
    }

    public void addEnergyProducer(EnergyProducer energyProducer) {
        energyProducers.add(energyProducer);
    }

    public void removeEnergyProducer(EnergyProducer energyProducer) {
        energyProducers.remove(energyProducer);
    }
}

