package com.brickbbbreaker.src.main;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import javax.swing.*;

public class BrickBBBreaker extends Canvas implements Runnable {

    public static final int HEIGHT = 320;
    public static final int WIDTH = HEIGHT / 12 * 9;
    public static final int SCALE = 2;
    public static final String TITLE = "BrickBBBreaker";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;

    private BufferedImage player;

    public void init() {
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("spriteSheet.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        SpriteSheet ss = new SpriteSheet(spriteSheet);
        player = ss.grabImage(1,1,32,32);
    }

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
        init();
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
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //////////////////////////////////

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(player, 100, 100, this);
        // All rendering goes here


        //////////////////////////////////
        g.dispose();
        bs.show();
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
