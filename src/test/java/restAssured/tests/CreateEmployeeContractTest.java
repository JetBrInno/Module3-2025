package restAssured.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restAssured.helper.AuthHelper;

public class CreateEmployeeContractTest {

    private AuthHelper authHelper;

    @BeforeEach
    public void setUp(){
        authHelper = new AuthHelper();
    }

    @Test
    @DisplayName("Создание сотрудника")
    public void createEmployeeCode200(){
        String token = authHelper.getToken("admin", "admin");
        System.out.println(token);
    }
}
