package com.vosxvo.java.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    public Button dashboard;
    public Button activities;
    public Button issues;
    public Button projects;
    public Button users;

    public static void show(Stage stage) {
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(HomePageController.class.getResource("../../res/views/home_dashboard.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Employee Manager - Home");
        stage.setScene(scene);
        stage.show();
    }

    private void showUsers(Stage stage) {
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(HomePageController.class.getResource("../../res/views/home_users.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Employee Manager - Users");
        stage.setScene(scene);
        stage.show();
    }

    public void onMouseClickedListener(MouseEvent mouseEvent) {
        System.out.println("Mouse");
        Object source = mouseEvent.getSource();
        Stage current = (Stage) users.getScene().getWindow();
        if (source == null) return;
        if (source.equals(users)) {
            showUsers(current);
        }
    }
}
