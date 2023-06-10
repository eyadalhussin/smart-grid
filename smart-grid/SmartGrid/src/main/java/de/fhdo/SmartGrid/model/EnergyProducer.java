package de.fhdo.SmartGrid.model;

import de.fhdo.SmartGrid.Components.EnergyHandler;
import de.fhdo.SmartGrid.Components.TimeObserver;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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

    protected abstract void setCurrentPowerGeneration();

    public double getCurrentPowerGeneration() {
        return currentPowerGeneration;
    }

}
