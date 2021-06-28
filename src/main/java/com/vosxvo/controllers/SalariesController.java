package com.vosxvo.controllers;

import com.vosxvo.services.model.Salary;
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
import java.util.Date;
import java.util.ResourceBundle;

public class SalariesController extends Dataflow implements Initializable {

    @FXML
    private TableView<Salary> table;

    @FXML
    private TableColumn<Salary, Integer> id;

    @FXML
    private TableColumn<Salary, Integer> salary;

    @FXML
    private TableColumn<Salary, Date> from;

    @FXML
    private TableColumn<Salary, Date> to;

    public static void show() throws IOException {
        ControllerManage controllerManage = ControllerManage.getInstance();
        MainController controller = (MainController) controllerManage.get(Controller.MAIN_CONTROLLER);
        controller.setMainPane(MainController.class.getResource("/com/vosxvo/views/manage_salaries.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManage manage = ControllerManage.getInstance();
        manage.add(Controller.SALARIES_CONTROLLER, this);
        NavigatorController.setCurrent(this);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableThread<Salary> thread = new TableThread<>(Controller.SALARIES_CONTROLLER);
        thread.start();
    }

    @Override
    public Dataflow getDataflow() {
        return this;
    }

    @Override
    public String getTable() {
        return Salary.TABLE;
    }

    @Override
    public String getAttributes() {
        return Salary.ATTRIBUTES;
    }

    @Override
    public ObservableList extract(ResultSet sets) throws SQLException {
        return Salary.extract(sets);
    }

    @Override
    public void navigateAction() {
        TableThread<Salary> thread = new TableThread<>(Controller.SALARIES_CONTROLLER);
        thread.start();
    }

    @Override
    public void update(ObservableList list, int records) {
        super.update(list, records);
        table.setItems(list);
    }
}
