package de.fhdo.SmartGrid.strategies;

import de.fhdo.SmartGrid.model.EnergyProducer;
import de.fhdo.SmartGrid.model.WeatherModel;
import de.fhdo.SmartGrid.model.WindPark;
import de.fhdo.SmartGrid.service.WeatherService;

public class WindParkStrategy implements PowerGenerationStrategy{
    @Override
    public double calculateCurrentPowerGeneration(EnergyProducer energyProducer, WeatherService weatherService) {
        if(!(energyProducer instanceof WindPark windPark))
            throw new IllegalArgumentException("EnergyProducer is not a WindPark!");

        WeatherModel weather = weatherService.getCurrentWeather();

        double area = Math.PI * Math.pow(windPark.getTurbineDiameter() / 2, 2);

        //Formel Betz'sche Gesetz 0.5 * ρ * A * v³ * η, Luftdichte i.d.R. 1.225 kg/m³
        return 0.5 * 1.225 * area * Math.pow(weather.getWindSpeed(), 3) * windPark.getTurbineEfficiency();
    }

    @Override
    public Class<? extends EnergyProducer> appliesTo() {
        return WindPark.class;
    }
}
