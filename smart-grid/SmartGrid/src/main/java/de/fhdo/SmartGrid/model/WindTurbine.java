package de.fhdo.SmartGrid.model;


import de.fhdo.SmartGrid.service.WeatherService;
import jakarta.persistence.Entity;

@Entity
public class WindTurbine extends EnergyProducer {

    public WindTurbine() {

    }

    public WindTurbine(String name, double capacity) {
        super(name, capacity);
    }

    @Override
    public void calculateCurrentPowerGeneration(WeatherService weatherService) {

    }

}
