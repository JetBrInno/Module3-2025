package restAssured.tests;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import restAssured.enitites.EmployeeRequest;
import restAssured.enitites.EmployeeResponse;
import restAssured.helper.AuthHelper;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateEmployeeRequestContractTest {

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
    @DisplayName("Создание сотрудника")
    public void createEmployeeCode200(){
        String token = authHelper.getToken("admin", "admin");
        given()
                .body(new EmployeeRequest("Moscow", "Alex", "IT", "Krytoi"))
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token).
        when().
                post("/employee").
        then().
                statusCode(201);
        }

    @Disabled("Баг - не удается создать юзера с обяз. полями")
    @Test
    @DisplayName("Создание сотрудника только с обяз. полями")
    public void createEmployeeRequiredFields(){
        String token = authHelper.getToken("admin", "admin");
        given()
                .body(new EmployeeRequest("Alex", "IT", "Krytoi"))
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token).
        when().
                post("/employee").
        then().
                statusCode(201);
    }

    @Test
    @DisplayName("Создание сотрудника без обязательных полей")
    public void createEmployeeCode400(){
        String token = authHelper.getToken("admin", "admin");

        ExtractableResponse<Response> response = given()
                .body(new EmployeeRequest("Moscow"))
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token).
        when().
                post("/employee").
        then().
                statusCode(400).extract();

        String message = response.jsonPath().getString("error");
        assertEquals("Missing required fields", message);
    }

    @Test
    @DisplayName("2 вариант - Создание сотрудника без обязательных полей")
    public void createEmployeeCode400SECONDVARIANT(){
        String token = authHelper.getToken("admin", "admin");

        given()
                .body(new EmployeeRequest("Moscow"))
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token).
        when().
                post("/employee").
        then().
                statusCode(400).body("error", is(equalTo("Missing required fields")))
        .body("missing_fields", hasItems("name", "surname", "position"));

    }

    @Test
    @DisplayName("3 вариант - Создание сотрудника без обязательных полей")
    public void createEmployeeCode400THIRDVARIANT(){
        String token = authHelper.getToken("admin", "admin");

        ExtractableResponse<Response> response = given()
                .body(new EmployeeRequest("Moscow"))
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token).
                when().
                post("/employee").
                then().
                statusCode(400).extract();

        List<String> misFields = response.jsonPath().getList("missing_fields");
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(misFields.get(0)).isEqualTo("name123");
            softAssertions.assertThat(misFields.get(1)).isEqualTo("surna241241me");
            softAssertions.assertThat(misFields.get(2)).isEqualTo("posi42141241tion");
        });
    }




    @Test
    @DisplayName("Создание сотрудника без авторизации")
    public void createEmployeeCode401(){
        given()
                .body(new EmployeeRequest("Moscow", "Alex", "IT", "Krytoi"))
                .contentType(ContentType.JSON).
        when().
                post("/employee").
        then().
                statusCode(401);
    }
}
