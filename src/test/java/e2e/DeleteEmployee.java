package e2e;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DeleteEmployee {

    @Test(dependsOnGroups = "assertEmployeeUpdate")
    public void DeleteEmployee() {
        // System.out.println("DeleteEmployee running...");

        Response res = RestAssured
                .given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + StaticVar.token)
                .log().all()
                .delete(StaticVar.BASE_URL + "/employee/delete");

        System.out.println(res.asPrettyString());

        assertEquals(res.statusCode(), 200, "Status code delete employee must be 200");
        String message = res.jsonPath().getString("message");
        assertEquals(message.toLowerCase(), "employee deleted successfully", "Message not matched");
    }
}
