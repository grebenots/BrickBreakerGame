package com.stoneberg.brickbreaker;

import java.awt.*;
import java.util.LinkedList;

public class GameController {

    private BrickBreakerGame game;
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private LinkedList<Brick> bricks = new LinkedList<Brick>();

    private Bullet tempBullet;
    private Brick tempBrick;

    public GameController(BrickBreakerGame game) {
        this.game = game;
    }

    public void tick() {
        for(int i = 0; i < bullets.size(); i++) {
            tempBullet = bullets.get(i);

            if(tempBullet.getPosition().getY() < 0)
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
}
