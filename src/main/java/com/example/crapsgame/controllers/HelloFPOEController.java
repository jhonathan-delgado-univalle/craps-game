package com.example.crapsgame.controllers;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class HelloFPOEController {

    @FXML
    private ImageView imageView;
    private int cont = 0;

    @FXML
    private TextField nicknameTextField;

    @FXML
    public void onActionFPOEButton(ActionEvent actionEvent) {
        System.out.println("Hello FPOE from FXML");
        cont += 1;
        if (cont % 2 == 0) {
            imageView.setImage(new Image(
                    getClass().getResourceAsStream("/com/example/crapsgame/imeges/dado.png"))
            );
        } else {
            imageView.setImage(new Image(
                    getClass().getResourceAsStream("/com/example/crapsgame/imeges/casino.png"))
            );
        }

    }

    @FXML
    void onKeyPressedNicknameTexField(KeyEvent event) {
        String nickname = nicknameTextField.getText();
        // System.out.println("+" + nickname);
        System.out.println(event.getCode());
    }

    @FXML
    void onKeyReleasedNicknameTexField(KeyEvent event) {
        String nickname = nicknameTextField.getText();
        // System.out.println("-" + nickname);
    }
}
