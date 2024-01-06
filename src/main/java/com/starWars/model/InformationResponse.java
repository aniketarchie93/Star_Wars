package com.starWars.model;

public class InformationResponse {
	 private Starship starship;
	 private String crew;
	 private boolean isLeiaOnPlanet;
	 
	 
	public InformationResponse(Starship starship, String crew, boolean isLeiaOnPlanet) {
		super();
		this.starship = starship;
		this.crew = crew;
		this.isLeiaOnPlanet = isLeiaOnPlanet;
	}
	public Starship getStarship() {
		return starship;
	}
	public void setStarship(Starship starship) {
		this.starship = starship;
	}
	public String getCrew() {
		return crew;
	}
	public void setCrew(String crew) {
		this.crew = crew;
	}
	public boolean getIsLeiaOnPlanet() {
		return isLeiaOnPlanet;
	}
	public void setIsLeiaOnPlanet(boolean isLeiaOnPlanet) {
		this.isLeiaOnPlanet = isLeiaOnPlanet;
	}
}
