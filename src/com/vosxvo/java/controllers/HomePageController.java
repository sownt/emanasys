package com.vosxvo.java.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    public Button employees;
    public Button users;
    public Button salaries;
    public Button departments;
    public Button titles;
    public Button logout;
    public BorderPane mainPane;
    public static Stage current;

    public static void show(Stage stage) {
        Scene scene = null;
        current = stage;
        BorderPane pane = null;
        try {
            scene = new Scene(FXMLLoader.load(HomePageController.class.getResource("../../res/views/homepage.fxml")));
            pane = (BorderPane) scene.lookup("#mainPane");
            pane.setCenter(FXMLLoader.load(HomePageController.class.getResource("../../res/views/employees_manage.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Employee Manager - Home");
        stage.setScene(scene);
        stage.show();
    }

    private void show(Stage stage, String res, String tittle) {
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(HomePageController.class.getResource(res)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(tittle);
        stage.setScene(scene);
        stage.show();
    }

    public void onMouseClickedListener(MouseEvent mouseEvent) throws IOException {
        Object source = mouseEvent.getSource();
        Stage current = (Stage) employees.getScene().getWindow();
        if (source == null) return;
        if (source.equals(employees)) {
            mainPane.setCenter(FXMLLoader.load(HomePageController.class.getResource("../../res/views/employees_manage.fxml")));
        } else if (source.equals(users)) {
            mainPane.setCenter(FXMLLoader.load(HomePageController.class.getResource("../../res/views/users_manage.fxml")));
        } else if (source.equals(salaries)) {
            mainPane.setCenter(FXMLLoader.load(HomePageController.class.getResource("../../res/views/salaries_manage.fxml")));
        } else if (source.equals(departments)) {
            mainPane.setCenter(FXMLLoader.load(HomePageController.class.getResource("../../res/views/departments_manage.fxml")));
        } else if (source.equals(titles)) {
            mainPane.setCenter(FXMLLoader.load(HomePageController.class.getResource("../../res/views/titles_manage.fxml")));
        } else if (source.equals(logout)) {
            LoginController.show(HomePageController.current);
        }
    }
}
