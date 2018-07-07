package com.brickbbbreaker.src.main;

import java.util.LinkedList;
import java.awt.Graphics;

public class Controller {

    private BrickBBBreaker game;
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private LinkedList<Brick> bricks = new LinkedList<Brick>();
    private Bullet tempBullet;
    private Brick tempBrick;

    public Controller(BrickBBBreaker game) {
        this.game = game;
        addBrick(new Brick(0,0, game));
        addBrick(new Brick(32,0, game));
    }

    public void tick() {
        for(int i = 0; i < bullets.size(); i++) {
            tempBullet = bullets.get(i);

            if(tempBullet.getY() < 0)
                removeBullet(tempBullet);

            tempBullet.tick();
        }

        for(int i = 0; i < bricks.size(); i++) {
            tempBrick = bricks.get(i);
            tempBrick.tick();
        }
    }

    public void render(Graphics g) {
        for(int i = 0; i < bullets.size(); i++) {
            tempBullet = bullets.get(i);
            tempBullet.render(g);
        }

        for(int i = 0; i < bricks.size(); i++) {
            tempBrick = bricks.get(i);
            tempBrick.render(g);
        }
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    public void removeBullet(Bullet b) {
        bullets.remove(b);
    }

    public void addBrick(Brick b) {
        bricks.add(b);
    }

    public void removeBrick(Brick b) {
        bricks.remove(b);
    }

    private void setupStage() {

    }
}
