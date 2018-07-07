package com.brickbbbreaker.src.main;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.AlphaComposite;

public class BrickBBBreaker extends Canvas implements Runnable {

    public static final int HEIGHT = 320;
    public static final int WIDTH = HEIGHT / 12 * 9;
    public static final int SCALE = 2;
    public static final String TITLE = "BrickBBBreaker";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private Player player;
    private Controller controller;

    public void init() {
        requestFocus();
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("spriteSheet.png");
            background = loader.loadImage("background.png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyInput(this));

        player = new Player(220,600,this);
        controller = new Controller(this);
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
        player.tick();
        controller.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        ///  All rendering goes here  ////
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);  // Black background

        // Opacity test stuff
        float alpha = 0.1f;
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        Graphics2D gg = (Graphics2D)g;
        gg.setComposite(ac);
        gg.drawImage(background, 0,0, getWidth(), getHeight(), null);
        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
        gg.setComposite(ac);
        // End opacity test

        player.render(g);
        controller.render(g);

        ///  End of rendering section  ///

        g.dispose();
        bs.show();
    }

    private void trackFPS() {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            player.setVelocityX(5);
        } else if(key == KeyEvent.VK_LEFT) {
            player.setVelocityX(-5);
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        } else if(key == KeyEvent.VK_SPACE) {
            controller.addBullet(new Bullet(player.getX(), player.getY(), this));
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_RIGHT) {
            player.setVelocityX(0);
        } else if(key == KeyEvent.VK_LEFT) {
            player.setVelocityX(0);
        } else if(key == KeyEvent.VK_DOWN) {

        } else if(key == KeyEvent.VK_UP) {

        }
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

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }
}
