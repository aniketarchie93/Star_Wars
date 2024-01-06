package com.starWars.service;

import com.starWars.common.Crud;
import com.starWars.model.Starship;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InformationServiceImpl implements InformationService {
    final String baseUri = "https://swapi.dev/api/";
    Crud crud = new Crud();
    String starShipUrl;
    int page;
    Starship starship;
    String crew;
    HashMap<String, Starship> starshipInfo = new HashMap<>();
    HashMap<String, Boolean> princessOnPlanet = new HashMap<>();

    @SneakyThrows
    @Override
    public Starship getStarshipInformation(String starshipName) {
        if (!starshipInfo.containsKey(starshipName)) {
            starship = new Starship();
            boolean dataFound = false;
            page = 1;
            HashMap<String, Integer> map = new HashMap<>();
            map.put("page", page);
            Response response;
            JSONArray starships;

            while (!dataFound) {
                response = crud.getAPIWithoutTokenWithQueryParam(baseUri, "starships", map);
                starships = new JSONObject(response.asString()).getJSONArray("results");
                for (int i = 0; i < starships.length(); i++) {
                    starship.setName(starships.getJSONObject(i).getString("name"));
                    starship.setModel(starships.getJSONObject(i).getString("model"));
                    starship.setStarClass(starships.getJSONObject(i).getString("starship_class"));

                    if (starship.getName().equalsIgnoreCase(starshipName)) {
                        starShipUrl = starships.getJSONObject(i).getString("url");
                        dataFound = true;
                        break;
                    }
                }
                page++;
            }
            starshipInfo.put(starshipName, starship);
        }
        return starshipInfo.get(starshipName);
    }

    @Override
    public String getDeathStarCrew() {
        if (crew == null) crew = crud.getAPIWithoutToken(starShipUrl).jsonPath().get("crew");
        return crew;
    }

    @Override
    public boolean isLeiaOnPlanet(String planetName) {
        boolean isPrincessOnPlanet = false;
        if (!princessOnPlanet.containsKey(planetName)) {
            boolean dataFound = false;
            page = 1;
            HashMap<String, Integer> map = new HashMap<>();
            map.put("page", page);
            Response response;
            JSONArray planets;

            while (!dataFound) {
                response = crud.getAPIWithoutTokenWithQueryParam(baseUri, "planets", map);
                planets = new JSONObject(response.asString()).getJSONArray("results");
                for (int i = 0; i < planets.length(); i++) {
                    if (!dataFound && planets.getJSONObject(i).getString("name").equalsIgnoreCase(planetName)) {
                        JSONArray residentUrls = planets.getJSONObject(i).getJSONArray("residents");
                        for (Object residentUrl : residentUrls) {
                            if (crud.getAPIWithoutToken(residentUrl.toString()).jsonPath().get("name").toString().equalsIgnoreCase("Leia Organa")) {
                                isPrincessOnPlanet = true;
                                dataFound = true;
                                break;
                            }
                        }
                    }
                }
                page++;
            }
            princessOnPlanet.put(planetName,isPrincessOnPlanet);
        }
        return princessOnPlanet.get(planetName);
    }
}
