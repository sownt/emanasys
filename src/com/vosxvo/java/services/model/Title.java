package com.vosxvo.java.services.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Title {
    private int id;
    private String title;
    private Date from;
    private Date to;

    public Title(int id, String title, Date from, Date to) {
        this.id = id;
        this.title = title;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public static ObservableList<Title> extract(ResultSet sets) throws SQLException {
        if (sets == null) return null;
        ObservableList<Title> list = FXCollections.observableArrayList();
        try {
            while (sets.next()) {
                int id = sets.getInt(1);
                String title = sets.getString(2);
                Date from = sets.getDate(3);
                Date to = sets.getDate(4);
                list.add(new Title(id, title, from, to));
            }
        } finally {
            sets.close();
        }
        return list;
    }
}
