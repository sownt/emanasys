package com.vosxvo.java.services.thread;

import com.vosxvo.java.controllers.ControllerManage;
import com.vosxvo.java.controllers.Dataflow;
import com.vosxvo.java.controllers.NavigatorController;
import com.vosxvo.java.services.file.Configuration;
import com.vosxvo.java.services.sql.MySQLHandle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TableThread<E> extends Thread {
    private ObservableList<E> list;
    private Dataflow controller;
    private int records;

    public TableThread(int controller) {
        this.controller = ControllerManage.getInstance().get(controller).getDataflow();
    }

    public ObservableList<E> getList() {
        return list;
    }

    public void setList(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet sets = statement.executeQuery(String.format("SELECT %s FROM %s LIMIT 25 OFFSET %d", controller.getAttributes(),
                controller.getTable(), NavigatorController.getOffset()));
        list = controller.extract(sets); // extract() also close ResultSet
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT COUNT(*) FROM " + controller.getTable());
        if (set != null && set.next()) {
            records = set.getInt(1);
            set.close();
        }
        if (statement != null) statement.close();
    }

    @Override
    public void run() {
        list = FXCollections.observableArrayList();
        Connection connection = null;
        try {
            MySQLHandle handle = new MySQLHandle(Configuration.load());
            connection = handle.getConnection();
            setList(connection);
            setRecords(connection);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            controller.update(getList(), getRecords());
            try {
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
