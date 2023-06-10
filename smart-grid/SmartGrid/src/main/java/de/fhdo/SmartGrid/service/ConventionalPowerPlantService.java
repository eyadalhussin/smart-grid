package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.repository.ConventionalPowerPlanRepository;
import de.fhdo.SmartGrid.model.ConventionalPowerPlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConventionalPowerPlantService {

    private final ConventionalPowerPlanRepository repository;
    private final EnergyProducerService energyProducerService;

    @Autowired
    public ConventionalPowerPlantService(ConventionalPowerPlanRepository repository, EnergyProducerService energyProducerService) {
        this.repository = repository;
        this.energyProducerService = energyProducerService;
    }


    public List<ConventionalPowerPlant> findAll() {
        return repository.findAll();
    }

    public ConventionalPowerPlant findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ConventionalPowerPlant save(ConventionalPowerPlant powerPlant) {
        try {
            energyProducerService.addEnergyProducer(powerPlant);
            return repository.save(powerPlant);
        } catch (DataAccessException e) {
            energyProducerService.removeEnergyProducer(powerPlant);
            throw e;
        }
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
