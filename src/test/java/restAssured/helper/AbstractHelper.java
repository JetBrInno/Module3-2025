package restAssured.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class AbstractHelper {

    protected Connection connection;

    public Connection getConnection() throws SQLException {
        String connectionString = "jdbc:postgresql://dpg-d24eoe7gi27c73dh3720-a.oregon-postgres.render.com/employee_postgres";
        String username = "employee_postgres_user";
        String password = "czXR22kYs1A3p2o7APqiwDRuQKp9xyJc";
        return DriverManager.getConnection(connectionString, username, password);
    }
}
