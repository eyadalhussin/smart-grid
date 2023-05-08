package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Repository.SolarParkRepository;
import de.fhdo.SmartGrid.model.SolarPark;
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
}
