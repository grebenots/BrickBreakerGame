package com.brickbbbreaker.src.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private double x;
    private double y;

    private BufferedImage image;

    public Bullet(double x, double y, BrickBBBreaker game) {
        this.x = x;
        this.y = y;

        image = game.getTextures().playerBullet;
    }

    public void tick() {
        y -= 10;
    }

    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
    }

    public double getY() {
        return y;
    }
}
