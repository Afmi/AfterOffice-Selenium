package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Post extends Login {

    @Test
    public void testAddObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String body = "{" +
                "\"name\": \"Apple MacBook Pro 16\"," +
                "\"data\": {" +
                "\"year\": 2019," +
                "\"price\": 1849.99," +
                "\"cpu_model\": \"Intel Core i9\"," +
                "\"hard_disk_size\": \"1 TB\"," +
                "\"capacity\": \"2 cpu\"," +
                "\"screen_size\": \"14 Inch\"," +
                "\"color\": \"red\"" +
                "}}";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TOKEN)
                .contentType("application/json")
                .body(body)
                .post("/webhook/api/objects")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200);
        assertTrue(response.asString().contains("Apple MacBook Pro"));
    }
}
