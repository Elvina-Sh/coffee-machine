package com.lab;

import java.sql.*;

public class ConnectionSource {

    static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:coffee.db";


    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void connection() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL);
        statement = connection.createStatement();
    }

    public static void closeDB() throws SQLException {
        connection.close();
        statement.close();
        resultSet.close();
    }
}
