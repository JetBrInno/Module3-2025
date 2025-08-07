package restAssured.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restAssured.enitites.EmployeeRequest;
import restAssured.enitites.EmployeeResponse;
import restAssured.helper.AuthHelper;
import restAssured.helper.EmployeeHelper;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isA;


public class GetEmployeeContractTest {

    private EmployeeHelper employeeHelper;

    private int createdEmployeeId;

    @BeforeAll
    public static void setUri(){
        baseURI = "https://innopolispython.onrender.com";
    }

    @BeforeEach
    public void setUp() {
        employeeHelper = new EmployeeHelper();
        EmployeeRequest employee = new EmployeeRequest("Moscow", "Alex", "IT", "Krytoi");
        createdEmployeeId = employeeHelper.createEmployee(employee);
    }

    @Test
    @DisplayName("Проверить типы параметров в ответе при получении списка сотрудников")
    public void getEmployeesResponseTypes(){
        given().
        when().
                get("/employee/" + createdEmployeeId).
        then().
                body("city", isA(String.class)).
                body("name", isA(String.class)).
                body("surname", isA(String.class)).
                body("position", isA(String.class));
    }

    @Test
    @DisplayName("Проверить типы параметров в ответе при получении списка сотрудников")
    public void getEmployeeResponseTypes2(){
        given().
                when().
                get("/employee/" + createdEmployeeId).
                as(EmployeeResponse.class);
    }
}
