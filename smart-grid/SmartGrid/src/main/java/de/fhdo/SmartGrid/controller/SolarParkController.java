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

    @GetMapping("/testadd")
    public String testadd() {
        return solarParkService.save(new SolarPark("SoPa01", 1000.0, 100.0, 20)).toString();
    }

    @GetMapping("/{city}/efficiency")
    public double getSolarPanelEfficiencyByCity(@PathVariable String city,
                                                @RequestParam(value = "timestamp", required = false) Long timestamp) {
        WeatherModel weatherModel = weatherService.getWeatherByCity(city);
        return solarParkService.calculateEfficiency(weatherModel);
    }
}
