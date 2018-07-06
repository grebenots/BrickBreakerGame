package com.brickbbbreaker.src.main;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Player {

    private double x;
    private double y;

    private double velocityX = 0;
    private double velocityY = 0;

    private BufferedImage player;

    public Player(double x, double y, BrickBBBreaker game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        player = ss.grabImage(1,1,32,32);
    }

    public void tick() {
        x += velocityX;
        y += velocityY;
    }

    public void render(Graphics g) {
        g.drawImage(player,(int)x,(int)y,null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

}
