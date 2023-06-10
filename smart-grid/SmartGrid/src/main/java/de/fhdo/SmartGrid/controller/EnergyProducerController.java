package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.service.EnergyProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/energy_producer")
public class EnergyProducerController {
    private final EnergyProducerService energyProducerService;
    @Autowired
    public EnergyProducerController(EnergyProducerService energyProducerService) {
        this.energyProducerService = energyProducerService;
    }

    @GetMapping("/test-add")
    public void testAdd() {
        energyProducerService.testAdd();
    }
}
