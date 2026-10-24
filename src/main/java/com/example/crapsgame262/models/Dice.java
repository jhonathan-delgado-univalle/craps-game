package com.example.crapsgame262.models;

public class Dice {

    private int value;

    public int roll() {
        value = (int)(Math.random()*6) + 1;
        return value;
    }

    /**
     * Returns the value obtained in the last roll without rolling again.
     *
     * @return the last rolled value, or {@code 0} if the dice have not been
     *         rolled yet
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the classpath location of the image that represents the current
     * value.
     *
     * @return the absolute classpath path of the dice image
     */
    public String getImagePath() {
        return "/com/example/crapsgame262/images/dices/dice" + value + ".png";
    }
}
