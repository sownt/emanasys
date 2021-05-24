package com.vosxvo.java.services.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Salary {
    private int id;
    private int salary;
    private Date from;
    private Date to;

    public Salary(int id, int salary, Date from, Date to) {
        this.id = id;
        this.salary = salary;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public int getSalary() {
        return salary;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public static ObservableList<Salary> extract(ResultSet sets) throws SQLException {
        if (sets == null) return null;
        ObservableList<Salary> list = FXCollections.observableArrayList();
        try {
            while (sets.next()) {
                int id = sets.getInt(1);
                int salary = sets.getInt(2);
                Date from = sets.getDate(3);
                Date to = sets.getDate(4);
                list.add(new Salary(id, salary, from, to));
            }
        } finally {
            sets.close();
        }
        return list;
    }
}
