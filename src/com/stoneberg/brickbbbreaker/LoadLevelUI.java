package com.stoneberg.brickbbbreaker;

import java.awt.*;
import java.util.*;

public class LoadLevelUI extends UI {

    private float currentAlpha;
    private boolean flickeringDown;
    private float flickerSpeed;
    private double timerCurrent;
    private double timerEnd;

    private Timer timer;

    public LoadLevelUI() {
        currentAlpha = 1.0f;
        flickeringDown = true;
        flickerSpeed = .005f;
        timerCurrent = 0.0;
        timerEnd = 3.0;
    }

    public void render() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();

        drawBorder();
        drawCurrentScore();
        drawCurrentLevel();
        drawLoadingText();

        if(timerCurrent == 0.0) {
            timerCurrent = System.nanoTime();
            timerEnd = timerCurrent + timerEnd * 1000000000;
        }
        timerCurrent = System.nanoTime();

        if(timerCurrent >= timerEnd)
            theStinkyCheese.setCurrentState(BrickBBBreaker.GameState.GAME);

    }

    private void drawLoadingText() {

        float minAlpha = .2f;
        float maxAlpha = 1.0f;
        float transparency = currentAlpha;
        String loadingText = "Get Ready!";
        String loadingText2 = "Lives left " + theStinkyCheese.getPlayer().getNumLives();

        if(currentAlpha < minAlpha)
            transparency = minAlpha;
        if(currentAlpha > maxAlpha)
            transparency = maxAlpha;

        setOpacity(transparency);
        drawCenteredString(loadingText,260, largeFont, Color.WHITE);
        drawCenteredString(loadingText2,278, coinFont, Color.RED);
        setOpacity(1.0f);

        if(flickeringDown) {
            currentAlpha -= flickerSpeed;
            if(currentAlpha < 0) {
                currentAlpha = 0;
                flickeringDown = false;
            }
        } else {
            currentAlpha += flickerSpeed;
            if(currentAlpha > 1) {
                currentAlpha = 1;
                flickeringDown = true;
            }
        }
    }
}
