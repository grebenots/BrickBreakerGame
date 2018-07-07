package com.brickbbbreaker.src.main;

import java.util.LinkedList;
import java.awt.Graphics;

public class Controller {

    private BrickBBBreaker game;
    private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
    private LinkedList<Brick> bricks = new LinkedList<Brick>();
    private LinkedList<UIBrick> uiBricks = new LinkedList<UIBrick>();
    private Bullet tempBullet;
    private Brick tempBrick;

    public Controller(BrickBBBreaker game) {
        this.game = game;
        addBrick(new Brick(0,0, game));
        addBrick(new Brick(1,0, game));
        addBrick(new Brick(2,0, game));
        addBrick(new Brick(3,0, game));
        addBrick(new Brick(4,0, game));
        addBrick(new Brick(5,0, game));
        addBrick(new Brick(6,0, game));
        addBrick(new Brick(7,0, game));
        addBrick(new Brick(8,0, game));
        addBrick(new Brick(9,0, game));
        addBrick(new Brick(10,0, game));

        addBrick(new Brick(1,1, game));
        addBrick(new Brick(2,1, game));
        addBrick(new Brick(4,1, game));

        addBrick(new Brick(1,2, game));
        addBrick(new Brick(2,2, game));
        addBrick(new Brick(4,2, game));

//        addUIBrick(new UIBrick(0,0, game));
//        addUIBrick(new UIBrick(0,1, game));
//        addUIBrick(new UIBrick(0,2, game));
//        addUIBrick(new UIBrick(0,3, game));
//        addUIBrick(new UIBrick(0,4, game));
//        addUIBrick(new UIBrick(0,5, game));
//        addUIBrick(new UIBrick(0,6, game));
//        addUIBrick(new UIBrick(0,7, game));
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

    public void addUIBrick(UIBrick b) {
        uiBricks.add(b);
    }

    private void setupStage() {

    }
}
