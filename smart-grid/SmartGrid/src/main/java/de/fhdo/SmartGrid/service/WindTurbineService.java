package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.model.WeatherModel;
import de.fhdo.SmartGrid.model.WindTurbine;
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

    public List<WindTurbine> getWindTurbines() {
        return windTurbineRepository.findAll();
    }

    public WindTurbine save(WindTurbine windTurbine) {
        return windTurbineRepository.save(windTurbine);
    }

    public double calculatePowerOutput() {
        double totalPowerOutput = 0;
        return totalPowerOutput;
    }

}
