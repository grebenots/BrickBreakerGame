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

        // Initialize the singleton game instance
        BrickBBBreaker theStinkyCheese = BrickBBBreaker.getCurrentGame();

        application.setPreferredSize(new Dimension((int)(theStinkyCheese.WINDOW_SIZE.getX() * theStinkyCheese.SCALE), (int)(theStinkyCheese.WINDOW_SIZE.getY() * theStinkyCheese.SCALE)));
        application.setMaximumSize(new Dimension(new Dimension((int)(theStinkyCheese.WINDOW_SIZE.getX() * theStinkyCheese.SCALE), (int)(theStinkyCheese.WINDOW_SIZE.getY() * theStinkyCheese.SCALE))));
        application.setMinimumSize(new Dimension(new Dimension((int)(theStinkyCheese.WINDOW_SIZE.getX() * theStinkyCheese.SCALE), (int)(theStinkyCheese.WINDOW_SIZE.getY() * theStinkyCheese.SCALE))));

        JFrame frame = new JFrame(theStinkyCheese.TITLE);
        frame.add(application);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        application.start();
    }

    private ApplicationEntry() {
        // Initialize the game instance
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
    }

    private void tick() {
        theStinkyCheese.getPlayer().tick();
        theStinkyCheese.getGameController().tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        //  All rendering goes here
        theStinkyCheese.setGraphics(bs.getDrawGraphics());
        theStinkyCheese.getGameController().render();


//        player.render(graphics);
//        gameController.render(graphics);
//
//
//         Temp rendering stuff
//        brick1.render(graphics);
//        brick2.render(graphics);
//        brick3.render(graphics);
//        brick4.render(graphics);
//        brick5.render(graphics);
//        brick6.render(graphics);


        // End of rendering section

          theStinkyCheese.getGraphics().dispose();
          bs.show();


//        graphics.drawImage(textures.blackBackground, 0, 0, getWidth(), getHeight(), this);  // Black background
//
//        // Opacity test stuff
//        float alpha = 0.1f;
//        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
//        Graphics2D gg = (Graphics2D)graphics;
//        gg.setComposite(ac);
//        gg.drawImage(textures.background, 0,0, getWidth(), getHeight(), null);
//        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
//        gg.setComposite(ac);
//        // End opacity test
//
//        BufferedImage blackBackground = new BufferedImage(BrickBreakerGame.WINDOW_SIZE.getX().intValue(), BrickBreakerGame.WINDOW_SIZE.getY().intValue(), BufferedImage.TYPE_INT_RGB);
//        graphics.drawImage(blackBackground, 0, 0, getWidth(), getHeight(), this);  // Black background
//        player.render(graphics);
//        gameController.render(graphics);
//
//
//         Temp rendering stuff
//        brick1.render(graphics);
//        brick2.render(graphics);
//        brick3.render(graphics);
//        brick4.render(graphics);
//        brick5.render(graphics);
//        brick6.render(graphics);
//
//        /  End of rendering section  ///
//
//        graphics.dispose();
//        bs.show();
    }



    private void init() {  // NOT DONE
        requestFocus();
        // Setup the input system
        //addKeyListener(new KeyInput(this));
        //inputController = new InputController(this);

        // Setup logic and components
        //gameController = new GameController(this);
        //player = new Player(WINDOW_CENTER.getX() - SPRITE_SIZE.getX() / 2, WINDOW_SIZE.getY() - SPRITE_SIZE.getY(), entitySheet);

        // Brick Testing
//        brick1 = new Brick(1,1, entitySheet);
//        brick2 = new Brick(2,1, entitySheet);
//        brick3 = new Brick(3,1, entitySheet);
//        brick4 = new Brick(2,2, entitySheet);
//        brick5 = new Brick(3,2, entitySheet);
//        brick6 = new Brick(11,2, entitySheet);

        //gameController = new Controller(this);
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
