package restAssured.helper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restAssured.enitites.EmployeeRequest;
import restAssured.enitites.EmployeeResponse;
import restAssured.enitites.User;

import static io.restassured.RestAssured.given;

public class EmployeeHelper {
    private final AuthHelper authHelper;

    public EmployeeHelper(){
        authHelper = new AuthHelper();
        RestAssured.baseURI = "https://innopolispython.onrender.com";
    }

    public int createEmployee(EmployeeRequest employee) {
        String token = authHelper.getToken("admin", "admin");

        JsonPath jsonPath = given()
                .body(employee)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token).
                when().
                post("/employee").jsonPath();
        try {
            return jsonPath.getInt("id");
        }
        catch (NullPointerException nullPointerException) {
            return -1;
        }
    }

    public EmployeeResponse getEmployee(int id) {
        Response response = given().
                when().
                get("/employee/" + id);
        try {
            return response.as(EmployeeResponse.class);
        }
        catch (IllegalStateException exception) {
            return new EmployeeResponse();
        }

    }

    public void deleteEmployee(int id) {
            given().
                    when().
            delete("/employee/" + id);
    }
}
