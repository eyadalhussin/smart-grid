package de.fhdo.SmartGrid.model;

import de.fhdo.SmartGrid.service.WeatherService;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EnergyProducer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String name;
    protected double currentPowerGeneration;
    public EnergyProducer() {
    }

    public EnergyProducer(String name) {
        this.name = name;
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


    public void setPowerGeneration(double powerGeneration) {
        this.currentPowerGeneration = powerGeneration;
    }

    public abstract void calculateCurrentPowerGeneration(WeatherService weatherService);

    public double getCurrentPowerGeneration() {
        return currentPowerGeneration;
    }

}
