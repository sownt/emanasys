package com.vosxvo.java.services.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLServices implements DatabaseServices {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String MYSQL_PROTOCOL = "jdbc:mysql://";

    @Override
    public Connection connectTo(String url, String username, String password) {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(MYSQL_PROTOCOL + url, username, password);
        } catch (ClassNotFoundException | SQLException exception) {
            // Write to log TODO
            exception.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        MySQLServices services = new MySQLServices();
        Connection connection = services.connectTo("172.17.0.2", "root", "010118");
        System.out.println(connection.isValid(100));
    }
}
