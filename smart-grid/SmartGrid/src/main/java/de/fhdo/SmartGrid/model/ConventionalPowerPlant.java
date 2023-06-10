package de.fhdo.SmartGrid.model;

import jakarta.persistence.Entity;

@Entity
public class ConventionalPowerPlant extends EnergyProducer{
    private FuelType fuelType;
    private double efficiency;

    public ConventionalPowerPlant() {
    }

    @Override
    protected void calculateCurrentPowerGeneration() {

    }

    public ConventionalPowerPlant(String name, double capacity, FuelType fuelType, double efficiency) {
        super(name, capacity);
        this.fuelType = fuelType;
        this.efficiency = efficiency;
    }

    public String getFuelType() {
        return fuelType.name();
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return "ConventionalPowerPlant{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", capacity=" + getCapacity() +
                ", fuelType='" + fuelType + '\'' +
                ", efficiency=" + efficiency +
                '}';
    }
}
