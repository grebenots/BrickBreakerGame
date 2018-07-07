package com.brickbbbreaker.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick {

    private double x;
    private double y;

    private BrickCoordinate coordinate;
    private BufferedImage image;

    public Brick(int x, int y, BrickBBBreaker game) {
        coordinate = new BrickCoordinate(x, y);
        this.x = coordinate.x * BrickBBBreaker.SPRITE_SIZE + BrickBBBreaker.SPRITE_SIZE;
        this.y = coordinate.y * BrickBBBreaker.BRICK_HEIGHT;

        image = game.getTextures().brick1;  // Change this to something dynamic
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }
}