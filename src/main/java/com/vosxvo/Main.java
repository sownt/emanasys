package com.vosxvo;

import com.vosxvo.controllers.ControllerManage;
import com.vosxvo.controllers.DatabaseController;
import com.vosxvo.controllers.LoginController;
import com.vosxvo.services.file.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ControllerManage manage = ControllerManage.getInstance();
        manage.setGlobal(stage);
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
