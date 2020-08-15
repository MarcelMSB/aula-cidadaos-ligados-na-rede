package com.exemplo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "ADMIN";

    public static Connection getCon() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
