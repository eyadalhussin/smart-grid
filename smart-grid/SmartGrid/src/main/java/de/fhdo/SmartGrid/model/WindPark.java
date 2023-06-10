package de.fhdo.SmartGrid.model;
import de.fhdo.SmartGrid.service.WeatherService;
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

    @Override
    public void calculateCurrentPowerGeneration(WeatherService weatherService){
        System.out.println("WindPark.calculateCurrentPowerGeneration");
        WeatherModel weather = weatherService.getCurrentWeather();

        double area = Math.PI * Math.pow(getTurbineDiameter() / 2, 2);

        //Formel Betz'sche Gesetz 0.5 * ρ * A * v³ * η, Luftdichte i.d.R. 1.225 kg/m³
        double powerOutput = 0.5 * 1.225 * area * Math.pow(weather.getWindSpeed(), 3) * getTurbineEfficiency();

        setPowerGeneration(powerOutput);
    }
}
