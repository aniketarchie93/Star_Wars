package com.starWars.controller;


import com.starWars.model.InformationResponse;
import com.starWars.model.Starship;
import com.starWars.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StarWarController {

    @Autowired
    private InformationService informationService;

    @GetMapping(value = "/information")
    public ResponseEntity<InformationResponse> getInformation() {
        Starship starship = informationService.getStarshipInformation("Death Star");
        String crew = informationService.getDeathStarCrew();
        boolean isLeiaOnPlanet = informationService.isLeiaOnPlanet("Alderaan");
        if (starship == null) {
            starship = new Starship();
        }

        InformationResponse response = new InformationResponse(starship, crew, isLeiaOnPlanet);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
