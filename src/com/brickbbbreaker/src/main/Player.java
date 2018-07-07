package com.brickbbbreaker.src.main;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Player {

    private double x;
    private double y;

    private double velocityX = 0;
    private double velocityY = 0;

    private BufferedImage playerCenter;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;

    public Player(double x, double y, BrickBBBreaker game) {
        this.x = x;
        this.y = y;

        playerLeft = game.getTextures().playerLeft;
        playerCenter = game.getTextures().playerCenter;
        playerRight = game.getTextures().playerRight;
    }

    public void tick() {
        x += velocityX;
        y += velocityY;

        if(x <= 0 + 16)
            x = 0 + 16;
        if(x >= 440 - 16)
            x = 440 - 16;
    }

    public void render(Graphics g) {
        g.drawImage(playerLeft, (int)x - 32, (int)y, null);
        g.drawImage(playerCenter,(int)x,(int)y,null);
        g.drawImage(playerRight, (int)x + 32, (int)y, null);
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
