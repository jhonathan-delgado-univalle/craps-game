package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.Dice;
import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Controller class for the Craps game view.
 * Handles user interactions and updates the UI based on game logic.
 */
public class GameController {

    private Player currentPlayer;

    /**
     * Game model instance that handles the Craps logic.
     */
    private Game game;

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    /**
     * Label used to display the current dice roll sum.
     */
    @FXML
    private Label dropLabel;

    /**
     * Label used to display the current point value.
     */
    @FXML
    private Label pointLabel;

    /**
     * TextArea used to display the game summary, including won/lost games and messages.
     */
    @FXML
    private TextArea summaryTextArea;

    /**
     * Initializes the controller after its root element has been completely processed.
     * Creates a new instance of the Game model.
     */
    @FXML
    public void initialize() {
        game = new Game();
    }

    /**
     * Gets the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player and updates the nickname label.
     *
     * @param currentPlayer the player to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    /**
     * Handles the roll button click event. Rolls both dice, updates their images,
     * processes the roll through the Game model, and updates the UI with the current
     * roll, point, won/lost games count and game messages.
     *
     * @param event The mouse event triggered by clicking the roll button.
     */
    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        // Create and roll dice 1
        Dice dice1 = new Dice();
        dice1.roll();

        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice1.getImagePath()
                        )
                )
        );

        // Create and roll dice 2
        Dice dice2 = new Dice();
        dice2.roll();

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice2.getImagePath()
                        )
                )
        );

        // Process the roll with the Game model
        game.jugar(dice1, dice2);

        // Update UI with game data
        dropLabel.setText(String.valueOf(game.getTiro()));
        pointLabel.setText(String.valueOf(game.getPunto()));

        String summary = "Games won: " + game.getJuegosGanados() + "\n" +
                         "Games lost: " + game.getJuegosPerdidos() + "\n" +
                         game.getMensaje();
        summaryTextArea.setText(summary);
    }
}
