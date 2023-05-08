package de.fhdo.SmartGrid.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmartGridController {

    @GetMapping("/home")
    public String home() {
        return "<h1>Hello World</h1>";
    }
}


/*
Energiespeicher
Energieerzeuger
|---- Erneuerbare Energien
|-------- Solar
|-------- Wind





 */