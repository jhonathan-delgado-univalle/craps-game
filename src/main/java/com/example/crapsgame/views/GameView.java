package com.example.crapsgame.views;

import com.example.crapsgame.HelloApplication;
import com.example.crapsgame.controllers.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameView extends Stage {

    private GameController controller;

    public GameView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("game-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        this.controller = fxmlLoader.getController();
        this.setTitle("Craps Game - Welcome!");
        this.setScene(scene);
    }

    public GameController getController() {
        return controller;
    }

    public static GameView getInstance() throws IOException {
        if (GameViewHolder.INSTANCE == null) {
            GameViewHolder.INSTANCE = new GameView();
            return GameViewHolder.INSTANCE;
        } else {
            return GameViewHolder.INSTANCE;
        }
    }

    private static class GameViewHolder {
        private static GameView INSTANCE;
    }

}
