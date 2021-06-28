/**
 *
 * @author Tran Thai Son
 */
package com.vosxvo.services.database;

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
            // TODO : Write exception to log
            exception.printStackTrace();
        }
        return connection;
    }
}
