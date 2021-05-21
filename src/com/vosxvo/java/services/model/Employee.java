package com.vosxvo.java.services.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String gender;
    private Date hiredDate;
    private String department;

    public Employee(int id, String firstName, String lastName, Date birthday, String gender, Date hiredDate, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.hiredDate = hiredDate;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public Date getHiredDate() {
        return hiredDate;
    }

    public String getDepartment() {
        return department;
    }

    public static ObservableList<Employee> extract(ResultSet sets) throws SQLException {
        if (sets == null) return null;
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            while (sets.next()) {
                int id = sets.getInt(1);
                String firstName = sets.getString(2);
                String lastName = sets.getString(3);
                java.sql.Date birthday = sets.getDate(4);
                String gender = sets.getString(5);
                java.sql.Date hiredDate = sets.getDate(6);
                String department = sets.getString(7);
                list.add(new Employee(id, firstName, lastName, birthday, gender, hiredDate, department));
            }
        } finally {
            sets.close();
        }
        return list;
    }
}
