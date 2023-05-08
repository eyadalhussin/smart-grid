package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.Repository.ConventionalPowerPlanRepository;
import de.fhdo.SmartGrid.model.ConventionalPowerPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConventionalPowerPlantService {

    @Autowired
    private ConventionalPowerPlanRepository repository;

    public List<ConventionalPowerPlant> findAll() {
        return repository.findAll();
    }

    public ConventionalPowerPlant findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ConventionalPowerPlant save(ConventionalPowerPlant powerPlant) {
        return repository.save(powerPlant);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
