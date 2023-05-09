package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Repository.SolarParkRepository;
import de.fhdo.SmartGrid.model.SolarPark;
import de.fhdo.SmartGrid.model.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolarParkService {

    @Autowired
    private SolarParkRepository repository;

    public List<SolarPark> findAll() {
        return repository.findAll();
    }

    public SolarPark findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SolarPark save(SolarPark solarPark) {
        return repository.save(solarPark);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public double calculateEfficiency(WeatherModel weatherModel) {
        double efficiency;

        //Effizienz anpassen
        if (weatherModel.getWeather().get(0).getMain().equalsIgnoreCase("clear")) {
            efficiency = 1.0;
        } else {
            efficiency = 0.5;
        }
        return efficiency;
    }
}
