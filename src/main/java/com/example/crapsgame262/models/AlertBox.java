package com.example.crapsgame262.models;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertBox implements AlertBoxInterface {

    @Override
    public boolean showConfirmBox(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        Optional<ButtonType> response = alert.showAndWait();
        if ( response.isPresent() && response.get() == ButtonType.OK) {
            return true;
        }
        return false;
    }

    @Override
    public void showAlertBox(String title, String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
