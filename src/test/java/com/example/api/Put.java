package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Put extends Login {

        @Test
        public void testUpdateObject() {
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
                                .put("/webhook-test/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/3")
                                .then()
                                .extract().response();

                assertEquals(response.statusCode(), 200);
        }

}
