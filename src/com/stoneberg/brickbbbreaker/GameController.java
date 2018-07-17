package com.stoneberg.brickbbbreaker;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class GameController {

    private BrickBBBreaker theStinkyCheese = null;

    public GameController() {

    }

    public void tick() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();

        // TO DO, tick based on the currentState
        theStinkyCheese.getPlayer().tick();
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

    private void renderMenu() {

    }

    private void renderGame() {
        // Render player
        theStinkyCheese.getPlayer().render();

        // Render bricks


        // Render ball


        // Render More Stuff...


    }

    private void renderPause() {

    }
}
