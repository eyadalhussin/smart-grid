package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.model.SolarPark;
import de.fhdo.SmartGrid.model.WeatherModel;
import de.fhdo.SmartGrid.service.SolarParkService;
import de.fhdo.SmartGrid.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/solarpark")
public class SolarParkController {

    @Autowired
    private SolarParkService solarParkService;
    private final WeatherService weatherService;

    public SolarParkController(SolarParkService solarParkService, WeatherService weatherService) {
        this.solarParkService = solarParkService;
        this.weatherService = weatherService;
    }

    @GetMapping("/all")
    public String all() {
        return solarParkService.findAll().toString();
    }
}
