package com.stoneberg.brickbreaker;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    BrickBreakerGame game;

    public KeyInput(BrickBreakerGame game) {
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        game.getInputController().keyPressed(e, game);
    }

    public void keyReleased(KeyEvent e) {
        game.getInputController().keyReleased(e, game);
    }
}