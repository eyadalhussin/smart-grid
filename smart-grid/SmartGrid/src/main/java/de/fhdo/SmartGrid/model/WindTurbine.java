package de.fhdo.SmartGrid.model;


import jakarta.persistence.Entity;

@Entity
public class WindTurbine extends EnergyProducer {

    private double windSpeed;
    private double efficiency;

    public WindTurbine() {

    }

    public WindTurbine(String name, double capacity, double windSpeed, double efficiency) {
        super(name, capacity);
        this.windSpeed = windSpeed;
        this.efficiency = efficiency;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public double getEfficiency() {
        return efficiency;
    }

}
