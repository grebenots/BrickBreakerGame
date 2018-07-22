package com.stoneberg.brickbbbreaker;

import java.util.Map;

public class GameUI extends UI {

    public void render() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();

        drawBorder();
        drawCurrentScore();
        drawCurrentLevel();
    }
}
