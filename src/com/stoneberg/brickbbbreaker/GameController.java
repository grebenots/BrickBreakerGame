package com.stoneberg.brickbbbreaker;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class GameController {

    private BrickBBBreaker theStinkyCheese = null;

    public GameController() {

    }

    public void tick() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();

        // Now we render based upon the current game state
        switch(theStinkyCheese.getCurrentState()) {
            case MENU:
                tickMenuState();
                break;
            case GAME:
                tickGameState();
                break;
            case PAUSED:
                tickPauseState();
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
                renderMenuState();
                break;
            case GAME:
                renderGameState();
                break;
            case PAUSED:
                renderPauseState();
                break;
        }
    }

    // Ticking
    private void tickMenuState() {

    }

    private void tickGameState() {
        // Tick player
        theStinkyCheese.getPlayer().tick();

        // Tick bricks


        // Tick ball


        // Tick More Stuff...



    }

    private void tickPauseState() {

    }

    // Rendering
    private void renderMenuState() {
        theStinkyCheese.getMainMenuUI().render();
    }

    private void renderGameState() {
        // Render UI
        theStinkyCheese.getGameUI().render();

        // Render player
        theStinkyCheese.getPlayer().render();

        // Render bricks for current level
        Level currentLevel = theStinkyCheese.getLevels().get(theStinkyCheese.getPlayer().getCurrentLevel());
        Map<Generic2D<Integer>, Brick> currentBricks = currentLevel.getBricks();
        for(Generic2D<Integer> key : currentBricks.keySet()) {
            Brick brick = currentBricks.get(key);
            Generic2D<Integer> currentCoordinate = brick.getWallCoordinate();
            theStinkyCheese.getGameUI().drawBrickByCoordinate(brick.getBrickName(), currentCoordinate.getX(), currentCoordinate.getY());
        }

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

    private void renderPauseState() {

    }
}
