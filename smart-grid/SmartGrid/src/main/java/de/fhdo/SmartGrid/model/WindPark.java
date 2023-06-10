package de.fhdo.SmartGrid.model;


import de.fhdo.SmartGrid.service.WeatherService;
import jakarta.persistence.Entity;

@Entity
public class WindPark extends EnergyProducer {

    public WindPark() {

    }

    public WindPark(String name) {
        super(name);
    }

    @Override
    public void calculateCurrentPowerGeneration(WeatherService weatherService) {
    }

}
