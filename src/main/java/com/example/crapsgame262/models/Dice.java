package com.example.crapsgame262.models;

public class Dice {

    private int value;

    public int roll() {
        value = (int)(Math.random()*6) + 1;
        return value;
    }

    public String getImagePath() {
        return "/com/example/crapsgame262/images/dices/dice" + value + ".png";
    }
}
