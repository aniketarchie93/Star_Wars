package com.starWars.service;

import com.starWars.model.Starship;

public interface InformationService {
	Starship getStarshipInformation(String name);

    String getDeathStarCrew();

    boolean isLeiaOnPlanet(String name);
}
