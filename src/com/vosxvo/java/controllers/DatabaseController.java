package com.vosxvo.java.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class DatabaseController {
    public TextField dbName;
    public TextField dbHost;
    public TextField dbPort;
    public TextField username;
    public PasswordField password;
    public TextField db;
    public TextField dbURL;
    public Button dbConnect;

    public static void show(Stage stage) {
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(DatabaseController.class.getResource("../../res/views/database.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("Employee Manager - Database Initialize");
        stage.setScene(scene);
        stage.show();
    }

    public void onDatabaseChanged(KeyEvent keyEvent) {
    }

    public void connectToDatabase(MouseEvent mouseEvent) {
    }
}
