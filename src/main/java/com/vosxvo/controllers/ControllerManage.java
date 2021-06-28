package com.vosxvo.controllers;

import javafx.stage.Stage;

public class ControllerManage {
    private Controller[] list = new Controller[10];
    private Stage global;

    private ControllerManage() {
    }

    public Stage getGlobal() {
        return global;
    }

    public void setGlobal(Stage global) {
        this.global = global;
    }

    public static ControllerManage getInstance() {
        return ControllerManageHelper.INSTANCE;
    }

    public void add(int pos, Controller o) {
        list[pos] = o;
    }

    public Controller get(int pos) {
        return list[pos];
    }

    public void kill(int pos) {
        list[pos] = null;
    }

    private static class ControllerManageHelper {
        private static final ControllerManage INSTANCE = new ControllerManage();
    }
}
