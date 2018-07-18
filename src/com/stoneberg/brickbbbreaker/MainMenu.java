package com.stoneberg.brickbbbreaker;

import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Random;

public class MainMenu {

    private BrickBBBreaker theStinkyCheese = null;
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

    // TO DO, MOVE UI.JAVA stuff into MainMenu, with UI.java BEING A HELPER CLASS TO DRAW UI ELEMENTS

    public MainMenu() {
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
        drawCenteredString("BRICK[BBB]REAKER", 110, titleFont, Color.WHITE);
        drawCenteredString("https://github.com/grebenots/BrickBreakerGame", 165, githubFont, Color.WHITE);

        if(numCredits > 0) {
            float minAlpha = .25f;
            float maxAlpha = 1.0f;
            float transparency = currentAlpha;

            if(currentAlpha < minAlpha)
                transparency = minAlpha;
            if(currentAlpha > maxAlpha)
                transparency = maxAlpha;

            setOpacity(transparency);
            drawCenteredString("Push [Enter] to play!", 500, mainFont, Color.WHITE);
            setOpacity(1);

            drawCenteredString("Credits " + numCredits + " (" + numCoins + "/" + coinsPerCredit + ")", 545, coinFont, Color.GREEN);
        } else {
            drawCenteredString("Push [+] key to insert a coin", 500, coinFont, Color.WHITE);
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

        // Display high score table
        ArrayList elements = highScoreTable.getScores();
        for(int i = 0; i < 10; i++) {
            HighScoreElement element = (HighScoreElement)elements.get(i);

            drawCenteredString("High Scores", 210, mainFont, Color.WHITE);

            Color color = setRandomColor();
            drawString(element.getName(), 125, (250 + (20 * i)), mainFont, color);
            drawString(Integer.toString(element.getScore()), 265, (250 + (20 * i)), mainFont, color);
            //System.out.println(element.getName() + " : " + element.getScore());
        }


        // Opacity test stuff
        // float alpha = 0.1f;
        // AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        // Graphics2D gg = (Graphics2D)g;
        // gg.setComposite(ac);
        // gg.drawImage(textures.background, 0,0, getWidth(), getHeight(), null);
        // ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
        // gg.setComposite(ac);
        // End opacity test
    }

    private void setOpacity(float amount) {
        AlphaComposite alphaC = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, amount);
        Graphics2D g2 = (Graphics2D)theStinkyCheese.getGraphics();
        g2.setComposite(alphaC);
    }

    private Color setRandomColor() {
        Random random = new Random();
        Color color = Color.WHITE;

        switch (random.nextInt(5)) {
            case 0:
                color = Color.WHITE;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.RED;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.CYAN;
                break;
            case 5:
                color = Color.MAGENTA;
        }

        return color;
    }

    private void drawString(String text, int x, int y, Font font, Color color) {
        theStinkyCheese.getGraphics().setFont(font);
        theStinkyCheese.getGraphics().setColor(color);
        theStinkyCheese.getGraphics().drawString(text, x, y);
    }

    private void drawCenteredString(String text, int y, Font font, Color color) {
        theStinkyCheese.getGraphics().setFont(font);
        theStinkyCheese.getGraphics().setColor(color);

        // Calculate center
        FontMetrics metrics = theStinkyCheese.getGraphics().getFontMetrics(font);
        int titleWidth = metrics.stringWidth(text);
        int x = theStinkyCheese.WINDOW_SIZE.getX().intValue() / 2 - titleWidth / 2;

        theStinkyCheese.getGraphics().drawString(text, x, y);
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
}
