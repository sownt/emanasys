package com.vosxvo.controllers;

import com.vosxvo.services.model.Department;
import com.vosxvo.services.thread.TableThread;
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
import java.util.ResourceBundle;

public class DepartmentsController extends Dataflow implements Initializable {

    @FXML
    private TableView<Department> table;

    @FXML
    private TableColumn<Department, Integer> id;

    @FXML
    private TableColumn<Department, String> name;

    public static void show() throws IOException {
        MainController controller = (MainController) Controller.CONTROLLER_MANAGE.get(Controller.MAIN_CONTROLLER);
        controller.setMainPane(MainController.class.getResource("/com/vosxvo/views/manage_departments.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.CONTROLLER_MANAGE.add(Controller.DEPARTMENTS_CONTROLLER, this);
        NavigatorController.setCurrent(this);

        id.setCellValueFactory(new PropertyValueFactory<>("dept_no"));
        name.setCellValueFactory(new PropertyValueFactory<>("dept_name"));

        TableThread<Department> thread = new TableThread<>(Controller.DEPARTMENTS_CONTROLLER);
        thread.start();
    }

    @Override
    public Dataflow getDataflow() {
        return this;
    }

    @Override
    public String getTable() {
        return Department.TABLE;
    }

    @Override
    public String getAttributes() {
        return Department.ATTRIBUTES;
    }

    @Override
    public ObservableList extract(ResultSet sets) throws SQLException {
        return Department.extract(sets);
    }

    @Override
    public void navigateAction() {
        TableThread<Department> thread = new TableThread<>(Controller.DEPARTMENTS_CONTROLLER);
        thread.start();
    }

    @Override
    public void update(ObservableList list, int records) {
        super.update(list, records);
        table.setItems(list);
    }
}
