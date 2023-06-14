package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.model.EnergyProducer;
import de.fhdo.SmartGrid.repository.EnergyProducerRepository;
import de.fhdo.SmartGrid.strategies.PowerGenerationStrategy;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class EnergyProducerService {

    private final WeatherService weatherService;
    private final EnergyProducerRepository energyProducerRepository;
    private final Map<Class<? extends EnergyProducer>, PowerGenerationStrategy> strategies;

    @Autowired
    public EnergyProducerService(WeatherService weatherService,
                                 EnergyProducerRepository energyProducerRepository,
                                 List<PowerGenerationStrategy> strategies) {
        this.weatherService = weatherService;
        this.energyProducerRepository = energyProducerRepository;
        this.strategies = strategies.stream()
                .collect(Collectors.toMap(PowerGenerationStrategy::appliesTo, Function.identity()));
    }

    public double calculateCurrentEnergyGeneration() {
        double currentEnergyGeneration = 0; // in kWh
        List<EnergyProducer> energyProducers = energyProducerRepository.findAll();
        for (EnergyProducer energyProducer : energyProducers) {
            PowerGenerationStrategy strategy = strategies.get(energyProducer.getClass());
            double powerGeneration = strategy.calculateCurrentPowerGeneration(energyProducer, weatherService);
            energyProducer.setCurrentPowerGeneration(powerGeneration);
            currentEnergyGeneration += powerGeneration;
        }
        return currentEnergyGeneration;
    }
}

