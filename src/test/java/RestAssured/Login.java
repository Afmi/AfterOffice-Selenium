package RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

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
        List<Object> objects = response.jsonPath().getList("$");
        assertNotNull(objects, "List objek tidak boleh null");
        assertTrue(objects.size() > 0, "Harus ada setidaknya 1 objek");
    }
}
