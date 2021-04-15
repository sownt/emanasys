package com.vosxvo.java.services.database;

public class MySQLConfiguration {
    private String host;
    private String database;
    private String username;
    private String password;

    public MySQLConfiguration(String host, String database, String username, String password) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public String getDatabase() {
        return database;
    }
}
