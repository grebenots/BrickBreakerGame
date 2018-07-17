package com.stoneberg.brickbbbreaker;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputController extends KeyAdapter {

    protected BrickBBBreaker theStinkyCheese = null;

    public InputController() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }

    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyChar());
    }
}
