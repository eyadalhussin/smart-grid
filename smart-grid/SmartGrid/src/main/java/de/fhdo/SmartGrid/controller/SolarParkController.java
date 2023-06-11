package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.model.SolarPark;
import de.fhdo.SmartGrid.service.SolarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solarpark")
public class SolarParkController {

    private final SolarParkService solarParkService;

    @Autowired
    public SolarParkController(SolarParkService solarParkService){
        this.solarParkService = solarParkService;
    }

    @GetMapping
    public String all() {
        return solarParkService.findAll().toString();
    }

    @PutMapping
    public SolarPark addSolarPark(@RequestBody SolarPark solarPark) {
        return solarParkService.addSolarPark(solarPark);
    }
}
