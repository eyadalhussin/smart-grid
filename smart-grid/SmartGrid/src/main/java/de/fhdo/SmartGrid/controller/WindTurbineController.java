package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.model.WindTurbine;
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
    public List<WindTurbine> getWindTurbines() {
        return windTurbineService.getWindTurbines();
    }

    @GetMapping("/testadd")
    public String testadd() {
        return windTurbineService.save(new WindTurbine("WT01", 1000.0, 10.0, 0.5)).toString();
    }

    @PostMapping
    public WindTurbine addWindTurbine(@RequestBody WindTurbine windTurbine) {
        return windTurbineService.save(windTurbine);
    }
}
