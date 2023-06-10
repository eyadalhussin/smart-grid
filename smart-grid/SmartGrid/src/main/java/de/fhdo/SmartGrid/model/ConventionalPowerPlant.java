package de.fhdo.SmartGrid.model;

import de.fhdo.SmartGrid.service.WeatherService;
import jakarta.persistence.Entity;

@Entity
public class ConventionalPowerPlant extends EnergyProducer{

    private FuelType fuelType;
    private int numberOfGenerators;

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getNumberOfGenerators() {
        return numberOfGenerators;
    }

    public void setNumberOfGenerators(int numberOfGenerators) {
        this.numberOfGenerators = numberOfGenerators;
    }

    public ConventionalPowerPlant() {
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
