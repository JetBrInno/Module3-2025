package restAssured.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restAssured.enitites.EmployeeRequest;
import restAssured.enitites.ValidationErrorResponse;
import restAssured.helper.AuthHelper;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UpdateEmployeeContractTest {


    private AuthHelper authHelper;

    @BeforeAll
    public static void setUri(){
        baseURI = "https://innopolispython.onrender.com";
    }

    @BeforeEach
    public void setUp(){
        authHelper = new AuthHelper();
    }

    @Test
    @DisplayName("Обновление сотрудника и проверка тела")
    public void updateEmployeeCheckBody(){
        String token = authHelper.getToken("admin", "admin");

        ValidationErrorResponse employee = given()
                .body(new EmployeeRequest("New York",  "Cleaner", "IvanOV"))
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token).
        when().
                put("/employee/" + "25").
        then().
                statusCode(400).extract().body().as(ValidationErrorResponse.class);

        System.out.println(employee.getMessage());
        System.out.println(employee.getError());
        System.out.println(employee.getWrong_type_fields());
    }
}
