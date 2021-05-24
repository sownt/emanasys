package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.Employee;
import com.vosxvo.java.services.model.User;
import com.vosxvo.java.services.thread.UserThread;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersController implements Initializable, Controller {
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> hashpass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        hashpass.setCellValueFactory(new PropertyValueFactory<>("password"));
        UserThread thread = new UserThread(this, 0);
        thread.start();
        table.setItems(thread.getList());
    }

    @Override
    public void update(Object o) {
        if (o instanceof ObservableList) {
            table.setItems((ObservableList<User>) o);
        }
    }
}
