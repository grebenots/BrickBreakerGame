package com.stoneberg.brickbbbreaker;

import java.awt.*;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;

public class MainMenu {

    private BrickBBBreaker theStinkyCheese = null;
    private Font titleFont;
    private Font coinFont;
    private Font mainFont;
    private Font githubFont;

    private Clip coinClip;
    private Clip creditClip;
    private File coinWav;
    private File creditWav;

    private int numCoins;
    private int coinsPerPlay;

    public MainMenu() {
        titleFont = new Font("arial", Font.BOLD, 18);
        mainFont = new Font("arial", Font.BOLD, 14);
        coinFont = new Font("arial", Font.BOLD, 12);
        githubFont = new Font("arial", Font.PLAIN, 10);

        numCoins = 0;
        coinsPerPlay = 4;

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
        drawCenteredString("Push [+] key to insert a coin", 500, mainFont, Color.WHITE);
        drawCenteredString("Credits (" + numCoins + "/" + coinsPerPlay + ")", 545, coinFont, Color.RED);

        if(numCoins >= coinsPerPlay) {
            drawCenteredString("Push [Enter] to play!", 300, mainFont, Color.WHITE);
        }
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
