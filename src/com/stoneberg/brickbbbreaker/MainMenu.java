package com.stoneberg.brickbbbreaker;

import java.awt.*;

public class MainMenu {

    private BrickBBBreaker theStinkyCheese = null;
    private Font titleFont;
    private Font coinFont;
    private Font mainFont;

    private int numCredits;
    private int creditsPerPlay;

    public MainMenu() {
        titleFont = new Font("arial", Font.BOLD, 18);
        mainFont = new Font("arial", Font.BOLD, 14);
        coinFont = new Font("arial", Font.BOLD, 12);
        numCredits = 0;
        creditsPerPlay = 4;
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
}
