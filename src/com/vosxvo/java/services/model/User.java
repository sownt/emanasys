package com.vosxvo.java.services.model;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String gender;
    private Date hiredDate;
    private String department;

    public User(int id, String username, String firstName, String lastName, Date birthday, String gender, Date hiredDate, String department) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.hiredDate = hiredDate;
        this.department = department;
    }
}
