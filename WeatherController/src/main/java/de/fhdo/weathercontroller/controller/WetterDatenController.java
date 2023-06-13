package de.fhdo.weathercontroller.controller;

import de.fhdo.weathercontroller.WeatherControllerApplication;
import de.fhdo.weathercontroller.model.WetterDaten;
import de.fhdo.weathercontroller.service.WetterDatenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class WetterDatenController {
    private final WetterDatenService wetterDatenService;

    @Autowired
    public WetterDatenController(WetterDatenService wetterDatenService) {
        this.wetterDatenService = wetterDatenService;
    }

    @RequestMapping("/all")
    public String all() {
        List<WetterDaten> wetterDatenList = wetterDatenService.findAll();
        System.out.println(wetterDatenList.stream().findFirst());
        return wetterDatenList.toString();
    }

    @RequestMapping("/now")
    public String now() {
        return wetterDatenService.findNow().toString();
    }

    @RequestMapping("/status")
    public String status() {
        if(!WeatherControllerApplication.running) {
            return WeatherControllerApplication.exception.getMessage();
        }
        return "OK";
    }
}
