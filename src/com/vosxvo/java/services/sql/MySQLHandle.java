package com.vosxvo.java.services.sql;

import com.vosxvo.java.services.database.MySQLServices;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLHandle {
    private String url;
    private String username;
    private String password;
    private Connection connection;
    private Statement statement;

    public MySQLHandle() {
    }

    public MySQLHandle(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        if (connection == null) connection.close();
        MySQLServices services = new MySQLServices();
        connection = services.connectTo(url, username, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null) return;
        connection.close();
    }
}