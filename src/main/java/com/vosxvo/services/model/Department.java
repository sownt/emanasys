package com.vosxvo.services.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department implements Model {
    public static final String TABLE = "`departments`";
    public static final String ATTRIBUTES = "`dept_no`, `dept_name`";

    private int dept_no;
    private String dept_name;

    public Department(int dept_no, String dept_name) {
        this.dept_no = dept_no;
        this.dept_name = dept_name;
    }

    public int getDept_no() {
        return dept_no;
    }

    public String getDept_name() {
        return dept_name;
    }

    public static ObservableList<Department> extract(ResultSet sets) throws SQLException {
        if (sets == null) return null;
        ObservableList<Department> list = FXCollections.observableArrayList();
        try {
            while (sets.next()) {
                int dept_no = sets.getInt(1);
                String dept_name = sets.getString(2);
                list.add(new Department(dept_no, dept_name));
            }
        } finally {
            sets.close();
        }
        return list;
    }
}
