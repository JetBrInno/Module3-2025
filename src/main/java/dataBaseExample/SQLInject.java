package dataBaseExample;

import java.sql.*;
import java.util.Scanner;

public class SQLInject {
    private static Connection connection;
    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        Scanner scanner = new Scanner(System.in);
        String city = scanner.nextLine();
        ResultSet resultSet = getEmployeeByCity(city);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("surname"));
            System.out.println("_________________________");
        }
    }

    public static Connection getConnection() throws SQLException {
        String connectionString = "jdbc:postgresql://dpg-d24eoe7gi27c73dh3720-a.oregon-postgres.render.com/employee_postgres";
        String username = "employee_postgres_user";
        String password = "czXR22kYs1A3p2o7APqiwDRuQKp9xyJc";
        return DriverManager.getConnection(connectionString, username, password);
    }

    public static ResultSet getEmployeeByCity(String city) throws SQLException {
        String SELECT_NAME_SURNAME = "SELECT name, surname FROM employee where city = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAME_SURNAME);
        preparedStatement.setString(1, city);

        return preparedStatement.executeQuery();
    }
}
