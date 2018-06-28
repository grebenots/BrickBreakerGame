package com.brickbbbreaker.src.main;

import java.awt.*;
import javax.swing.*;

public class BrickBBBreaker extends Canvas implements Runnable {

    public static final int HEIGHT = 320;
    public static final int WIDTH = HEIGHT / 12 * 9;
    public static final int SCALE = 2;
    public static final String TITLE = "BrickBBBreaker";

    private boolean running = false;
    private Thread thread;

    private synchronized void start() {
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if(!running)
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
        long lastTime = System.nanoTime();
        final double targetFPS = 60.0;
        double ns = 1000000000 / targetFPS;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running) {
            trackFPS();

            /* The following section of code limits updating to the target frames per second */
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " Ticks, FPS " + frames);
                updates = 0;
                frames = 0;
            }
            /* End section */
        }
        stop();
    }

    private void tick() {

    }

    private void render() {

    }

    private void trackFPS() {

    }

    public static void main (String args[]) {
        BrickBBBreaker brickBBBreaker = new BrickBBBreaker();

        brickBBBreaker.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        brickBBBreaker.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        brickBBBreaker.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(brickBBBreaker.TITLE);
        frame.add(brickBBBreaker);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        brickBBBreaker.start();
    }
}
