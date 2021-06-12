package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.Title;
import com.vosxvo.java.services.thread.TableThread;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TitlesController extends Dataflow implements Initializable {

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

    public static void show() throws IOException {
        ControllerManage controllerManage = ControllerManage.getInstance();
        MainController controller = (MainController) controllerManage.get(Controller.MAIN_CONTROLLER);
        controller.setMainPane(MainController.class.getResource("/com/vosxvo/res/views/manage_titles.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManage manage = ControllerManage.getInstance();
        manage.add(Controller.TITLES_CONTROLLER, this);
        NavigatorController.setCurrent(this);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        from.setCellValueFactory(new PropertyValueFactory<>("from"));
        to.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableThread<Title> thread = new TableThread<>(Controller.TITLES_CONTROLLER);
        thread.start();
    }

    @Override
    public Dataflow getDataflow() {
        return this;
    }

    @Override
    public String getTable() {
        return Title.TABLE;
    }

    @Override
    public String getAttributes() {
        return Title.ATTRIBUTES;
    }

    @Override
    public ObservableList extract(ResultSet sets) throws SQLException {
        return Title.extract(sets);
    }

    @Override
    public void navigateAction() {
        TableThread<Title> thread = new TableThread<>(Controller.TITLES_CONTROLLER);
        thread.start();
    }

    @Override
    public void update(ObservableList list, int records) {
        super.update(list, records);
        table.setItems(list);
    }
}
