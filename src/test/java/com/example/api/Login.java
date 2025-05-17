package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

public class Login {
    protected static String TOKEN;

    @BeforeClass
    public void loginAndSetToken() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{" +
                        "\"email\": \"afmiruri@gmail.com\"," +
                        "\"password\": \"P@ssw0rd\"" +
                        "}")
                .post("/webhook/api/login")
                .then()
                .extract().response();

        TOKEN = response.jsonPath().getString("token");
    }
}
