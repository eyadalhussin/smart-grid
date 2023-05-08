package de.fhdo.SmartGrid.controller;

import de.fhdo.SmartGrid.model.SolarPark;
import de.fhdo.SmartGrid.service.SolarParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solarpark")
public class SolarParkController {

    @Autowired
    private SolarParkService solarParkService;

    @GetMapping("/all")
    public String all() {
        return solarParkService.findAll().toString();
    }

    @GetMapping("/testadd")
    public String testadd() {
        return solarParkService.save(new SolarPark("SoPa01", 1000.0, 100.0, 20)).toString();
    }


}
