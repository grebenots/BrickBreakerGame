package com.stoneberg.brickbbbreaker;

public class HighScoreElement {

    private String name;
    private int score;

    public HighScoreElement(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
