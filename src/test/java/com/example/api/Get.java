package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Get extends Login {

    @Test
    public void GetAllObjects() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TOKEN)
                .get("/webhook/api/objects")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200);
        assertTrue(response.jsonPath().getList("$").size() > 0);
    }

    @Test
    public void GetObjectById() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TOKEN)
                .get("/webhook-test/api/objects?id=3")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200);
        assertNotNull(response.jsonPath().get("id"));
    }

    @Test
    public void GetSingleObjectByUUID() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TOKEN)
                .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/12")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200);
        assertNotNull(response.jsonPath().get("name"));
    }

    @Test
    public void testGetAllDepartments() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TOKEN)
                .get("/webhook/api/department")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200);
        assertTrue(response.asString().contains("department") || response.asString().contains("name"));
    }
}
