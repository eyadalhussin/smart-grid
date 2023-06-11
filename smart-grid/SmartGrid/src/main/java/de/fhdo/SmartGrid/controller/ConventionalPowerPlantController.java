package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.model.ConventionalPowerPlant;
import de.fhdo.SmartGrid.service.ConventionalPowerPlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conventional_power_plant")
public class ConventionalPowerPlantController {

    private final ConventionalPowerPlantService conventionalPowerPlantService;

    @Autowired
    public ConventionalPowerPlantController(ConventionalPowerPlantService conventionalPowerPlantService) {
        this.conventionalPowerPlantService = conventionalPowerPlantService;
    }

    @PutMapping
    public ConventionalPowerPlant addConventionalPowerPlant(@RequestBody ConventionalPowerPlant conventionalPowerPlant) {
        return conventionalPowerPlantService.addConventionalPowerPlant(conventionalPowerPlant);
    }
}
