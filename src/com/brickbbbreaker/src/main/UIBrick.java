package com.brickbbbreaker.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIBrick {

    private double x;
    private double y;

    private BrickCoordinate coordinate;
    private BufferedImage image;

    public UIBrick(int x, int y, BrickBBBreaker game) {
        coordinate = new BrickCoordinate(x, y);
        this.x = coordinate.x * BrickBBBreaker.SPRITE_SIZE;
        this.y = coordinate.y * BrickBBBreaker.BRICK_HEIGHT;

        image = game.getTextures().uiBorderLeft;  // Change this to something dynamic
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }
}
