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

    @Autowired
    public WindParkService (WindParkRepository windTurbineRepository) {
        this.windTurbineRepository = windTurbineRepository;
    }

    public List<WindPark> getWindTurbines() {
        return windTurbineRepository.findAll();
    }

    public WindPark save(WindPark windPark) {
        try {
            return windTurbineRepository.save(windPark);
        } catch (DataAccessException e) {
            throw e;
        }
    }

    public double calculatePowerOutput() {
        double totalPowerOutput = 0;
        return totalPowerOutput;
    }

}
