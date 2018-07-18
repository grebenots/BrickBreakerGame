package com.stoneberg.brickbbbreaker;

import java.util.ArrayList;

public class HighScoreTable {

    private ArrayList<HighScoreElement> scores;

    public HighScoreTable() {
        scores = new ArrayList<HighScoreElement>();

        // TO DO, SAVE FROM FILE AND READ FROM THAT INSTEAD OF HARD CODING THIS
        scores.add(new HighScoreElement("Tim", 999));
        scores.add(new HighScoreElement("Rachel", 900));
        scores.add(new HighScoreElement("Ryan", 800));
        scores.add(new HighScoreElement("Reagan", 700));
        scores.add(new HighScoreElement("Kennedy", 600));
        scores.add(new HighScoreElement("Mario", 500));
        scores.add(new HighScoreElement("Luigi", 400));
        scores.add(new HighScoreElement("Link", 300));
        scores.add(new HighScoreElement("Sonic", 200));
        scores.add(new HighScoreElement("Ganon", 100));
    }

    private void insertNewScore() {

    }

    private void sortTable() {

    }

    public ArrayList<HighScoreElement> getScores() {
        return scores;
    }

}
