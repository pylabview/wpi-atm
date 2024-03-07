package com.wpi.atm.model;

import java.sql.*;
import java.util.ArrayList;

/*
    JDBC class is used to interact with our MySQL Database to perform activities such as retrieving and updating our db
 */
public class MySQLConnection {
    // database configurations
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/atm";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Cucuta1983!@#$";

    public static void main(String[] args) throws SQLException {
        try {
                    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users"
            );
                    // execute query and store into a result set
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("holder"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}











