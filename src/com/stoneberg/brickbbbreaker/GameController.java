package com.stoneberg.brickbbbreaker;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class GameController {

    public GameController() {

    }

    public void tick() {

    }

    public void render() {
        BrickBBBreaker theStinkyCheese = BrickBBBreaker.getCurrentGame();
        Graphics graphics = theStinkyCheese.getGraphics();

        // Set the background to black, then draw it
        BufferedImage blackBackground = new BufferedImage(theStinkyCheese.WINDOW_SIZE.getX().intValue(), theStinkyCheese.WINDOW_SIZE.getY().intValue(), BufferedImage.TYPE_INT_RGB);
        graphics.drawImage(blackBackground, 0, 0, theStinkyCheese.WINDOW_SIZE.getX().intValue(), theStinkyCheese.WINDOW_SIZE.getY().intValue(),null);

        // Render player
        // ENDED HERE, GET THE PLAYER RENDERING!!!!

        // Render bricks


        // Render ball


        // Render More Stuff...



    }
}
