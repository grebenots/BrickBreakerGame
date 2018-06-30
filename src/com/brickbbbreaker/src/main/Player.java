package com.brickbbbreaker.src.main;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Player {

    private double x;
    private double y;

    private BufferedImage player;

    public Player(double x, double y, BrickBBBreaker game) {
        this.x = x;
        this.y = y;

        SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

        player = ss.grabImage(1,1,32,32);
    }

    public void tick() {
        x++;
    }

    public void render(Graphics g) {
        g.drawImage(player,(int)x,(int)y,null);
    }

}
