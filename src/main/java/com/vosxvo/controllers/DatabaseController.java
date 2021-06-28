package com.vosxvo.controllers;

import com.vosxvo.services.database.MySQLServices;
import com.vosxvo.services.file.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class DatabaseController {
    public TextField dbName;
    public TextField dbHost;
    public TextField dbPort;
    public TextField username;
    public PasswordField password;
    public TextField db;
    public TextField dbURL;
    public Button dbConnect;
    public static Stage current;

    public static void show(Stage stage) {
        DatabaseController.current = stage;
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(Objects.requireNonNull(DatabaseController.class.getResource("views/database.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("eManasys - Database Initialize");
        stage.setScene(scene);
        stage.show();
    }

    private void setDbURL(String host, String port, String database) {
        if (host.equals(""))
            throw new IllegalArgumentException();
        else if (port.equals("") && database.equals(""))
            dbURL.setText(String.format("%s%s", MySQLServices.MYSQL_PROTOCOL, host));
        else if (database.equals(""))
            dbURL.setText(String.format("%s%s:%d", MySQLServices.MYSQL_PROTOCOL, host, Integer.parseInt(port)));
        else if (port.equals(""))
            dbURL.setText(String.format("%s%s/%s", MySQLServices.MYSQL_PROTOCOL, host, database));
        else
            dbURL.setText(String.format("%s%s:%d/%s", MySQLServices.MYSQL_PROTOCOL, host, Integer.parseInt(port), database));
    }

    public void onChanged(KeyEvent keyEvent) {
        Object source = keyEvent.getSource();
        if (source.equals(dbHost) || source.equals(dbPort) || source.equals(db)) {
            setDbURL(dbHost.getText(), dbPort.getText(), db.getText());
        }
    }

    public void connectToDatabase(MouseEvent mouseEvent) {
        Properties props = new Properties();
        props.setProperty("host", dbHost.getText());
        props.setProperty("port", dbPort.getText());
        props.setProperty("database", db.getText());
        props.setProperty("username", username.getText());
        props.setProperty("password", password.getText());
        try {
            Configuration.save(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginController.show(DatabaseController.current);
    }
}
