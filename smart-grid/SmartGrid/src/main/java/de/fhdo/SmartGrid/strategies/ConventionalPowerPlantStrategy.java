package de.fhdo.SmartGrid.strategies;

import de.fhdo.SmartGrid.model.ConventionalPowerPlant;
import de.fhdo.SmartGrid.model.EnergyProducer;
import de.fhdo.SmartGrid.service.WeatherService;

public class ConventionalPowerPlantStrategy implements PowerGenerationStrategy {
    @Override
    public double calculateCurrentPowerGeneration(EnergyProducer energyProducer, WeatherService weatherService) {
        if (!(energyProducer instanceof ConventionalPowerPlant conventionalPowerPlant))
            throw new IllegalArgumentException("EnergyProducer is not a ConventionalPowerPlant!");

        double basePowerOutput = 100;
        return (basePowerOutput * conventionalPowerPlant.getFuelType().getEfficiency()) * conventionalPowerPlant.getNumberOfGenerators();

    }

    @Override
    public Class<? extends EnergyProducer> appliesTo() {
        return ConventionalPowerPlant.class;
    }
}
