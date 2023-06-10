package de.fhdo.SmartGrid.model;


import jakarta.persistence.Entity;

@Entity
public class WindTurbine extends EnergyProducer {

    public WindTurbine() {

    }

    @Override
    public void calculateCurrentPowerGeneration() {

    }

    public WindTurbine(String name, double capacity) {
        super(name, capacity);
    }

}
