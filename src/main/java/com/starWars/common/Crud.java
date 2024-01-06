package com.starWars.common;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Crud {

    public Response getAPIWithoutTokenWithQueryParam(String baseUri, String basePath, HashMap queryParam) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;
        return (Response) given().contentType(ContentType.JSON).queryParams(queryParam).log().all().when().get().then().extract();
    }


    public Response getAPIWithoutToken(String uri) {
        RestAssured.baseURI = uri;
        RestAssured.basePath = "";
        return (Response) given().contentType(ContentType.JSON).log().all().when().get().then().extract();
    }

}
