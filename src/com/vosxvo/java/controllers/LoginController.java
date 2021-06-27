package com.vosxvo.java.controllers;

import com.vosxvo.java.services.file.Configuration;
import com.vosxvo.java.services.sql.MySQLHandle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    public TextField username;
    public PasswordField password;
    public Button forgotPassword;
    public Button signIn;
    public Button createAccount;
    public static Stage current;

    public static void show(Stage stage) {
        Scene scene = null;
        LoginController.current = stage;
        try {
            scene = new Scene(FXMLLoader.load(LoginController.class.getResource("/com/vosxvo/res/views/login.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("eManasys - Login");
        stage.setScene(scene);
        stage.show();
    }

    private static boolean isValidCrd(String username, String password) {
        if (username == null || password == null) return false;
        Connection connection = null;
        Statement statement = null;
        boolean isValid = false;
        try {
//            MySQLHandle handle = new MySQLHandle("172.17.0.2", "vo", "root", "010118");
            MySQLHandle handle = new MySQLHandle(Configuration.load());
            connection = handle.getConnection();
            statement = connection.createStatement();
            ResultSet crd = statement.executeQuery("SELECT users.username, users.password FROM users;");
            if (crd != null) {
                crd.next();
                isValid = (crd.getString("username").equals(username)) && (crd.getString("password").equals(password));
                crd.close();
            }
        } catch (SQLException | IOException exception) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return isValid;
    }

    private boolean signIn() {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username == null || password == null || username.equals("") || password.equals("")) {
            return false;
        }

        if (isValidCrd(username, password)) {
            // TODO : Do somethings
            try {
                MainController.show(Controller.CONTROLLER_MANAGE.getGlobal());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public void setOnMouseClicked(MouseEvent mouseEvent) {
        Object source = mouseEvent.getSource();
        if (source.equals(signIn)) {
            signIn();
        }
    }


    public void onEnterPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            signIn();
        }
    }
}
