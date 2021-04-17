package com.vosxvo.java.controllers;

import com.vosxvo.java.services.file.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        File file = new File(Configuration.CONFIGURATION_FILE);
        if (file.exists()) {
            LoginController.show(stage);
        } else {
            DatabaseController.show(stage);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
