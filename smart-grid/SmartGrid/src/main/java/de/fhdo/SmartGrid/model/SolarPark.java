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

    @Override
    public void calculateCurrentPowerGeneration(WeatherService weatherService) {
        System.out.println("SolarPark.calculateCurrentPowerGeneration");
        WeatherModel weather = weatherService.getCurrentWeather();

        double temp = weather.getTemp();
        int cloudiness = weather.getCloud();

        // Stromerzeugung basierend auf Temperatur
        double powerOutput = temp / 100;

        // Einfluss der Wolken
        powerOutput *= (1 - cloudiness / 100.0);

        // Zufällige Schwankung von -10% bis +10%
        powerOutput *= 0.9 + (0.2 * RANDOM.nextDouble());

        // Multiplizierung mit Anzahl der Zellen, Effizienz
        powerOutput *= numberOfCells * cellEfficiency;

        setPowerGeneration(powerOutput);
    }
}
