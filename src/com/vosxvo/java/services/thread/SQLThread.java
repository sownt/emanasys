package com.vosxvo.java.services.thread;

import com.vosxvo.java.controllers.ManageEmployeeController;
import com.vosxvo.java.services.model.Employee;
import com.vosxvo.java.services.sql.MySQLHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLThread extends Thread {
    private String sql;
    private ObservableList<Employee> list;
    private ManageEmployeeController controller;

    public SQLThread(ManageEmployeeController controller) {
        this.controller = controller;
    }

    public ObservableList<Employee> getList() {
        return list;
    }

    @Override
    public void run() {
        int from = 0, to = 10;
        list = FXCollections.observableArrayList();
        Connection connection = null;
        Statement statement = null;
        try {
            MySQLHandle handle = new MySQLHandle("172.17.0.2", "vo", "root", "010118");
            connection = handle.getConnection();
            statement = connection.createStatement();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ResultSet sets = statement.executeQuery(String.format("SELECT `id`, `first_name`, `last_name`, `birthday`, `gender`, `hire_date`, `department` FROM `vo`.`vo_employee` LIMIT %d OFFSET %d;", from + to, from));
//            if (sets == null) return;
//            while (sets.next()) {
//                int id = sets.getInt(1);
//                String firstName = sets.getString(2);
//                String lastName = sets.getString(3);
//                java.sql.Date birthday = sets.getDate(4);
//                String gender = sets.getString(5);
//                java.sql.Date hiredDate = sets.getDate(6);
//                String department = sets.getString(7);
//                list.add(new Employee(id, firstName, lastName, birthday, gender, hiredDate, department));
//            }
//            sets.close();
            list = Employee.extract(sets);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            controller.update(list);
        }
    }

}
