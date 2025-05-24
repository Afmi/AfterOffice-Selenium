package RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import java.util.List;

public class Patch extends Login {

    @Test
    public void testPatchObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String body = "{" +
                "\"name\": \"Apple MacBook Pro 1611-albert12\"," +
                "\"year\": \"2030\"" +
                "}";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + TOKEN)
                .contentType("application/json")
                .body(body)
                .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/12")
                .then()
                .extract().response();

        assertEquals(response.statusCode(), 200);
        List<Object> objects = response.jsonPath().getList("$");
        assertNotNull(objects, "List objek tidak boleh null");
        assertTrue(objects.size() > 0, "Harus ada setidaknya 1 objek");
        System.out.println(response.getBody().asPrettyString());
    }
}
