package com.brickbbbreaker.src.main;

import java.util.LinkedList;
import java.awt.Graphics;

public class Controller {

    private BrickBBBreaker game;
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private Bullet tempBullet;

    public Controller(BrickBBBreaker game) {
        this.game = game;
    }

    public void tick() {
        for(int i = 0; i < bullets.size(); i++) {
            tempBullet = bullets.get(i);

            if(tempBullet.getY() < 0)
                removeBullet(tempBullet);

            tempBullet.tick();
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < bullets.size(); i++) {
            tempBullet = bullets.get(i);
            tempBullet.render(g);
        }
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public void removeBullet(Bullet b) {
        bullets.remove(b);
    }
}
