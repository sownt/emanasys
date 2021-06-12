package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.User;
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
import java.util.ResourceBundle;

public class UsersController extends Dataflow implements Initializable {

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> username;

    @FXML
    private TableColumn<User, String> hashpass;

    public static void show() throws IOException {
        ControllerManage controllerManage = ControllerManage.getInstance();
        MainController controller = (MainController) controllerManage.get(Controller.MAIN_CONTROLLER);
        controller.setMainPane(MainController.class.getResource("/com/vosxvo/res/views/manage_users.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManage manage = ControllerManage.getInstance();
        manage.add(Controller.USERS_CONTROLLER, this);
        NavigatorController.setCurrent(this);

        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        hashpass.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableThread<User> thread = new TableThread<>(Controller.USERS_CONTROLLER);
        thread.start();
    }

    @Override
    public Dataflow getDataflow() {
        return this;
    }

    @Override
    public String getTable() {
        return User.TABLE;
    }

    @Override
    public String getAttributes() {
        return User.ATTRIBUTES;
    }

    @Override
    public ObservableList extract(ResultSet sets) throws SQLException {
        return User.extract(sets);
    }

    @Override
    public void navigateAction() {
        TableThread<User> thread = new TableThread<>(Controller.USERS_CONTROLLER);
        thread.start();
    }

    @Override
    public void update(ObservableList list, int records) {
        super.update(list, records);
        table.setItems(list);
    }
}
