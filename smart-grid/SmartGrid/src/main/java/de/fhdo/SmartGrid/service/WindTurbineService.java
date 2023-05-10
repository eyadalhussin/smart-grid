package de.fhdo.SmartGrid.service;

import de.fhdo.SmartGrid.model.WindTurbine;
import de.fhdo.SmartGrid.repository.WindTurbineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WindTurbineService {

    private final WindTurbineRepository windTurbineRepository;

    @Autowired
    public WindTurbineService(WindTurbineRepository windTurbineRepository) {
        this.windTurbineRepository = windTurbineRepository;
    }

    public List<WindTurbine> getWindTurbines() {
        return windTurbineRepository.findAll();
    }

    public WindTurbine save(WindTurbine windTurbine) {
        return windTurbineRepository.save(windTurbine);
    }
}
