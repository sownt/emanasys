package com.vosxvo.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class NavigatorController implements Initializable, Controller {

    @FXML
    private Label info;

    @FXML
    private Button first;

    @FXML
    private Button previous;

    @FXML
    private Button next;

    @FXML
    private Button last;

    private static Dataflow current;
    private static int offset = 0;
    private static int records;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller.CONTROLLER_MANAGE.add(Controller.NAVIGATOR_CONTROLLER, this);
        info.setText(String.format("%d - %d of %d", offset + 1, offset + 25, records));
    }

    public void onMouseClickedListener(MouseEvent mouseEvent) {
        Object source = mouseEvent.getSource();
        try {
            if (source.equals(next)) {
                offset += 25;
            } else if (source.equals(previous)) {
                if (offset - 25 >= 0) offset -= 25;
                else offset = 0;
            } else if (source.equals(first)) {
                offset = 0;
            } else if (source.equals(last)) {
                if (records - 25 >= 0) offset = records - 25;
                else offset = 0;
            }
        } finally {
            NavigatorController.getCurrent().navigateAction();
            updateInfo();
        }
    }

    private void updateInfo() {
        info.setText(String.format("%d - %d of %d", offset, offset + 25, records));
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

    public static Dataflow getCurrent() {
        return current;
    }

    public static void setCurrent(Dataflow current) {
        NavigatorController.current = current;
        NavigatorController.offset = 0;
    }

    public static int getOffset() {
        return offset;
    }

    public static void setOffset(int offset) {
        NavigatorController.offset = offset;
    }

    public static void addOffset(int add) {
        NavigatorController.offset += add;
    }

    public static int getRecords() {
        return records;
    }

    public static void setRecords(int records) {
        NavigatorController.records = records;
    }
}
