package com.stoneberg.brickbbbreaker;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class BrickBBBreaker {

    /* Singleton pattern for instance of the current game
       ==================================================

       Using this pattern for three reasons.

       First, we only ever want a single instance of the current game.
       Second, and more importantly, we want an easy way for all of the other classes to grab information from the game
           instance.  We would be passing an instance of the class in just about every method call anyways.  => Why not
           use the singleton pattern.
       Third, the pattern is thread safe and ensures only a single instance of the game is every created.
     */

    // The game instance
    private static volatile BrickBBBreaker currentGame;

    // Game components
    private GameController gameController;
    private InputController inputController;
    private Player player;

    // Sprite Sheets
    private SpriteSheet entitySheet;

    // Game Loop and threading
    private boolean running = false;
    private Thread thread;

    // Debug logging
    private LinkedList<DebugLog> log = new LinkedList<DebugLog>();

    private BrickBBBreaker(){
        sayHello();
        initComponents();
    }

    public static BrickBBBreaker getCurrentGame() {
        if(currentGame == null) {
            synchronized (BrickBBBreaker.class) {
                if(currentGame == null) {
                    currentGame = new BrickBBBreaker();
                }
            }
        }
        return currentGame;
    }



    public void tick() {
        //player.tick();
        //gameController.tick();
    }

    public void render() {
        //BufferStrategy bs = this.getBufferStrategy();

        //if (bs == null) {
        //    createBufferStrategy(3);
        //    return;
        //}
        //Graphics g = bs.getDrawGraphics();

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

        //BufferedImage blackBackground = new BufferedImage(BrickBreakerGame.WINDOW_SIZE.getX().intValue(), BrickBreakerGame.WINDOW_SIZE.getY().intValue(), BufferedImage.TYPE_INT_RGB);
        //g.drawImage(blackBackground, 0, 0, getWidth(), getHeight(), this);  // Black background
        //player.render(g);
        //gameController.render(g);


        // Temp rendering stuff
        //brick1.render(g);
        //brick2.render(g);
        //brick3.render(g);
        //brick4.render(g);
        //brick5.render(g);
        //brick6.render(g);

        ///  End of rendering section  ///

        //g.dispose();
        //bs.show();
    }






    public GameController getGameController() {
        return gameController;
    }

    public Player getPlayer() {
        return player;
    }

    private void sayHello() {
        System.out.println("BrickBBBreaker Singleton says Hello World!");

        AddDebugLog("Said Hello World");
    }

    private void initComponents() {
        gameController = new GameController();
        player = new Player();

        // Attempt to get resources for the game
        try {
            entitySheet = new SpriteSheet("entitySheet.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        AddDebugLog("Initialized Components");
    }

    public void AddDebugLog(String message) {
        log.add(new DebugLog(message));
    }
}