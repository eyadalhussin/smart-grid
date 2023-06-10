package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.model.WindPark;
import de.fhdo.SmartGrid.service.WindTurbineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/windturbine")
public class WindTurbineController {

    private final WindTurbineService windTurbineService;


    @Autowired
    public WindTurbineController(WindTurbineService windTurbineService) {
        this.windTurbineService = windTurbineService;
    }

    @GetMapping("/all")
    public List<WindPark> getWindTurbines() {
        return windTurbineService.getWindTurbines();
    }

    @PostMapping
    public WindPark addWindTurbine(@RequestBody WindPark windTurbine) {
        return windTurbineService.save(windTurbine);
    }

    @GetMapping("/poweroutput")
    public double calculatePowerOutput() {
        return windTurbineService.calculatePowerOutput();
    }
}
