package com.vosxvo.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable, Controller {

    @FXML
    private BorderPane mainPane;

    @FXML
    private AnchorPane topBar;

    @FXML
    private VBox sideBar;

    @FXML
    private AnchorPane bottomBar;

    @FXML
    private HBox tools;

    @FXML
    private HBox status;

    /**
     * Show main scene
     * @param stage current stage to add this scene
     * @throws IOException main.fxml file not found
     */
    public static void show(Stage stage) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(Objects.requireNonNull(MainController.class.getResource("/com/vosxvo/views/main.fxml"))));
        stage.setTitle("eManasys - Employee Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void manage() throws IOException {
        ControllerManage controllerManage = ControllerManage.getInstance();
        MainController controller = (MainController) controllerManage.get(Controller.MAIN_CONTROLLER);
        controller.setSideBar(MainController.class.getResource("/com/vosxvo/views/manage_menu.fxml"));
        controller.setMainPane(MainController.class.getResource("/com/vosxvo/views/manage_employees.fxml"));
        controller.setTools(MainController.class.getResource("/com/vosxvo/views/bottom_nav.fxml"));
    }

    public void setMainPane(URL url) throws IOException {
        mainPane.setCenter(FXMLLoader.load(url));
    }

    public void setSideBar(URL url) throws IOException {
        sideBar.getChildren().clear();
        sideBar.getChildren().add(FXMLLoader.load(url));
    }

    public void setTools(URL url) throws IOException {
        tools.getChildren().add(FXMLLoader.load(url));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerManage manage = ControllerManage.getInstance();
        manage.add(Controller.MAIN_CONTROLLER, this);
        try {
            mainPane.setCenter(FXMLLoader.load(Objects.requireNonNull(MainController.class.getResource("/com/vosxvo/views/main_dashboard.fxml"))));
            sideBar.getChildren().add(FXMLLoader.load(Objects.requireNonNull(MainController.class.getResource("/com/vosxvo/views/main_menu.fxml"))));
            status.getChildren().add(FXMLLoader.load(Objects.requireNonNull(MainController.class.getResource("/com/vosxvo/views/bottom_status.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
