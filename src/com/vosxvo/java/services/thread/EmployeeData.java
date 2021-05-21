package com.vosxvo.java.services.thread;

import com.vosxvo.java.services.model.Employee;
import com.vosxvo.java.services.sql.MySQLHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeData implements Runnable {
    private ObservableList<Employee> list;
    private int from;
    private int to;

    public EmployeeData(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public ObservableList<Employee> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "Employees " + from + " - " + to;
    }

    @Override
    public void run() {
        Connection connection = null;
        Statement statement = null;
        list = FXCollections.observableArrayList();
        try {
            MySQLHandle handle = new MySQLHandle("172.17.0.2", "vo", "root", "010118");
            connection = handle.getConnection();
            statement = connection.createStatement();
            ResultSet sets = statement.executeQuery(String.format("SELECT `id`, `first_name`, `last_name`, `birthday`, `gender`, `hire_date`, `department` FROM `vo`.`vo_employee` LIMIT %d OFFSET %d;", from + to, from));

        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
