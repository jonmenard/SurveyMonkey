package org.surveymonkey.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    // Static reference to itself
    private static ConnectionFactory instance = new ConnectionFactory();

    public static final String DRIVER_CLASS = "org.postgresql.Driver";
    public static final String URL = "jdbc:postgresql://ec2-3-222-11-129.compute-1.amazonaws.com:5432/d82th3769hut12";
    public static final String USER = "sisomxljckdenn";
    public static final String PASSWORD = "fad42bb61ccd74dce68fdb90f67b09e310b316a251a65c3766ec4e234609ed8c";



    // private constructor
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}
