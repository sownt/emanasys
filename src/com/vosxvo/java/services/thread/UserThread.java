package com.vosxvo.java.services.thread;

import com.vosxvo.java.controllers.UsersController;
import com.vosxvo.java.services.file.Configuration;
import com.vosxvo.java.services.model.Employee;
import com.vosxvo.java.services.model.User;
import com.vosxvo.java.services.sql.MySQLHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserThread extends Thread {
    private ObservableList<User> list;
    private UsersController controller;
    private int offset;

    public UserThread(UsersController controller, int offset) {
        this.controller = controller;
        this.offset = offset;
    }

    public ObservableList<User> getList() {
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
            ResultSet sets = statement.executeQuery(String.format("SELECT `emp_no`, `username`, `password` FROM `users` LIMIT %d OFFSET %d;", 25, offset));
            list = User.extract(sets);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            controller.update(list);
        }
    }
}
