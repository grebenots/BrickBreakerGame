package com.stoneberg.brickbreaker;

import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.AlphaComposite;

public class BrickBreakerGame extends Canvas implements Runnable {

    // Constants
    public static final Generic2D<Integer> SPRITE_SIZE = new Generic2D<Integer>(32,32);
    public static final Generic2D<Integer> BRICK_SIZE = new Generic2D<Integer>(32, SPRITE_SIZE.getY() / 2);
    public static final Generic2D<Double> WINDOW_SIZE = new Generic2D<Double>((double)SPRITE_SIZE.getX() * 13, ((double)SPRITE_SIZE.getX() * 13) * 12 / 9);
    public static final int SCALE = 1;
    public static final String TITLE = "Brick Breaker Game";

    // Game Loop and threading
    private boolean running = false;
    private Thread thread;

    // Logic
    private Controller gameController;

    // Graphics
    private Textures textures;

    // Other needed things
    private Player player;

    // Application entry
    public static void main (String args[]) {
        BrickBreakerGame game = new BrickBreakerGame();

        game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();
    }

    public void init() {
        requestFocus();

        // Attempt to get resources for the game
        try {
            textures = new Textures();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Setup the input system
        addKeyListener(new KeyInput(this));

        // Setup logic and components
        //player = new Player(120,500, this);
        //gameController = new Controller(this);
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
        g.drawImage(textures.blackBackground, 0, 0, getWidth(), getHeight(), this);  // Black background

        // Opacity test stuff
        float alpha = 0.1f;
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        Graphics2D gg = (Graphics2D)g;
        gg.setComposite(ac);
        gg.drawImage(textures.background, 0,0, getWidth(), getHeight(), null);
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

        } else if(key == KeyEvent.VK_SPACE && !isShooting) {
            isShooting = true;
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

        } else if(key == KeyEvent.VK_SPACE) {
            isShooting = false;
        }
    }



    public Textures getTextures() {
        return textures;
    }

}