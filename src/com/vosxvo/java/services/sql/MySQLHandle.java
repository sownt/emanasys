package com.vosxvo.java.services.sql;

import com.vosxvo.java.services.database.*;
import java.sql.Connection;
import java.sql.SQLException;

public class MySQLHandle {
    private String host;
    private String database;
    private String username;
    private String password;
    private Connection connection;

    /**
     * Constructor
     */
    public MySQLHandle(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public MySQLHandle(String host, String database, String username, String password) throws SQLException {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        disconnect();
        connect();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setDatabase(String database) throws SQLException {
        this.database = database;
        disconnect();
        connect();
    }

    /**
     * Connect to MySQL Database
     * @throws SQLException
     */
    public void connect() throws SQLException {
        if (connection != null) connection.close();
        if (database == null || database == "")
            throw new SQLException();

        MySQLServices services = new MySQLServices();
        connection = services.connectTo(host + "/" + database, username, password);
    }

    public void connect(String database) throws SQLException {
        if (connection != null) connection.close();
        MySQLServices services = new MySQLServices();
        connection = services.connectTo(host + "/" + database, username, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null) return;
        connection.close();
    }
}