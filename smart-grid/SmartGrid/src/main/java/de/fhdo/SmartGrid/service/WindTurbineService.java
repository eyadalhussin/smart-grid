package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.model.WindPark;
import de.fhdo.SmartGrid.repository.WindTurbineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WindTurbineService {

    private final WindTurbineRepository windTurbineRepository;
    private final WeatherService weatherService;

    @Autowired
    public WindTurbineService(WindTurbineRepository windTurbineRepository, WeatherService weatherService) {
        this.windTurbineRepository = windTurbineRepository;
        this.weatherService = weatherService;
    }

    public List<WindPark> getWindTurbines() {
        return windTurbineRepository.findAll();
    }

    public WindPark save(WindPark windTurbine) {
        return windTurbineRepository.save(windTurbine);
    }

    public double calculatePowerOutput() {
        double totalPowerOutput = 0;
        return totalPowerOutput;
    }

}
