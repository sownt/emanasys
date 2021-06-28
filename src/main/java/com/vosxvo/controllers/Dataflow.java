package com.vosxvo.controllers;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dataflow implements Controller {
    public abstract String getTable();
    public abstract String getAttributes();
    public abstract ObservableList extract(ResultSet sets) throws SQLException;
    public abstract void navigateAction();

    public void update(ObservableList list, int records) {
        NavigatorController.setRecords(records);
    }

    @Override
    public Menu getMenu() {
        return null;
    }

    @Override
    public Navigator getNavigator() {
        return null;
    }
}
