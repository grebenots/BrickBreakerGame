package com.brickbbbreaker.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick {

    private double x;
    private double y;

    private BufferedImage image;

    public Brick(double x, double y, BrickBBBreaker game) {
        this.x = x;
        this.y = y;

        image = game.getTextures().brick1;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }
}
