package com.vosxvo.java.controllers;

import com.vosxvo.java.services.file.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.File;

/**
 *
 * /opt/java/jdk-15.0.2/bin/java
 * --module-path /opt/java/javafx-sdk-16/lib/
 * --add-modules javafx.controls,javafx.fxml
 * --add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
 * -Djava.library.path=/opt/java/javafx-sdk-16/lib
 * -javaagent:/opt/idea/lib/idea_rt.jar=42633:/opt/idea/bin
 * -Dfile.encoding=UTF-8
 * -classpath /home/sn/src/java/employee-management-system/out/production/employee-management-system:
 * /opt/java/javafx-sdk-16/lib/src.zip:/opt/java/javafx-sdk-16/lib/javafx-swt.jar:
 * /opt/java/javafx-sdk-16/lib/javafx.web.jar:/opt/java/javafx-sdk-16/lib/javafx.base.jar:
 * /opt/java/javafx-sdk-16/lib/javafx.fxml.jar:/opt/java/javafx-sdk-16/lib/javafx.media.jar:
 * /opt/java/javafx-sdk-16/lib/javafx.swing.jar:/opt/java/javafx-sdk-16/lib/javafx.controls.jar:
 * /opt/java/javafx-sdk-16/lib/javafx.graphics.jar:/opt/java/jdk-15.0.2:
 * /opt/java/mysql-connector-java-8.0.24.jar com.vosxvo.java.controllers.Main
 *
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        File file = new File(Configuration.CONFIGURATION_FILE);
//        if (file.exists()) {
//            LoginController.show(stage);
//        } else {
//            DatabaseController.show(stage);
//        }
        HomePageController.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
