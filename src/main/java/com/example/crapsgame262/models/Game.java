package com.example.crapsgame262.models;

/**
 * Models a Craps game session.
 *
 * <p>The game starts with a come-out roll. Rolling a 7 or an 11 wins
 * immediately, while rolling a 2, 3 or 12 is a "craps" and loses. Any other
 * total (4, 5, 6, 8, 9 or 10) becomes the <em>point</em>. Once a point is
 * established, the player keeps rolling until they repeat the point (win) or
 * roll a 7 before it (lose).</p>
 *
 * <p>The class also keeps the number of games won and lost during the
 * session.</p>
 */
public class Game {

    /**
     * Possible outcomes of a single turn.
     */
    public enum TurnResult {
        /** The player won the game. */
        WIN,
        /** The player lost the game. */
        LOSE,
        /** The come-out roll established a new point. */
        POINT_ESTABLISHED,
        /** The game continues; the player must roll again. */
        CONTINUE
    }

    /** Value used to represent that no point has been established. */
    private static final int NO_POINT = 0;

    private int point;
    private int lastRoll;
    private int wonGames;
    private int lostGames;

    /**
     * Plays one turn with the given total of both dice.
     *
     * <p>The turn is evaluated as a come-out roll when no point is
     * established, otherwise it is evaluated against the current point.</p>
     *
     * @param total sum of both dice, expected between 2 and 12
     * @return the outcome of the turn
     */
    public TurnResult play(int total) {
        lastRoll = total;

        if (point == NO_POINT) {
            return playComeOutRoll(total);
        }
        return playPointRoll(total);
    }

    /**
     * Evaluates a come-out roll, which may win, lose or establish the point.
     *
     * @param total sum of both dice
     * @return the outcome of the roll
     */
    private TurnResult playComeOutRoll(int total) {
        if (total == 7 || total == 11) {
            wonGames++;
            return TurnResult.WIN;
        }
        if (total == 2 || total == 3 || total == 12) {
            lostGames++;
            return TurnResult.LOSE;
        }
        point = total;
        return TurnResult.POINT_ESTABLISHED;
    }

    /**
     * Evaluates a roll made after the point has been established.
     *
     * @param total sum of both dice
     * @return the outcome of the roll
     */
    private TurnResult playPointRoll(int total) {
        if (total == point) {
            wonGames++;
            point = NO_POINT;
            return TurnResult.WIN;
        }
        if (total == 7) {
            lostGames++;
            point = NO_POINT;
            return TurnResult.LOSE;
        }
        return TurnResult.CONTINUE;
    }

    /**
     * Resets the current point and clears the win/loss counters, starting a
     * new game session.
     */
    public void reset() {
        point = NO_POINT;
        lastRoll = 0;
        wonGames = 0;
        lostGames = 0;
    }

    /**
     * Returns the current point.
     *
     * @return the established point, or {@code 0} if there is no point yet
     */
    public int getPoint() {
        return point;
    }

    /**
     * Returns the total obtained in the last roll.
     *
     * @return the last roll total, or {@code 0} if no roll has been made
     */
    public int getLastRoll() {
        return lastRoll;
    }

    /**
     * Returns the number of games won in this session.
     *
     * @return the amount of games won
     */
    public int getWonGames() {
        return wonGames;
    }

    /**
     * Returns the number of games lost in this session.
     *
     * @return the amount of games lost
     */
    public int getLostGames() {
        return lostGames;
    }

    /**
     * Indicates whether a point is currently established.
     *
     * @return {@code true} if the player must repeat the point to win
     */
    public boolean isPointEstablished() {
        return point != NO_POINT;
    }
}
