package de.fhdo.SmartGrid.model;
import de.fhdo.SmartGrid.service.WeatherService;
import jakarta.persistence.Entity;
import java.util.Random;

@Entity
public class SolarPark extends EnergyProducer {

    private int numberOfCells;

    public int getNumberOfCells() {
        return numberOfCells;
    }

    public void setNumberOfCells(int numberOfCells) {
        this.numberOfCells = numberOfCells;
    }

    @Override
    public String toString() {
        return "SolarPark{" +
                "numberOfCells=" + numberOfCells +
                ", cellEfficiency=" + cellEfficiency +
                '}';
    }

    public double getCellEfficiency() {
        return cellEfficiency;
    }

    public void setCellEfficiency(double cellEfficiency) {
        this.cellEfficiency = cellEfficiency;
    }

    private double cellEfficiency;

    private static final Random RANDOM = new Random(); // Das ist die RANDOM-Instanz

    public SolarPark() {

    }

    public SolarPark(String name) {
        super(name);
    }
}
