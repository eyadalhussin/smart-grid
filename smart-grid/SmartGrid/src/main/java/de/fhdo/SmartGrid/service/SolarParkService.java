package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.repository.SolarParkRepository;
import de.fhdo.SmartGrid.model.SolarPark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolarParkService {

    private final SolarParkRepository repository;

    @Autowired
    public SolarParkService(SolarParkRepository repository) {
        this.repository = repository;
    }


    public List<SolarPark> findAll() {
        return repository.findAll();
    }

    public SolarPark findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SolarPark save(SolarPark solarPark) {
        try {
            return repository.save(solarPark);
        } catch (DataAccessException e) {
            throw e;
        }

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
