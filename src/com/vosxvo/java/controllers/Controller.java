package com.vosxvo.java.controllers;

public interface Controller {
    int MAIN_CONTROLLER = 0;
    int EMPLOYEES_CONTROLLER = 1;
    int USERS_CONTROLLER = 2;
    int SALARIES_CONTROLLER = 3;
    int DEPARTMENTS_CONTROLLER = 4;
    int TITLES_CONTROLLER = 5;
    int NAVIGATOR_CONTROLLER = 6;
    ControllerManage CONTROLLER_MANAGE = ControllerManage.getInstance();

    Dataflow getDataflow();
    Menu getMenu();
    Navigator getNavigator();
}
