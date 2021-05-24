package com.vosxvo.java.services.thread;

import com.vosxvo.java.controllers.SalariesController;
import com.vosxvo.java.services.file.Configuration;
import com.vosxvo.java.services.model.Salary;
import com.vosxvo.java.services.sql.MySQLHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalaryThread extends Thread {
    private ObservableList<Salary> list;
    private SalariesController controller;
    private int offset;

    public SalaryThread(SalariesController controller, int offset) {
        this.controller = controller;
        this.offset = offset;
    }

    public ObservableList<Salary> getList() {
        return list;
    }

    @Override
    public void run() {
        list = FXCollections.observableArrayList();
        Connection connection = null;
        Statement statement = null;
        try {
            MySQLHandle handle = new MySQLHandle(Configuration.load());
            connection = handle.getConnection();
            statement = connection.createStatement();
            ResultSet sets = statement.executeQuery(String.format("SELECT `emp_no`, `salary`, `from_date`, `to_date` FROM `salaries` LIMIT %d OFFSET %d;", 25, offset));
            list = Salary.extract(sets);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            controller.update(list);
        }
    }
}
