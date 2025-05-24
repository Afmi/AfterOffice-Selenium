package e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pojo.LoginRequest;
import pojo.RegisterRequest;
import pojo.RegisterResponse;

import static org.testng.Assert.*;

public class RegisterEmployee {
    @BeforeSuite
    public void beforeSuite() {
        // System.out.println("Suite E2E running...");

        StaticVar.email = "Budi1@mail.com";
        StaticVar.password = "password";
        StaticVar.fullName = "Budi";
        StaticVar.department = "Finance";
        StaticVar.title = "QA";
    }

    @Test
    public void addEmployee() throws Exception {
        // System.out.println("addEmployee starting....");

        RegisterRequest req = new RegisterRequest(
                StaticVar.email,
                StaticVar.password,
                StaticVar.fullName,
                StaticVar.department,
                StaticVar.title);

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(req);

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log().all()
                .post(StaticVar.BASE_URL + "/employee/add");

        System.out.println(res.asPrettyString());

        assertEquals(res.statusCode(), 200, "Status code add employee must be 200");

        RegisterResponse[] responseBody = mapper.readValue(res.asString(), RegisterResponse[].class);
        assertEquals(responseBody[0].email, StaticVar.email);
        assertEquals(responseBody[0].full_name, StaticVar.fullName);
        assertEquals(responseBody[0].department, StaticVar.department);
        assertEquals(responseBody[0].title, StaticVar.title);
    }

    @Test(dependsOnMethods = "addEmployee")
    public void loginEmployee() throws Exception {
        System.out.println("loginEmployee starting....");

        LoginRequest loginReq = new LoginRequest(StaticVar.email, StaticVar.password);
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(loginReq);

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .log().all()
                .post(StaticVar.BASE_URL + "/employee/login");

        System.out.println(res.asPrettyString());

        assertEquals(res.statusCode(), 200, "Status code login employee must be 200");
        StaticVar.token = res.jsonPath().getString("[0].token");
        assertNotNull(StaticVar.token, "Token is null");
        assertFalse(StaticVar.token.isEmpty(), "Token is empty");
    }

    @Test(dependsOnMethods = "loginEmployee", groups = "assertEmployeeRegister")
    public void searchEmployee() {
        // System.out.println("searchEmployee starting....");

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .log().all()
                .get(StaticVar.BASE_URL + "/41a9698d-d8b0-42df-9ddc-89c0a1a1aa79/employee/search/"
                        + StaticVar.fullName);

        System.out.println(res.asPrettyString());

        assertEquals(res.statusCode(), 200, "Status code search employee must be 200");
        assertTrue(res.jsonPath().getString("[0].query").equals(StaticVar.fullName), "Query must match full name");
        assertTrue(res.jsonPath().getString("[0].result.full_name").contains(StaticVar.fullName),
                "Full name not matched");
    }

    @Test(dependsOnMethods = "loginEmployee", groups = "assertEmployeeRegister")
    public void getAllEmployee() {
        // System.out.println("getAllEmployee starting....");

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .log().all()
                .get(StaticVar.BASE_URL + "/employee/get_all");

        System.out.println(res.asPrettyString());

        assertEquals(res.statusCode(), 200, "Status code get all employee must be 200");

        boolean dataIsFound = res.jsonPath().getList("full_name").contains(StaticVar.fullName);
        assertTrue(dataIsFound, "Data not found in system");
    }
}
