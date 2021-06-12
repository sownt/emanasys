package com.vosxvo.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageMenuController extends Menu implements Initializable {
    @FXML
    private Button employees;

    @FXML
    private Button users;

    @FXML
    private Button salaries;

    @FXML
    private Button departments;

    @FXML
    private Button titles;

    @FXML
    private Button back;

    public static Button selected;

    @FXML
    void onMouseClickedListener(MouseEvent event) throws IOException {
        ControllerManage manage = ControllerManage.getInstance();
        Object source = event.getSource();
        if (source.equals(employees)) {
            EmployeesController.show();
        } else if (source.equals(users)) {
            UsersController.show();
        } else if (source.equals(salaries)) {
            SalariesController.show();
        } else if (source.equals(departments)) {
            DepartmentsController.show();
        } else if (source.equals(titles)) {
            TitlesController.show();
        } else if (source.equals(back)) {
            MainController.show(manage.getGlobal());
        }
        changeSelected(this, (Button) source);
    }

    @Override
    public Dataflow getDataflow() {
        return null;
    }

    @Override
    public Menu getMenu() {
        return null;
    }

    @Override
    public Navigator getNavigator() {
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        changeSelected(this, employees);
    }

    @Override
    public void onClickedListener(MouseEvent mouseEvent) {

    }

    @Override
    public Button getSelected() {
        return ManageMenuController.selected;
    }

    @Override
    public void setSelected(Button selected) {
        ManageMenuController.selected = selected;
    }
}
