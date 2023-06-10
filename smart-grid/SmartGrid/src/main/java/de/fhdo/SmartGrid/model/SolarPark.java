package de.fhdo.SmartGrid.model;

import jakarta.persistence.Entity;

@Entity
public class SolarPark extends EnergyProducer {


    public SolarPark() {

    }

    @Override
    protected void setCurrentPowerGeneration() {

    }

    public SolarPark(String name, double capacity) {
        super(name, capacity);
    }

    @Override
    public String toString() {
        return "SolarPark{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", capacity=" + getCapacity() +
                '}';
    }

}
