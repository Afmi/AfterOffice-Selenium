package e2e;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

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

        assert res.getStatusCode() == 200 : "Status code add employee must be 200";
        assert res.jsonPath().getBoolean("[0].success") == true : "Delete not success";
    }
}
