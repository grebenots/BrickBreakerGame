package com.stoneberg.brickbbbreaker;

import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import java.util.ArrayList;

public class MainMenuUI extends UI {

    private HighScoreTable highScoreTable;
    private Font titleFont;
    private Font coinFont;
    private Font mainFont;
    private Font githubFont;

    private Clip coinClip;
    private Clip creditClip;
    private File coinWav;
    private File creditWav;

    private int numCoins;
    private int numCredits;
    private int coinsPerCredit;

    private float currentAlpha;
    private float flickerSpeed;
    private boolean flickeringDown;

    public MainMenuUI() {
        highScoreTable = new HighScoreTable();
        titleFont = new Font("arial", Font.BOLD, 18);
        mainFont = new Font("arial", Font.BOLD, 16);
        coinFont = new Font("arial", Font.BOLD, 12);
        githubFont = new Font("arial", Font.PLAIN, 10);

        numCoins = 0;
        numCredits = 0;
        coinsPerCredit = 4;

        currentAlpha = 1.0f;
        flickerSpeed = 0.01f;
        flickeringDown = true;

        try {
            coinClip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
            creditClip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
            coinWav = new File("src/resources/insertCoin.wav");
            creditWav = new File("src/resources/addCredit.wav");

            coinClip.open(AudioSystem.getAudioInputStream(coinWav));
            creditClip.open(AudioSystem.getAudioInputStream(creditWav));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void render() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();

        drawBorder();
        drawStaticElements();
        drawHighScores();
        drawDynamicElements();
    }

    public void insertCoin() {
        numCoins++;

        if(numCoins % 4 == 0) {
            numCredits++;
            numCoins = 0;
            creditClip.setFramePosition(0);
            creditClip.loop(0);
            creditClip.start();
        } else {
            coinClip.setFramePosition(0);
            coinClip.loop(0);
            coinClip.start();
        }
    }

    public void useCredit() {
        if(numCredits > 0) {
            numCredits--;
            theStinkyCheese.setCurrentState(BrickBBBreaker.GameState.GAME);
        }
    }

    private void drawStaticElements() {
        drawCenteredString("BRICK[BBB]REAKER", 142, titleFont, Color.WHITE);
        drawCenteredString("https://github.com/grebenots/BrickBreakerGame", 200, githubFont, Color.WHITE);

        // Bricks
        for(int i = 1; i < 12; i++) {
            drawBrickByCoordinate("brickRed", i, 5);
            drawBrickByCoordinate("brickBlue", i, 6);

            drawBrickByCoordinate("brickYellow", i, 9);
            drawBrickByCoordinate("brickOrange", i, 10);
        }

        for(int i = 1; i < 3; i++) {
            drawBrickByCoordinate("brickGreen", i, 7);
            drawBrickByCoordinate("brickPurple", i, 8);
        }

        for(int i = 10; i < 12; i++) {
            drawBrickByCoordinate("brickGreen", i, 7);
            drawBrickByCoordinate("brickPurple", i, 8);
        }
    }

    private void drawDynamicElements() {
        if(numCredits > 0) {
            float minAlpha = .25f;
            float maxAlpha = 1.0f;
            float transparency = currentAlpha;

            if(currentAlpha < minAlpha)
                transparency = minAlpha;
            if(currentAlpha > maxAlpha)
                transparency = maxAlpha;

            setOpacity(transparency);
            drawCenteredString("Push [Enter] to play!", 525, mainFont, Color.WHITE);
            setOpacity(1);

            drawCenteredString("Credits " + numCredits + " (" + numCoins + "/" + coinsPerCredit + ")", 545, coinFont, Color.GREEN);
        } else {
            drawCenteredString("Push [+] key to insert a coin", 525, coinFont, Color.WHITE);
            drawCenteredString("Credits 0 (" + numCoins + "/" + coinsPerCredit + ")", 545, coinFont, Color.RED);
        }

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

    private void drawHighScores() {
        ArrayList elements = highScoreTable.getScores();
        for(int i = 0; i < 10; i++) {
            HighScoreElement element = (HighScoreElement)elements.get(i);

            drawCenteredString("High Scores", 230, mainFont, Color.WHITE);

            Color color = randomColor();
            drawString(element.getName(), 125, (270 + (20 * i)), mainFont, color);
            drawString(Integer.toString(element.getScore()), 265, (270 + (20 * i)), mainFont, color);
        }
    }

}
