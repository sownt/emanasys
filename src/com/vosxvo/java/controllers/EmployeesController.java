package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.Employee;
import com.vosxvo.java.services.thread.EmployeeThread;
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

public class EmployeesController implements Initializable, Controller {
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

    public void onMouseClickedListener(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        hiredDate.setCellValueFactory(new PropertyValueFactory<>("hiredDate"));
        EmployeeThread thread = new EmployeeThread(this, 0);
        thread.start();
        table.setItems(thread.getList());
    }

    @Override
    public void update(Object o) {
        if (o instanceof ObservableList) {
            table.setItems((ObservableList<Employee>) o);
        }
    }
}
