package dataBaseExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseDemo {
    public static void main(String[] args) throws SQLException {
        String connectionString = "jdbc:postgresql://dpg-d24eoe7gi27c73dh3720-a.oregon-postgres.render.com/employee_postgres";
        String username = "employee_postgres_user";
        String password = "czXR22kYs1A3p2o7APqiwDRuQKp9xyJc";

        String SELECT_NAME_SURNAME = "SELECT name, surname FROM employee limit 10;";
        Connection connection = DriverManager.getConnection(connectionString, username, password);
        ResultSet resultSet = connection.createStatement().executeQuery(SELECT_NAME_SURNAME);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("surname"));
            System.out.println("_________________________");
        }
    }
}
