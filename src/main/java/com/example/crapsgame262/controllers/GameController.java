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
 * <p>
 * Handles user interactions and updates the UI based on game logic by delegating
 * all rules to the {@link Game} model. The last roll, the current point and the
 * number of games won/lost are shown in the view.
 * </p>
 */
public class GameController {

    /** Model that holds the current game state and rules. */
    private final Game game = new Game();

    /** Current player of the session. */
    private Player currentPlayer;

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    /**
     * Label used to display the sum of the last dice roll.
     */
    @FXML
    private Label dropLabel;

    /**
     * Label used to display the current point value.
     */
    @FXML
    private Label pointLabel;

    /**
     * TextArea used to display the summary of games won/lost and the game messages.
     */
    @FXML
    private TextArea summaryTextArea;

    /**
     * Returns the current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player and updates the nickname label.
     * <p>
     * A new game is started automatically when the player is assigned.
     * </p>
     *
     * @param currentPlayer the player to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
        startNewGame();
    }

    /**
     * Handles the roll button click event. Rolls both dice, updates their images,
     * processes the roll through the {@link Game} model, and updates the UI with
     * the current roll, point, won/lost games count and game messages.
     *
     * @param event the mouse event triggered by clicking the roll button
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
        appendSummary(result, total, hadPoint);

        if (result == Game.TurnResult.WIN || result == Game.TurnResult.LOSE) {
            appendSummaryMessage("Nuevo juego: realiza tu tiro de salida.");
        }
    }

    /**
     * Resets the match state and the view for a new game.
     */
    private void startNewGame() {
        game.reset();
        updateScoreboard(0);
        summaryTextArea.clear();
        appendSummaryMessage(
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
        if (total == 0) {
            dropLabel.setText("--");
        } else {
            dropLabel.setText(String.valueOf(total));
        }

        if (game.isPointEstablished()) {
            pointLabel.setText(String.valueOf(game.getPoint()));
        } else {
            pointLabel.setText("--");
        }
    }

    /**
     * Appends the game summary including won/lost games and the turn message
     * to the summary text area.
     *
     * @param result   outcome returned by the game model
     * @param total    total obtained in the last roll
     * @param hadPoint whether a point was established before the roll
     */
    private void appendSummary(Game.TurnResult result, int total, boolean hadPoint) {
        String summary = "Games won: " + game.getWonGames() + System.lineSeparator()
                + "Games lost: " + game.getLostGames() + System.lineSeparator()
                + buildMessage(result, total, hadPoint);

        summaryTextArea.setText(summary);
    }

    /**
     * Appends an additional message to the summary text area.
     *
     * @param message message to append
     */
    private void appendSummaryMessage(String message) {
        summaryTextArea.appendText(System.lineSeparator() + message);
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
                return "Sacaste " + total + ". El punto sigue en " + game.getPoint() + ".";
        }
    }
}
