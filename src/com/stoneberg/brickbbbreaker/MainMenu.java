package com.stoneberg.brickbbbreaker;

import com.sun.media.sound.WaveFileReader;

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

    private Clip coinClip;
    private Clip creditClip;
    private File coinWav;
    private File creditWav;

    private int numCredits;
    private int creditsPerPlay;

    public MainMenu() {
        titleFont = new Font("arial", Font.BOLD, 18);
        mainFont = new Font("arial", Font.BOLD, 14);
        coinFont = new Font("arial", Font.BOLD, 12);
        numCredits = 0;
        creditsPerPlay = 4;

        try {
            coinClip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
            creditClip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
            coinWav = new File("../../../resources/insertCoin.wav");
            creditWav = new File("../../../resources/insertCoin.wav");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void render() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();

        drawCenteredString("BRICK[BBB]REAKER", 110, titleFont, Color.WHITE);
        drawCenteredString("Push [+] key to insert a coin", 500, mainFont, Color.WHITE);
        drawCenteredString("Credits (" + numCredits + "/" + creditsPerPlay + ")", 545, coinFont, Color.RED);

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
        numCredits++;

        try {
            coinClip.open(AudioSystem.getAudioInputStream(coinWav));
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public int getNumCredits() {
        return numCredits;
    }

    public void setNumCredits(int numCredits) {
        this.numCredits = numCredits;
    }
}
