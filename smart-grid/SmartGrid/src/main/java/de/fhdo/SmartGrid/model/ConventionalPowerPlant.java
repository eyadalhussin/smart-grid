package de.fhdo.SmartGrid.model;

import de.fhdo.SmartGrid.service.WeatherService;
import jakarta.persistence.Entity;

@Entity
public class ConventionalPowerPlant extends EnergyProducer{

    public ConventionalPowerPlant() {
    }

    @Override
    public void calculateCurrentPowerGeneration(WeatherService weatherService) {
        currentPowerGeneration = 100d;
    }

    public ConventionalPowerPlant(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return "ConventionalPowerPlant{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
