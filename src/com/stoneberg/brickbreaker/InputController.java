package com.stoneberg.brickbreaker;

import java.awt.event.KeyEvent;

public class InputController {

    BrickBreakerGame game;

    public InputController(BrickBreakerGame game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            game.getPlayer().getVelocity().setX(5.0);
        } else if(key == KeyEvent.VK_LEFT) {
            game.getPlayer().getVelocity().setX(-5.0);
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        } else if(key == KeyEvent.VK_SPACE && !game.getPlayer().getIsShooting()) {
            game.getPlayer().setIsShooting(true);
            game.getGameController().addBullet(new Bullet(game.getPlayer().getPosition().getX(), game.getPlayer().getPosition().getY(), game.getEntitySheet()));
        }
    }

    public void keyReleased(KeyEvent e) {
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
