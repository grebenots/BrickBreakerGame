package com.stoneberg.brickbbbreaker;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class GameController {

    private BrickBBBreaker theStinkyCheese = null;

    public GameController() {

    }

    public void tick() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();

        // Now we render based upon the current game state
        switch(theStinkyCheese.getCurrentState()) {
            case MENU:
                tickMenu();
                break;
            case GAME:
                tickGame();
                break;
            case PAUSED:
                tickPause();
                break;
        }
    }

    public void render() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
        Graphics graphics = theStinkyCheese.getGraphics();

        // Set the background to black, then draw it
        BufferedImage blackBackground = new BufferedImage(theStinkyCheese.WINDOW_SIZE.getX().intValue(), theStinkyCheese.WINDOW_SIZE.getY().intValue(), BufferedImage.TYPE_INT_RGB);
        graphics.drawImage(blackBackground, 0, 0, theStinkyCheese.WINDOW_SIZE.getX().intValue(), theStinkyCheese.WINDOW_SIZE.getY().intValue(),null);

        // Now we render based upon the current game state
        switch(theStinkyCheese.getCurrentState()) {
            case MENU:
                renderMenu();
                break;
            case GAME:
                renderGame();
                break;
            case PAUSED:
                renderPause();
                break;
        }
    }

    private void tickMenu() {

    }

    private void tickGame() {
        // Tick player
        theStinkyCheese.getPlayer().tick();

        // Tick bricks


        // Tick ball


        // Tick More Stuff...



    }

    private void tickPause() {

    }

    private void renderMenu() {
        theStinkyCheese.getUI().render();
        theStinkyCheese.getMainMenu().render();
    }

    private void renderGame() {
        // Render player
        theStinkyCheese.getPlayer().render();

        // Render bricks


        // Render ball


        // Render More Stuff...



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

    private void renderPause() {

    }
}
