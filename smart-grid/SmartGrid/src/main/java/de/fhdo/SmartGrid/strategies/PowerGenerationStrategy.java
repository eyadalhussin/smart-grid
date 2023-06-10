package de.fhdo.SmartGrid.strategies;

import de.fhdo.SmartGrid.model.EnergyProducer;
import de.fhdo.SmartGrid.service.WeatherService;

public interface PowerGenerationStrategy {
    double calculateCurrentPowerGeneration(EnergyProducer energyProducer, WeatherService weatherService);
    Class<? extends EnergyProducer> appliesTo();
}
