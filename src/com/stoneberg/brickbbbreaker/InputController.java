package com.stoneberg.brickbbbreaker;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputController extends KeyAdapter {

    protected BrickBBBreaker theStinkyCheese = null;

    public InputController() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
    }

    public void keyPressed(KeyEvent e) {
        switch(theStinkyCheese.getCurrentState()) {
            case MENU:
                menuPressed(e);
                break;
            case GAME:
                gamePressed(e);
                break;
            case PAUSED:
                pausePressed(e);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(theStinkyCheese.getCurrentState()) {
            case MENU:
                menuReleased(e);
                break;
            case GAME:
                gameReleased(e);
                break;
            case PAUSED:
                pauseReleased(e);
                break;
        }
    }

    private void gamePressed(KeyEvent e) {
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

    private void gameReleased(KeyEvent e) {  // NOT DONE
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {  // Change to getPlayer().moveRight() etc...
            theStinkyCheese.getPlayer().getVelocity().setX(0.0);
        } else if(key == KeyEvent.VK_LEFT) {
            theStinkyCheese.getPlayer().getVelocity().setX(0.0);
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        } else if(key == KeyEvent.VK_SPACE) {
            theStinkyCheese.getPlayer().setIsShooting(false);
        }
    }

    private void menuPressed(KeyEvent e) {

    }

    private void menuReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_EQUALS || key == KeyEvent.VK_PLUS) {
            theStinkyCheese.getMainMenuUI().insertCoin();
        }
        if(key == KeyEvent.VK_ENTER) {
            theStinkyCheese.getMainMenuUI().useCredit();
        }
    }

    private void pausePressed(KeyEvent e) {

    }

    private void pauseReleased(KeyEvent e) {

    }
}
