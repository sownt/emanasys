package com.vosxvo.java.controllers;

import com.vosxvo.java.services.model.User;
import com.vosxvo.java.services.sql.MySQLHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable {
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> id;

    @FXML
    private TableColumn<User, String> firstName;

    @FXML
    private TableColumn<User, String> lastName;

    @FXML
    private TableColumn<User, Date> birthday;

    @FXML
    private TableColumn<User, String> gender;

    @FXML
    private TableColumn<User, Date> hiredDate;

    @FXML
    private TableColumn<User, String> department;

    public void onMouseClickedListener(MouseEvent mouseEvent) {
    }

    public ObservableList<User> getData(int from, int to) {
        Connection connection = null;
        Statement statement = null;
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            MySQLHandle handle = new MySQLHandle("172.17.0.2", "vo", "root", "010118");
            connection = handle.getConnection();
            statement = connection.createStatement();
            ResultSet sets = statement.executeQuery(String.format("SELECT `id`, `first_name`, `last_name`, `birthday`, `gender`, `hire_date`, `department` FROM `vo`.`vo_employee` LIMIT %d OFFSET %d;", from + to, from));
//            ResultSet sets = statement.executeQuery("SELECT `id`, `first_name`, `last_name`, `birthday`, `gender`, `hire_date`, `department` FROM `vo`.`vo_employee`;");
            if (sets == null) return null;
            while (sets.next()) {
                int id = sets.getInt(1);
                String firstName = sets.getString(2);
                String lastName = sets.getString(3);
                java.sql.Date birthday = sets.getDate(4);
                String gender = sets.getString(5);
                java.sql.Date hiredDate = sets.getDate(6);
                String department = sets.getString(7);
                list.add(new User(id, firstName, lastName, birthday, gender, hiredDate, department));
            }
            sets.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
//        username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        birthday.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        hiredDate.setCellValueFactory(new PropertyValueFactory<>("HiredDate"));
        department.setCellValueFactory(new PropertyValueFactory<>("Department"));
        table.setItems(getData(0, 10));
    }
}
