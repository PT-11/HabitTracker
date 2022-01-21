package com.example.habittracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static final String jdbcURL = "jdbc:postgresql://localhost:5432/test";
    private static final String username = "pt";
    private static final String password = "";

    public DataSource() {

    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to the server");

            //connection.close();
        } catch (SQLException e) {
            System.out.println("Error in connecting to database");
            e.printStackTrace();
        }
        return connection;
    }
}
