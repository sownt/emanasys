package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable {
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> firstName;

    @FXML
    private TableColumn<User, String> lastName;

    @FXML
    private TableColumn<User, Date> birthday;

    @FXML
    private TableColumn<User, String> gender;

    @FXML
    private TableColumn<User, Date> hiredDate;

    @FXML
    private TableColumn<User, String> department;

    public void onMouseClickedListener(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        hiredDate.setCellValueFactory(new PropertyValueFactory<>("HiredDate"));
        department.setCellValueFactory(new PropertyValueFactory<>("Department"));
//        table.setItems(list);
    }
}
