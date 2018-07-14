package com.stoneberg.brickbreaker;

import java.awt.event.KeyEvent;

public class InputController {

    public void keyPressed(KeyEvent e, BrickBreakerGame game) {

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            game.getPlayer().getVelocity().setX(5.0);
        } else if(key == KeyEvent.VK_LEFT) {
            game.getPlayer().getVelocity().setX(-5.0);
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        } else if(key == KeyEvent.VK_SPACE && !game.getPlayer().getIsShooting()) {
            game.getPlayer().setIsShooting(true);
            //controller.addBullet(new Bullet(player.getX(), player.getY(), this));
        }
    }

    public void keyReleased(KeyEvent e, BrickBreakerGame game) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            game.getPlayer().getVelocity().setX(0.0);
        } else if(key == KeyEvent.VK_LEFT) {
            game.getPlayer().getVelocity().setX(0.0);
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        } else if(key == KeyEvent.VK_SPACE) {
            game.getPlayer().setIsShooting(false);
        }
    }
}
