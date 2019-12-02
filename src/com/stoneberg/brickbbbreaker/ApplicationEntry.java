/*  Tim Stoneberg
    Loyola University Chicago
    Summer Semester 2018
    Final Project

    Title:  BrickBBBreaker
    Purpose:  A clone and modification of the classic brick breaking, paddle moving, physics game

    Additional notes:
    I followed a YouTube tutorial to learn about implementing threading, fps management, and other useful game
    programming information at https://www.youtube.com/watch?v=hXImR8Wm53M&list=PLWms45O3n--6KCNAEETGiVTEFvnqA7qCi

    The tutorial creates a Space Warfare game.  The threading logic seen in this class is a rehash of some of that
    tutorial code.  The logic for the brick breaking game however, as seen in the rest of the classes, are all
    my own.
 */

package com.stoneberg.brickbbbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class ApplicationEntry extends Canvas implements Runnable {

    // Threading
    private boolean running = false;
    private Thread thread;

    // Singleton game instance
    private BrickBBBreaker theStinkyCheese;

    // Application entry point
    public static void main(String[] args) {
        ApplicationEntry application = new ApplicationEntry();  // Application instance
        application.addKeyListener(application.theStinkyCheese.getInputController());

        // Configure the game game window
        application.setPreferredSize(new Dimension((int)(BrickBBBreaker.WINDOW_SIZE.getX() * BrickBBBreaker.SCALE), (int)(BrickBBBreaker.WINDOW_SIZE.getY() * BrickBBBreaker.SCALE)));
        application.setMaximumSize(new Dimension(new Dimension((int)(BrickBBBreaker.WINDOW_SIZE.getX() * BrickBBBreaker.SCALE), (int)(BrickBBBreaker.WINDOW_SIZE.getY() * BrickBBBreaker.SCALE))));
        application.setMinimumSize(new Dimension(new Dimension((int)(BrickBBBreaker.WINDOW_SIZE.getX() * BrickBBBreaker.SCALE), (int)(BrickBBBreaker.WINDOW_SIZE.getY() * BrickBBBreaker.SCALE))));

        JFrame frame = new JFrame(BrickBBBreaker.TITLE);
        frame.add(application);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        application.start();
    }

    private ApplicationEntry() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();  // Initialize the singleton game instance
    }

    // All ticking goes here
    private void tick() {

        switch(theStinkyCheese.getCurrentState()) {  // Begin State Machine
            case MENU:
                tickMenuState();
                break;
            case LOADLEVEL:
                tickLoadLevelState();
                break;
            case GAME:
                tickGameState();
                break;
            case PAUSED:
                tickPauseState();
                break;
        }
    }

    private void tickMenuState() {

    }

    private void tickGameState() {
        // Tick player
        theStinkyCheese.getPlayer().tick();

        // Tick ball
        theStinkyCheese.getBall().tick();
    }

    private void tickPauseState() {

    }

    private void tickLoadLevelState() {

    }

    // All rendering goes here
    private void render() {

        // Triple graphics buffering
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        // Set background to black
        theStinkyCheese.setGraphics(bs.getDrawGraphics());
        BufferedImage blackBackground = new BufferedImage(BrickBBBreaker.WINDOW_SIZE.getX().intValue(), BrickBBBreaker.WINDOW_SIZE.getY().intValue(), BufferedImage.TYPE_INT_RGB);
        theStinkyCheese.getGraphics().drawImage(blackBackground, 0, 0, BrickBBBreaker.WINDOW_SIZE.getX().intValue(), BrickBBBreaker.WINDOW_SIZE.getY().intValue(),null);

        //  All additional rendering goes here
        switch(theStinkyCheese.getCurrentState()) {  // Begin State Machine
            case MENU:
                renderMenuState();
                break;
            case LOADLEVEL:
                renderLoadLevelState();
                break;
            case GAME:
                renderGameState();
                break;
            case PAUSED:
                renderPauseState();
                break;
        }
        // End of rendering section

        theStinkyCheese.getGraphics().dispose();
        bs.show();
    }

    private void renderMenuState() {
        theStinkyCheese.getMainMenuUI().render();
    }

    private void renderGameState() {
        theStinkyCheese.getGameUI().render();
        theStinkyCheese.getPlayer().render();
        theStinkyCheese.getBall().render();
    }

    private void renderPauseState() {

    }

    private void renderLoadLevelState() {
        theStinkyCheese.getLoadLevelUI().render();
        theStinkyCheese.getPlayer().render();
    }


    private void init() {
        requestFocus();
        detectRefreshRate();
    }

    private double detectRefreshRate() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        int refreshRate = 60;
        for (int i = 0; i < gs.length; i++) {
            DisplayMode dm = gs[i].getDisplayMode();

            refreshRate = dm.getRefreshRate();
            if (refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
                System.out.println("Unknown refresh rate detected.  Using 60 Hz as a default.");
                return 60.0;
            }
        }
        System.out.println("Refresh rate detected:  " + refreshRate + " Hz");
        return refreshRate;
    }


    private synchronized void start() {
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void run() {
        init();
        long lastTime = System.nanoTime();
        //final double targetFPS = detectRefreshRate();
        final double targetFPS = 60;  // Hard locking FPS because the game is very tough on my 144hz monitor
        double ns = 1000000000 / targetFPS;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {

            /* The following section of code limits updating to the target frames per second */
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " Ticks, FPS " + frames);
                updates = 0;
                frames = 0;
            }
            /* End section */
        }
        stop();
    }
}
