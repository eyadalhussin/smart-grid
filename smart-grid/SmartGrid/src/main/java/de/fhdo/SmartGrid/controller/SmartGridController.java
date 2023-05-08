package de.fhdo.SmartGrid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

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