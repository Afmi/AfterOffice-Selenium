package e2e;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.UpdateRequest;

import static org.testng.Assert.assertEquals;

public class UpdateEmployee {

    @Test(dependsOnGroups = "assertEmployeeRegister")
    public void UpdateEmployee() throws Exception {
        // System.out.println("UpdateEmployee running...");

        StaticVar.email = "budi-update1@mail.com";
        StaticVar.password = "password";
        StaticVar.fullName = "Budi";
        StaticVar.department = "Finance";
        StaticVar.title = "QA";

        UpdateRequest req = new UpdateRequest(
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
                .header("Authorization", "Bearer " + StaticVar.token)
                .body(body)
                .log().all()
                .put(StaticVar.BASE_URL + "/employee/update");

        System.out.println(res.asPrettyString());

        assertEquals(res.getStatusCode(), 200, "Status code update employee must be 200");
        assertEquals(res.jsonPath().getString("[0].email"), StaticVar.email);
        assertEquals(res.jsonPath().getString("[0].full_name"), StaticVar.fullName);
        assertEquals(res.jsonPath().getString("[0].department"), StaticVar.department);
        assertEquals(res.jsonPath().getString("[0].title"), StaticVar.title);
    }

    @Test(dependsOnMethods = "UpdateEmployee", groups = "assertEmployeeUpdate")
    public void searchEmployee() {
        new RegisterEmployee().searchEmployee();
    }

    @Test(dependsOnMethods = "UpdateEmployee", groups = "assertEmployeeUpdate")
    public void getAllEmployee() {
        new RegisterEmployee().getAllEmployee();
    }
}
