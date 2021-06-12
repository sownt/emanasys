package com.vosxvo.java.controllers;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public abstract class Menu implements Controller {
    public abstract void onClickedListener(MouseEvent mouseEvent);
    public abstract Button getSelected();
    public abstract void setSelected(Button selected);
    public void changeSelected(Menu menu, Button selected) {
        if (menu.getSelected() != null) {
            menu.getSelected().getStyleClass().removeAll("menu-current");
            menu.getSelected().getStyleClass().add("menu-item");
            menu.setSelected(selected);
            menu.getSelected().getStyleClass().add("menu-current");
        } else {
            menu.setSelected(selected);
            menu.getSelected().getStyleClass().add("menu-current");
        }
    }
}
