package com.stoneberg.brickbbbreaker;

public class GameUI extends UI {

    public void render() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
        drawBorder();
        drawCurrentScore();

    }
}
