package com.vosxvo.java.services.model;

import java.util.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String gender;
    private Date hiredDate;
    private String department;

    public User(int id, String firstName, String lastName, Date birthday, String gender, Date hiredDate, String department) {
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
}
