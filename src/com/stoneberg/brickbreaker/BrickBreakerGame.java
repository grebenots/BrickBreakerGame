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
    public static final Generic2D<Integer> SPRITE_SIZE = new Generic2D<Integer>(32, 32);
    public static final Generic2D<Integer> BRICK_SIZE = new Generic2D<Integer>(32, SPRITE_SIZE.getY() / 2);
    public static final Generic2D<Double> WINDOW_SIZE = new Generic2D<Double>((double) SPRITE_SIZE.getX() * 13, ((double) SPRITE_SIZE.getX() * 13) * 12 / 9);
    public static final Generic2D<Double> WINDOW_CENTER = new Generic2D<Double>(WINDOW_SIZE.getX() / 2, WINDOW_SIZE.getY() / 2);
    public static final double SCALE = 1;
    public static final String TITLE = "Brick Breaker Game";

    // Game Loop and threading
    private boolean running = false;
    private Thread thread;

    // Input
    private InputController inputController;

    // Logic
    private GameController gameController;

    // Sprite Sheets
    private SpriteSheet entitySheet;
    private SpriteSheet uiSheet;

    // Other needed things
    private Player player;

    // Test stuff to delete
    private Brick brick1;
    private Brick brick2;
    private Brick brick3;
    private Brick brick4;
    private Brick brick5;
    private Brick brick6;

    // Application entry
    public static void main(String args[]) {
        BrickBreakerGame game = new BrickBreakerGame();

        game.setPreferredSize(new Dimension((int)(WINDOW_SIZE.getX() * SCALE), (int)(WINDOW_SIZE.getY() * SCALE)));
        game.setMaximumSize(new Dimension(new Dimension((int)(WINDOW_SIZE.getX() * SCALE), (int)(WINDOW_SIZE.getY() * SCALE))));
        game.setMinimumSize(new Dimension(new Dimension((int)(WINDOW_SIZE.getX() * SCALE), (int)(WINDOW_SIZE.getY() * SCALE))));

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
            entitySheet = new SpriteSheet("entitySheet.png");
            uiSheet = new SpriteSheet("uiSheet.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Setup the input system
        addKeyListener(new KeyInput(this));
        inputController = new InputController(this);

        // Setup logic and components
        gameController = new GameController(this);
        player = new Player(WINDOW_CENTER.getX() - SPRITE_SIZE.getX() / 2, WINDOW_SIZE.getY() - SPRITE_SIZE.getY(), entitySheet);

        // Brick Testing
        brick1 = new Brick(1,1, entitySheet);
        brick2 = new Brick(2,1, entitySheet);
        brick3 = new Brick(3,1, entitySheet);
        brick4 = new Brick(2,2, entitySheet);
        brick5 = new Brick(3,2, entitySheet);
        brick6 = new Brick(11,2, entitySheet);

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
            trackFPS();

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

    private void tick() {
        player.tick();
        gameController.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        ///  All rendering goes here  ////
//        g.drawImage(textures.blackBackground, 0, 0, getWidth(), getHeight(), this);  // Black background
//
//        // Opacity test stuff
//        float alpha = 0.1f;
//        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
//        Graphics2D gg = (Graphics2D)g;
//        gg.setComposite(ac);
//        gg.drawImage(textures.background, 0,0, getWidth(), getHeight(), null);
//        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
//        gg.setComposite(ac);
//        // End opacity test

        BufferedImage blackBackground = new BufferedImage(BrickBreakerGame.WINDOW_SIZE.getX().intValue(), BrickBreakerGame.WINDOW_SIZE.getY().intValue(), BufferedImage.TYPE_INT_RGB);
        g.drawImage(blackBackground, 0, 0, getWidth(), getHeight(), this);  // Black background
        player.render(g);
        gameController.render(g);


        // Temp rendering stuff
        brick1.render(g);
        brick2.render(g);
        brick3.render(g);
        brick4.render(g);
        brick5.render(g);
        brick6.render(g);

        ///  End of rendering section  ///

        g.dispose();
        bs.show();
    }

    private void trackFPS() {

    }

    public InputController getInputController() {
        return inputController;
    }

    public Player getPlayer() {
        return this.player;
    }

    public GameController getGameController() {
        return this.gameController;
    }

    public SpriteSheet getEntitySheet() {
        return entitySheet;
    }
}
