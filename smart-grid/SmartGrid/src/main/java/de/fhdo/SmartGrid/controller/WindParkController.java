package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.model.WindPark;
import de.fhdo.SmartGrid.service.WindParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/windpark")
public class WindParkController {

    private final WindParkService windTurbineService;


    @Autowired
    public WindParkController(WindParkService windTurbineService) {
        this.windTurbineService = windTurbineService;
    }

    @GetMapping
    public List<WindPark> getWindTurbines() {
        return windTurbineService.getWindTurbines();
    }

    @PutMapping
    public WindPark addWindTurbine(@RequestBody WindPark windTurbine) {
        return windTurbineService.save(windTurbine);
    }
}
