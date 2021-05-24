package com.vosxvo.java.services.thread;

import com.vosxvo.java.controllers.DepartmentsController;
import com.vosxvo.java.services.file.Configuration;
import com.vosxvo.java.services.model.Department;
import com.vosxvo.java.services.model.Employee;
import com.vosxvo.java.services.sql.MySQLHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentThread extends Thread {
    private ObservableList<Department> list;
    private DepartmentsController controller;
    private int offset;

    public DepartmentThread(DepartmentsController controller, int offset) {
        this.controller = controller;
        this.offset = offset;
    }

    public ObservableList<Department> getList() {
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
            ResultSet sets = statement.executeQuery(String.format("SELECT `dept_no`, `dept_name` FROM `departments` LIMIT %d OFFSET %d;", 25, offset));
            list = Department.extract(sets);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            controller.update(list);
        }
    }
}
