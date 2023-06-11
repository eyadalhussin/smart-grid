package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.model.EnergyStorage;
import de.fhdo.SmartGrid.service.EnergyStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/energystorages")
public class EnergyStorageController {

    private EnergyStorageService energyStorageService;

    @Autowired
    public EnergyStorageController(EnergyStorageService energyStorageService) {
        this.energyStorageService = energyStorageService;
    }

    @GetMapping
    public List<EnergyStorage> getAllEnergyStorages() {
        return energyStorageService.getAllEnergyStorages();
    }


    @PutMapping
    public EnergyStorage createEnergyStorageTest(@RequestBody EnergyStorage energyStorage) {
        return energyStorageService.addEnergyStorage(energyStorage);
    }
}
