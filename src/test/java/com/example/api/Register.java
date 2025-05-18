package com.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import java.util.List;

public class Register extends Login {

    @Test
    public void RegisterUser() {
        RestAssured.baseURI = "https://whitesomehouse.com";

        String requestBody = "{" +
                "\"email\" : \"afmiruri@gmail.com\"," +
                "\"full_name\" : \"Afmi Ruri\"," +
                "\"password\" : \"P@ssw0rd\"," +
                "\"departement\" : \"manager\"," +
                "\"phone_number\" : \"08212345345\"," +
                "}";

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .post("/webhook/api/register")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200);
        assertTrue(response.asString().contains("success") || response.asString().contains("registered"));
        List<Object> objects = response.jsonPath().getList("$");
        assertNotNull(objects, "List objek tidak boleh null");
        assertTrue(objects.size() > 0, "Harus ada setidaknya 1 objek");
        System.out.println(response.getBody().asPrettyString());
    }

    @Test
    public void LoginUser() {
        assertNotNull(TOKEN, "Login token should not be null");
    }

}
