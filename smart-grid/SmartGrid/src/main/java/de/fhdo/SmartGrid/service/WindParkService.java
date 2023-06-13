package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.model.WindPark;
import de.fhdo.SmartGrid.repository.WindParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WindParkService {

    private final WindParkRepository windTurbineRepository;
    private final EnergyProducerService energyProducerService;


    @Autowired
    public WindParkService (WindParkRepository windTurbineRepository, EnergyProducerService energyProducerService) {
        this.windTurbineRepository = windTurbineRepository;
        this.energyProducerService = energyProducerService;
    }

    public List<WindPark> getWindTurbines() {
        return windTurbineRepository.findAll();
    }

    public WindPark save(WindPark windPark) {
        try {
            energyProducerService.addEnergyProducer(windPark);
            return windTurbineRepository.save(windPark);
        } catch (DataAccessException e) {
            energyProducerService.removeEnergyProducer(windPark);
            throw e;
        }
    }

    public double calculatePowerOutput() {
        double totalPowerOutput = 0;
        return totalPowerOutput;
    }

}
