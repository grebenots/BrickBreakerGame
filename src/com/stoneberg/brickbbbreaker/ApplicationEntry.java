package com.stoneberg.brickbbbreaker;

import javax.swing.*;
import java.awt.*;

public class ApplicationEntry extends Canvas implements Runnable {

    // Constants
    public static final Generic2D<Integer> SPRITE_SIZE = new Generic2D<Integer>(32, 32);
    public static final Generic2D<Integer> BRICK_SIZE = new Generic2D<Integer>(32, SPRITE_SIZE.getY() / 2);
    public static final Generic2D<Double> WINDOW_SIZE = new Generic2D<Double>((double) SPRITE_SIZE.getX() * 13, ((double) SPRITE_SIZE.getX() * 13) * 12 / 9);
    public static final Generic2D<Double> WINDOW_CENTER = new Generic2D<Double>(WINDOW_SIZE.getX() / 2, WINDOW_SIZE.getY() / 2);
    public static final double SCALE = 1;
    public static final String TITLE = "Brick Breaker Game";

    // Threading
    private boolean running = false;
    private Thread thread;

    public static void main(String[] args) {
        // Application
        ApplicationEntry application = new ApplicationEntry();

        // Initialize the singleton game instance
        BrickBBBreaker theStinkyCheese = BrickBBBreaker.getCurrentGame();

        application.setPreferredSize(new Dimension((int)(WINDOW_SIZE.getX() * SCALE), (int)(WINDOW_SIZE.getY() * SCALE)));
        application.setMaximumSize(new Dimension(new Dimension((int)(WINDOW_SIZE.getX() * SCALE), (int)(WINDOW_SIZE.getY() * SCALE))));
        application.setMinimumSize(new Dimension(new Dimension((int)(WINDOW_SIZE.getX() * SCALE), (int)(WINDOW_SIZE.getY() * SCALE))));

        JFrame frame = new JFrame(TITLE);
        frame.add(application);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        application.start();
    }

    public void init() {  // NOT DONE
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
        BrickBBBreaker theStinkyCheese = BrickBBBreaker.getCurrentGame();
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
                theStinkyCheese.tick();
                updates++;
                delta--;
            }
            theStinkyCheese.render();
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
