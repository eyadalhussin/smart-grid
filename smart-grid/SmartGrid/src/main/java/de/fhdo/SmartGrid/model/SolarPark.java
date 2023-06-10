package de.fhdo.SmartGrid.model;
import jakarta.persistence.Entity;
import org.springframework.web.client.RestTemplate;

@Entity
public class SolarPark extends EnergyProducer {


    public SolarPark() {

    }

    public SolarPark(String name, double capacity) {
        super(name, capacity);
    }

    @Override
    protected void calculateCurrentPowerGeneration() {
        RestTemplate restTemplate = new RestTemplate();

    }

}
