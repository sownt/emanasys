package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.Employee;
import com.vosxvo.java.services.sql.MySQLHandle;
import com.vosxvo.java.services.thread.EmployeeData;
import com.vosxvo.java.services.thread.SQLThread;
import com.vosxvo.java.services.thread.ThreadHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable {
    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, Integer> id;

    @FXML
    private TableColumn<Employee, String> firstName;

    @FXML
    private TableColumn<Employee, String> lastName;

    @FXML
    private TableColumn<Employee, Date> birthday;

    @FXML
    private TableColumn<Employee, String> gender;

    @FXML
    private TableColumn<Employee, Date> hiredDate;

    @FXML
    private TableColumn<Employee, String> department;

    public void onMouseClickedListener(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        hiredDate.setCellValueFactory(new PropertyValueFactory<>("HiredDate"));
        department.setCellValueFactory(new PropertyValueFactory<>("Department"));
        SQLThread thread = new SQLThread(this);
        thread.start();
        table.setItems(thread.getList());
    }

    public void update(ObservableList list) {
        table.setItems(list);
    }
}
