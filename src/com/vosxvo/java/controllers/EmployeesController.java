package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.Employee;
import com.vosxvo.java.services.thread.TableThread;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeesController extends Dataflow implements Initializable {

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

    public static void show() throws IOException {
        MainController controller = (MainController) Controller.CONTROLLER_MANAGE.get(Controller.MAIN_CONTROLLER);
        controller.setMainPane(MainController.class.getResource("/com/vosxvo/res/views/manage_employees.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.CONTROLLER_MANAGE.add(Controller.EMPLOYEES_CONTROLLER, this);
        NavigatorController.setCurrent(this);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        hiredDate.setCellValueFactory(new PropertyValueFactory<>("hiredDate"));

        TableThread<Employee> thread = new TableThread<>(Controller.EMPLOYEES_CONTROLLER);
        thread.start();
    }

    @Override
    public Dataflow getDataflow() {
        return this;
    }

    @Override
    public String getTable() {
        return Employee.TABLE;
    }

    @Override
    public String getAttributes() {
        return Employee.ATTRIBUTES;
    }

    @Override
    public ObservableList<Employee> extract(ResultSet sets) throws SQLException {
        return Employee.extract(sets);
    }

    @Override
    public void navigateAction() {
        TableThread<Employee> thread = new TableThread<>(Controller.EMPLOYEES_CONTROLLER);
        thread.start();
    }

    @Override
    public void update(ObservableList list, int records) {
        super.update(list, records);
        table.setItems(list);
    }
}
