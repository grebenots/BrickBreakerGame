package com.stoneberg.brickbbbreaker;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    // Constants
    public static final Generic2D<Integer> SPRITE_SIZE = new Generic2D<Integer>(32, 32);
    public static final Generic2D<Integer> BRICK_SIZE = new Generic2D<Integer>(32, SPRITE_SIZE.getY() / 2);
    public static final Generic2D<Double> WINDOW_SIZE = new Generic2D<Double>((double) SPRITE_SIZE.getX() * 13, ((double) SPRITE_SIZE.getX() * 13) * 12 / 9);
    public static final Generic2D<Double> WINDOW_CENTER = new Generic2D<Double>(WINDOW_SIZE.getX() / 2, WINDOW_SIZE.getY() / 2);
    public static final double SCALE = 1;
    public static final String TITLE = "BrickBBBreaker Game";

    // Game States
    public enum GameState {
        MENU,
        GAME,
        PAUSED
    }

    // Current Game State
    private GameState currentState;

    // Graphics
    private Graphics graphics;

    // Game components
    private GameController gameController;
    private InputController inputController;
    private MainMenuUI mainMenuUI;
    private GameUI gameUI;
    private PauseUI pauseUI;
    private Player player;

    // Levels
    private Map<Integer, Level> levels = null;

    // Sprite Sheets
    private SpriteSheet spriteSheet;

    // Game Loop and threading
    private boolean running = false;
    private Thread thread;

    // Debug logging
    private LinkedList<DebugLog> log = new LinkedList<DebugLog>();

    private BrickBBBreaker(){}

    public static BrickBBBreaker getCurrentGame() {
        if(currentGame == null) {
            synchronized (BrickBBBreaker.class) {
                if(currentGame == null) {
                    currentGame = new BrickBBBreaker();
                    currentGame.currentState = GameState.MENU;
                    currentGame.sayHello();
                    currentGame.initComponents();
                    currentGame.initLevels();
                }
            }
        }
        return currentGame;
    }


    public GameController getGameController() {
        return gameController;
    }

    public InputController getInputController() {
        return inputController;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public MainMenuUI getMainMenuUI() {
        return mainMenuUI;
    }

    public GameUI getGameUI() {
        return gameUI;
    }

    public PauseUI getPauseUI() {
        return pauseUI;
    }

    public Player getPlayer() {
        return player;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public Map<Integer, Level> getLevels() {
        return levels;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    private void sayHello() {
        System.out.println("BrickBBBreaker Singleton says Hello World!");

        AddDebugLog("Said Hello World", true);
    }

    private void initComponents() {
        // Attempt to get resources for the game
        try {
            spriteSheet = new SpriteSheet("spriteSheet.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        gameController = new GameController();
        inputController = new InputController();
        mainMenuUI = new MainMenuUI();
        gameUI = new GameUI();
        pauseUI = new PauseUI();
        player = new Player(WINDOW_CENTER.getX() - SPRITE_SIZE.getX() / 2, WINDOW_SIZE.getY() - SPRITE_SIZE.getY());

        AddDebugLog("Initialized Components", true);
    }

    private void initLevels() {
        levels = new HashMap<>();

        Level level = new Level(0);
        for(int i = 1; i < 12; i++) {
            level.addBrick(i,5,Brick.BrickType.BLUE);
            level.addBrick(i,6,Brick.BrickType.PURPLE);
            level.addBrick(i,7, Brick.BrickType.YELLOW);
            level.addBrick(i,8, Brick.BrickType.GREEN);
            level.addBrick(i,9, Brick.BrickType.RED);
        }

        levels.put(0, level);
    }

    public void AddDebugLog(String message) {
        log.add(new DebugLog(message));
    }

    public void AddDebugLog(String message, boolean printToConsole) {
        DebugLog newLog = new DebugLog(message);
        log.add(newLog);

        if(printToConsole)
            System.out.println(newLog.getMessage() + " ::: " + newLog.getTimestamp());
    }
}
