package com.stoneberg.brickbbbreaker;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputController extends KeyAdapter {

    protected BrickBBBreaker theStinkyCheese = null;

    public InputController() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            theStinkyCheese.getPlayer().getVelocity().setX(5.0);
        } else if(key == KeyEvent.VK_LEFT) {
            theStinkyCheese.getPlayer().getVelocity().setX(-5.0);
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        } else if(key == KeyEvent.VK_SPACE && !theStinkyCheese.getPlayer().getIsShooting()) {
            theStinkyCheese.getPlayer().setIsShooting(true);
            //theStinkyCheese.getGameController().addBullet(new Bullet(game.getPlayer().getPosition().getX(), game.getPlayer().getPosition().getY(), game.getEntitySheet()));
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            theStinkyCheese.getPlayer().getVelocity().setX(0.0);
        } else if(key == KeyEvent.VK_LEFT) {
            theStinkyCheese.getPlayer().getVelocity().setX(0.0);
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        } else if(key == KeyEvent.VK_SPACE) {
            theStinkyCheese.getPlayer().setIsShooting(false);
        }
    }
}
