package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.Title;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class TitlesController implements Initializable, Controller {
    @FXML
    private TableView<Title> table;

    @FXML
    private TableColumn<Title, Integer> id;

    @FXML
    private TableColumn<Title, String> title;

    @FXML
    private TableColumn<Title, Date> from;

    @FXML
    private TableColumn<Title, Date> to;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("to"));

    }

    @Override
    public void update(Object o) {
        if (o instanceof ObservableList) {
            table.setItems((ObservableList<Title>) o);
        }
    }
}
