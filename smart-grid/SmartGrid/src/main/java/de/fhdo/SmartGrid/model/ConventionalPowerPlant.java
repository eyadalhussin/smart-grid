package de.fhdo.SmartGrid.model;

import de.fhdo.SmartGrid.service.WeatherService;
import jakarta.persistence.Entity;

@Entity
public class ConventionalPowerPlant extends EnergyProducer{

    public ConventionalPowerPlant() {
    }

    @Override
    public void calculateCurrentPowerGeneration(WeatherService weatherService) {

    }

    public ConventionalPowerPlant(String name, double capacity, FuelType fuelType, double efficiency) {
        super(name, capacity);
    }


    @Override
    public String toString() {
        return "ConventionalPowerPlant{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", capacity=" + getCapacity() +
                '}';
    }
}
