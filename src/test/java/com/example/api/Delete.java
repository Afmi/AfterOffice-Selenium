package com.example.api;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.testng.Assert.*;

public class Delete extends Login {

    @Test
    public void testDeleteObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TOKEN)
                .delete("/webhook-test/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/3")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200);
    }

}
