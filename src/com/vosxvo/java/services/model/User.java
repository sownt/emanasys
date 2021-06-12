package com.vosxvo.java.services.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User implements Model {
    public static final String TABLE = "`users`";
    public static final String ATTRIBUTES = "`emp_no`, `username`, `password`";

    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static String encrypt(String input) throws NoSuchAlgorithmException {
        MessageDigest algo = MessageDigest.getInstance("SHA-512");
        byte[] messages = algo.digest(input.getBytes());
        BigInteger integer = new BigInteger(1, messages);
        String hash = integer.toString();
        while (hash.length() < 32) {
            hash = "0" + hash;
        }
        return hash;
    }

    public static ObservableList<User> extract(ResultSet sets) throws SQLException {
        if (sets == null) return null;
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            while (sets.next()) {
                int id = sets.getInt(1);
                String username = sets.getString(2);
                String password = sets.getString(3);
                list.add(new User(id, username, encrypt(password)));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            sets.close();
        }
        return list;
    }
}
