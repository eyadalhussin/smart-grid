package de.fhdo.weathercontroller.service;

import de.fhdo.weathercontroller.WeatherControllerApplication;
import de.fhdo.weathercontroller.model.WetterDaten;
import de.fhdo.weathercontroller.repository.WetterDatenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WetterDatenService {
    private final WetterDatenRepository repository;

    @Autowired
    public WetterDatenService(WetterDatenRepository repository) {
        this.repository = repository;
    }

    public List<WetterDaten> findAll() {
        return repository.findAll();
    }

    public WetterDaten findNow() {
        System.out.println("CurrentTime: " + WeatherControllerApplication.CurrentTime);
        return repository.findById(WeatherControllerApplication.CurrentTime).orElse(null);
    }
}
