package com.stoneberg.brickbbbreaker;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputController extends KeyAdapter {

    protected BrickBBBreaker theStinkyCheese = null;
    private boolean leftPressed;
    private boolean rightPressed;

    private double startDuration;
    private double targetDuration;
    private double endDuration;


    public InputController() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
        leftPressed = false;
        rightPressed = false;

        startDuration = 0.0;
        targetDuration = .25;
        endDuration = 0.0;
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

    public float getEasement() {
        double current = System.nanoTime();
        return current > endDuration ? parametricEase(1.0f) : parametricEase((float)((current - startDuration) / (endDuration - startDuration)));

    }

    private float parametricEase(float t) {  // Borrowed from https://stackoverflow.com/questions/13462001/ease-in-and-ease-out-animation-formula
        float sqt = (float)Math.pow(t,2);
        return sqt / (2.0f * (sqt - t) + 1.0f);
    }

    private void gamePressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(startDuration == 0.0) {
            startDuration = System.nanoTime();
            endDuration = startDuration + targetDuration * 1000000000;
        }

        if(key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            theStinkyCheese.getPlayer().moveRight();
        } else if(key == KeyEvent.VK_LEFT) {
            leftPressed = true;
            theStinkyCheese.getPlayer().moveLeft();
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        } else if(key == KeyEvent.VK_SPACE && !theStinkyCheese.getPlayer().getIsShooting()) {
            //theStinkyCheese.getPlayer().setIsShooting(true);
            //theStinkyCheese.getGameController().addBullet(new Bullet(game.getPlayer().getPosition().getX(), game.getPlayer().getPosition().getY(), game.getEntitySheet()));
        }
    }

    private void gameReleased(KeyEvent e) {
        int key = e.getKeyCode();

        startDuration = 0.0;
        endDuration = 0.0;

        if(key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
            if(leftPressed) {
                theStinkyCheese.getPlayer().moveLeft();
            } else {
                theStinkyCheese.getPlayer().stopMoving();
            }
        } else if(key == KeyEvent.VK_LEFT) {
            leftPressed = false;
            if(rightPressed) {
                theStinkyCheese.getPlayer().moveRight();
            } else {
                theStinkyCheese.getPlayer().stopMoving();
            }
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
