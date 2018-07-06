package com.brickbbbreaker.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    BrickBBBreaker game;

    public KeyInput(BrickBBBreaker game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        game.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        game.keyReleased(e);
    }
}
