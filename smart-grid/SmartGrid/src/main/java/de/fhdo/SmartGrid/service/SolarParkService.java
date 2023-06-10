package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.repository.SolarParkRepository;
import de.fhdo.SmartGrid.model.SolarPark;
import de.fhdo.SmartGrid.model.WeatherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolarParkService {

    private final SolarParkRepository repository;
    private final EnergyProducerService energyProducerService;

    @Autowired
    public SolarParkService(SolarParkRepository repository, EnergyProducerService energyProducerService) {
        this.repository = repository;
        this.energyProducerService = energyProducerService;
    }


    public List<SolarPark> findAll() {
        return repository.findAll();
    }

    public SolarPark findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SolarPark addSolarPark(SolarPark solarPark) {
        try {
            energyProducerService.addEnergyProducer(solarPark);
            return repository.save(solarPark);
        } catch (DataAccessException e) {
            energyProducerService.removeEnergyProducer(solarPark);
            throw e;
        }

    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
