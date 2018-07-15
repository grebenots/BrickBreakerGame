package com.stoneberg.brickbbbreaker;

import java.awt.*;

public class ApplicationEntry {

    private boolean running = false;
    private Thread thread;

    public static void main(String[] args) {
        // Initialize the game instance
        BrickBBBreaker.getCurrentGame();
    }
}
