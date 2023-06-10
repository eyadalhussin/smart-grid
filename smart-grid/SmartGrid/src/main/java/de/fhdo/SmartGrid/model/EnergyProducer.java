package de.fhdo.SmartGrid.model;

import de.fhdo.SmartGrid.service.WeatherService;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EnergyProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String name;
    private double capacity;
    protected double currentPowerGeneration;
    public EnergyProducer() {
    }

    public EnergyProducer(String name, double capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public abstract void calculateCurrentPowerGeneration(WeatherService weatherService);

    public double getCurrentPowerGeneration() {
        return currentPowerGeneration;
    }

}
