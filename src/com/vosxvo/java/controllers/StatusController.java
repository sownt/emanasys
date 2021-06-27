package com.vosxvo.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StatusController {
    @FXML
    private ImageView icon;

    @FXML
    private Label status;

    public void setOnline() {
        icon.setImage(new Image(String.valueOf(MainController.class.getResource("/com/vosxvo/res/icons/outline_cloud_black_48dp.png"))));
    }
}
