package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.AlertBox;
import com.example.crapsgame262.models.Player;
import com.example.crapsgame262.views.GameView;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private TextField textFieldNickname;

    @FXML
    void onMouseClickedBtnStart(MouseEvent event) {
        String nickname = textFieldNickname.getText();

        if (nickname == "") {
            AlertBox alertBox = new AlertBox();
            alertBox.showAlertBox(
                    "Craps Game - Nombre de usuario",
                    "Nombre de usuario",
                    "Debes diligenciar tu nombre de usuario.");
        }

        Player player = new Player();
        player.setNickname(nickname);

        GameView gameView = null;
        try {
            gameView = GameView.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameController gameController = gameView.getController();
        gameController.setCurrentPlayer(player);

        gameView.show();

        textFieldNickname.getScene().getWindow().hide();
    }

}
