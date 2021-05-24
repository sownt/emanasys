package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.Salary;
import com.vosxvo.java.services.thread.SalaryThread;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SalariesController implements Initializable, Controller {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("to"));
        SalaryThread thread = new SalaryThread(this, 0);
        thread.start();
        table.setItems(thread.getList());
    }

    @Override
    public void update(Object o) {
        if (o instanceof ObservableList) {
            table.setItems((ObservableList<Salary>) o);
        }
    }
}
