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
 * Controller for the Craps game view.
 *
 * <p>It rolls both dice, delegates the rules to {@link Game} and reflects the
 * state of the match (last roll, point, won games and lost games) in the
 * view.</p>
 */
public class GameController {

    /** Text shown when there is no value to display yet. */
    private static final String EMPTY_VALUE = "--";

    /** Model that holds the point and the win/loss counters. */
    private final Game game = new Game();

    private Player currentPlayer;

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label tiroLabel;

    @FXML
    private Label puntoLabel;

    @FXML
    private Label wonGamesLabel;

    @FXML
    private Label lostGamesLabel;

    @FXML
    private TextArea messageTextArea;

    /**
     * Returns the player of the current game.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the player of the current game and starts a new match.
     *
     * @param currentPlayer the player who is going to play
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
        startNewGame();
    }

    /**
     * Handles the click on the "Lanzar" button: rolls both dice, applies the
     * Craps rules and updates the view with the result.
     *
     * @param event the mouse event that triggered the action
     */
    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        dice1.roll();
        dice2.roll();

        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice1.getImagePath()
                        )
                )
        );

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                dice2.getImagePath()
                        )
                )
        );

        int total = dice1.getValue() + dice2.getValue();
        boolean hadPoint = game.isPointEstablished();

        Game.TurnResult result = game.play(total);

        updateScoreboard(total);
        appendMessage(buildMessage(result, total, hadPoint));

        if (result == Game.TurnResult.WIN || result == Game.TurnResult.LOSE) {
            appendMessage("Nuevo juego: realiza tu tiro de salida.");
        }
    }

    /**
     * Resets the match state and the view for a new game session.
     */
    private void startNewGame() {
        game.reset();
        updateScoreboard(game.getLastRoll());
        messageTextArea.clear();
        appendMessage(
                "Nuevo juego para " + currentPlayer.getNickname()
                        + ". Realiza tu tiro de salida."
        );
    }

    /**
     * Updates the labels that show the state of the game.
     *
     * @param total total obtained in the last roll
     */
    private void updateScoreboard(int total) {
        tiroLabel.setText(total == 0 ? EMPTY_VALUE : String.valueOf(total));
        puntoLabel.setText(
                game.isPointEstablished()
                        ? String.valueOf(game.getPoint())
                        : EMPTY_VALUE
        );
        wonGamesLabel.setText(String.valueOf(game.getWonGames()));
        lostGamesLabel.setText(String.valueOf(game.getLostGames()));
    }

    /**
     * Builds the message that describes the outcome of a turn.
     *
     * @param result   outcome returned by the game model
     * @param total    total obtained in the last roll
     * @param hadPoint whether a point was established before the roll
     * @return the message to show to the player
     */
    private String buildMessage(Game.TurnResult result, int total, boolean hadPoint) {
        switch (result) {
            case WIN:
                return hadPoint
                        ? "¡Ganaste! Repetiste el punto con un " + total + "."
                        : "¡Ganaste! Sacaste " + total + " en el tiro de salida.";
            case LOSE:
                return hadPoint
                        ? "Sacaste 7 antes del punto. Perdiste."
                        : "Craps: sacaste " + total + ". Perdiste.";
            case POINT_ESTABLISHED:
                return "Punto establecido: " + total + ".";
            default:
                return "Sacaste " + total + ". El punto sigue en "
                        + game.getPoint() + ".";
        }
    }

    /**
     * Appends a line to the game log shown in the view.
     *
     * @param message message to append
     */
    private void appendMessage(String message) {
        messageTextArea.appendText(message + System.lineSeparator());
    }
}
