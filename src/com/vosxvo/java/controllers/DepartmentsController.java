package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.Department;
import com.vosxvo.java.services.thread.DepartmentThread;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentsController implements Initializable, Controller {
    @FXML
    private TableView<Department> table;

    @FXML
    private TableColumn<Department, Integer> id;

    @FXML
    private TableColumn<Department, String> name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("dept_no"));
        name.setCellValueFactory(new PropertyValueFactory<>("dept_name"));
        DepartmentThread thread = new DepartmentThread(this, 0);
        thread.start();
    }

    @Override
    public void update(Object o) {
        if (o instanceof ObservableList) {
            table.setItems((ObservableList<Department>) o);
        }
    }
}
