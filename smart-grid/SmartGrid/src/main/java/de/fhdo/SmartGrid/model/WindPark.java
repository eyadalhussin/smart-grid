package de.fhdo.SmartGrid.model;
import jakarta.persistence.Entity;

@Entity
public class WindPark extends EnergyProducer {

    private int numberOfTurbines;
    private double turbineEfficiency;
    private double turbineDiameter;


    public WindPark() {

    }

    public WindPark(String name) {
        super(name);
    }

    public int getNumberOfTurbines() {
        return numberOfTurbines;
    }

    public void setNumberOfTurbines(int numberOfTurbines) {
        this.numberOfTurbines = numberOfTurbines;
    }

    public double getTurbineEfficiency() {
        return turbineEfficiency;
    }

    public void setTurbineEfficiency(double turbineEfficiency) {
        this.turbineEfficiency = turbineEfficiency;
    }

    public double getTurbineDiameter() {
        return turbineDiameter;
    }

    public void setTurbineDiameter(double turbineDiameter) {
        this.turbineDiameter = turbineDiameter;
    }
}
