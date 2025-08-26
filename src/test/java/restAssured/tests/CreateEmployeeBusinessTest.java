package restAssured.tests;

import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import restAssured.enitites.EmployeeRequest;
import restAssured.enitites.EmployeeResponse;
import restAssured.helper.AuthHelper;
import restAssured.helper.EmployeeHelper;
import restAssured.helper.EmployeeHelperDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateEmployeeBusinessTest {

    private static EmployeeHelper employeeHelper;
    private static EmployeeHelperDB employeeHelperDB;

    @BeforeAll
    public static void setUri() throws SQLException, IOException {
        baseURI = "https://innopolispython.onrender.com";
        employeeHelper = new EmployeeHelper();
        employeeHelperDB = new EmployeeHelperDB();
    }

    @Test
    @DisplayName("Создание сотрудника")
    public void createEmployee() throws Exception {
            int employeeId = employeeHelper.createEmployee(new EmployeeRequest("Moscow", "Igor", "Igorin", "IT"));
            EmployeeResponse employee = employeeHelperDB.getEmployee(employeeId); // ИСПОЛЬЗОВАТЬ БД
            // Преимущества использования БД
            // 1. Стабильность
            // 2. Скорее всего запрос напрямую к БД быстрее
            // Недостатки использования БД
            // 1. Иногда один эндпойнт выполняет не просто создание объекта в одной таблице, а может запускать целую цепочку событий
            // 2. Может не быть доступа к БД
            // 3. Подключение к БД и написание запросов может быть сложнее чем отправка API запроса
            // 4. Процессы могут меняться. Сегодня для создания юзера надо создать данные в одной таблице, а завтра сразу в двух

            assertEquals(employeeId, employee.getId());
    }

    @Test
    @DisplayName("Создание сотрудника с пустым полем")
    public void createEmployeeWithEmptyBody() throws Exception {
        int employeeId = employeeHelper.createEmployee(new EmployeeRequest());
        EmployeeResponse employee = employeeHelperDB.getEmployee(employeeId);
        assertEquals(employee.getId(), 0);
        assertNull(employee.getName());
        assertEquals(-1, employeeId);
    }
}
