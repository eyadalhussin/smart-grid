package de.fhdo.SmartGrid.strategies;

import de.fhdo.SmartGrid.model.EnergyProducer;
import de.fhdo.SmartGrid.model.SolarPark;
import de.fhdo.SmartGrid.model.WeatherModel;
import de.fhdo.SmartGrid.service.WeatherService;

import java.util.Random;

public class SolarParkStrategy implements PowerGenerationStrategy {
    @Override
    public double calculateCurrentPowerGeneration(EnergyProducer energyProducer, WeatherService weatherService) {
        if(!(energyProducer instanceof SolarPark solarPark)) {
            throw new IllegalArgumentException("EnergyProducer is not a SolarPark");
        }

        WeatherModel weather = weatherService.getCurrentWeather();

        double temp = weather.getTemp();
        int cloudiness = weather.getCloud();

        // Stromerzeugung basierend auf Temperatur
        double powerOutput = temp / 100;

        // Einfluss der Wolken
        powerOutput *= (1 - cloudiness / 100.0);

        // Zufällige Schwankung von -10% bis +10%
        powerOutput *= 0.9 + (0.2 * new Random().nextDouble());

        // Multiplizierung mit Anzahl der Zellen, Effizienz
        powerOutput *= solarPark.getNumberOfCells() * solarPark.getCellEfficiency();

        return powerOutput;
    }

    @Override
    public Class<? extends EnergyProducer> appliesTo() {
        return SolarPark.class;
    }
}
