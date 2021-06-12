package com.vosxvo.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends Menu implements Initializable {
    @FXML
    private Button dashboard;

    @FXML
    private Button manage;

    @FXML
    private Button notifications;

    @FXML
    private Button messages;

    @FXML
    private Button logout;

    public static Button selected;

//    @Override
//    public void changeSelected(Button selected) {
//        if (MainMenuController.selected != null) {
//            MainMenuController.selected.getStyleClass().removeAll("menu-current");
//            MainMenuController.selected.getStyleClass().add("menu-item");
//            MainMenuController.selected = selected;
//            MainMenuController.selected.getStyleClass().add("menu-current");
//        } else {
//            MainMenuController.selected = selected;
//            MainMenuController.selected.getStyleClass().add("menu-current");
//        }
//    }

    public void onClickedListener(MouseEvent mouseEvent) {
        Object source = mouseEvent.getSource();
        if (source.equals(dashboard)) {
            changeSelected(this, dashboard);
        } else if (source.equals(manage)) {
            try {
                MainController.manage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (source.equals(notifications)) {
            changeSelected(this, notifications);
        } else if (source.equals(messages)) {
            changeSelected(this, messages);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeSelected(this, dashboard);
    }

    @Override
    public Dataflow getDataflow() {
        return null;
    }

    @Override
    public Menu getMenu() {
        return null;
    }

    @Override
    public Navigator getNavigator() {
        return null;
    }

    @Override
    public Button getSelected() {
        return MainMenuController.selected;
    }

    @Override
    public void setSelected(Button selected) {
        MainMenuController.selected = selected;
    }
}
