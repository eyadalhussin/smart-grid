package de.fhdo.SmartGrid.model;


import jakarta.persistence.Entity;

@Entity
public class WindTurbine extends EnergyProducer {

    public WindTurbine() {

    }

    @Override
    protected void setCurrentPowerGeneration() {

    }

    public WindTurbine(String name, double capacity) {
        super(name, capacity);
    }

}
