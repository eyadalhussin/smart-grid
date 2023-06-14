package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.repository.ConventionalPowerPlantRepository;
import de.fhdo.SmartGrid.model.ConventionalPowerPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConventionalPowerPlantService {

    private final ConventionalPowerPlantRepository repository;

    @Autowired
    public ConventionalPowerPlantService(ConventionalPowerPlantRepository repository) {
        this.repository = repository;
    }


    public List<ConventionalPowerPlant> findAll() {
        return repository.findAll();
    }

    public ConventionalPowerPlant findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ConventionalPowerPlant save(ConventionalPowerPlant powerPlant) {
        try {
            return repository.save(powerPlant);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
