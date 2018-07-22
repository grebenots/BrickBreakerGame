/*  Tim Stoneberg
    COMP 170, Intro to Object Oriented Programming

    or as I like to call it, "Learning to make cars, and trucks, and boats out of generic vehicle Play-Doh 101"

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
import java.util.Map;

public class ApplicationEntry extends Canvas implements Runnable {

    // Threading
    private boolean running = false;
    private Thread thread;

    // Singleton game instance
    private BrickBBBreaker theStinkyCheese;

    // Application entry point
    public static void main(String[] args) {
        // Application instance
        ApplicationEntry application = new ApplicationEntry();

        // Initialize input controller
        application.addKeyListener(application.theStinkyCheese.getInputController());

        // Configure the game game window
        application.setPreferredSize(new Dimension((int)(application.theStinkyCheese.WINDOW_SIZE.getX() * application.theStinkyCheese.SCALE), (int)(application.theStinkyCheese.WINDOW_SIZE.getY() * application.theStinkyCheese.SCALE)));
        application.setMaximumSize(new Dimension(new Dimension((int)(application.theStinkyCheese.WINDOW_SIZE.getX() * application.theStinkyCheese.SCALE), (int)(application.theStinkyCheese.WINDOW_SIZE.getY() * application.theStinkyCheese.SCALE))));
        application.setMinimumSize(new Dimension(new Dimension((int)(application.theStinkyCheese.WINDOW_SIZE.getX() * application.theStinkyCheese.SCALE), (int)(application.theStinkyCheese.WINDOW_SIZE.getY() * application.theStinkyCheese.SCALE))));

        JFrame frame = new JFrame(application.theStinkyCheese.TITLE);
        frame.add(application);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        application.start();
    }

    private ApplicationEntry() {
        // Initialize the singleton game instance
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
    }

    // All ticking
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

        // Tick bricks


        // Tick ball
        theStinkyCheese.getBall().tick();


        // Tick More Stuff...



    }

    private void tickPauseState() {

    }

    private void tickLoadLevelState() {

    }

    // All rendering
    private void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        theStinkyCheese.setGraphics(bs.getDrawGraphics());
        BufferedImage blackBackground = new BufferedImage(theStinkyCheese.WINDOW_SIZE.getX().intValue(), theStinkyCheese.WINDOW_SIZE.getY().intValue(), BufferedImage.TYPE_INT_RGB);
        theStinkyCheese.getGraphics().drawImage(blackBackground, 0, 0, theStinkyCheese.WINDOW_SIZE.getX().intValue(), theStinkyCheese.WINDOW_SIZE.getY().intValue(),null);

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
        // Render UI
        theStinkyCheese.getGameUI().render();

        // Render player
        theStinkyCheese.getPlayer().render();

        // Render ball
        theStinkyCheese.getBall().render();


        // Render More Stuff...

    }

    private void renderPauseState() {

    }

    private void renderLoadLevelState() {
        // Render UI
        theStinkyCheese.getLoadLevelUI().render();

        // Render player
        theStinkyCheese.getPlayer().render();
    }


    private void init() {  // NOT DONE
        requestFocus();  // NOT DONE
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
        final double targetFPS = 60.0;
        double ns = 1000000000 / targetFPS;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            // trackFPS();

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
