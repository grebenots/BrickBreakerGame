package com.stoneberg.brickbbbreaker;

public class Player extends Entity {

    private boolean isShooting;
    private int currentLevel;
    private int currentScore;
    private int numLives;

    public Player(double x, double y) {
        super();
        position.set(x,y);
        velocity.set(0.0, 0.0);
        isShooting = false;
        currentScore = 0;
        currentLevel = 0;
        numLives = 3;

        sprites.put("paddleLeft", theStinkyCheese.getSpriteSheet().getSprite(1,1,32,32));
        sprites.put("paddleCenter", theStinkyCheese.getSpriteSheet().getSprite(2,1,32,32));
        sprites.put("paddleRight", theStinkyCheese.getSpriteSheet().getSprite(3,1,32,32));
    }

    public void tick() {
        updateTheCheese();

        double x = position.getX();
        double y = position.getY();

        x += velocity.getX();
        y += velocity.getY();

        if(x <= theStinkyCheese.SPRITE_SIZE.getX() / 2)
            x = theStinkyCheese.SPRITE_SIZE.getX() / 2;  // Restricts the player from moving off the left of screen

        if(x >= theStinkyCheese.WINDOW_SIZE.getX() - theStinkyCheese.SPRITE_SIZE.getX())
            x = theStinkyCheese.WINDOW_SIZE.getX() - theStinkyCheese.SPRITE_SIZE.getX();  // Restricts the player from moving off the right of screen

        position.set(x,y);
    }

    public void render() {
        updateTheCheese();

        double x = position.getX();
        double y = position.getY();

        theStinkyCheese.getGraphics().drawImage(sprites.get("paddleLeft"), (int)x - 32, (int)y, null);
        theStinkyCheese.getGraphics().drawImage(sprites.get("paddleCenter"),(int)x,(int)y,null);
        theStinkyCheese.getGraphics().drawImage(sprites.get("paddleRight"), (int)x + 32, (int)y, null);
    }

    // Not sure if I will implement this here or not
    //public void fireBullet() {
        // NEED TO MOVE CODE FOR FIRING FROM THE GAME CONTROLLER TO HERE
        //game.getGameController().addBullet(new Bullet(game.getPlayer().getPosition().getX(), game.getPlayer().getPosition().getY(), game.getEntitySheet()));
    //}

    public boolean getIsShooting() {
        return isShooting;
    }

    public void setIsShooting(boolean isShooting) {
        this.isShooting = isShooting;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getNumLives() {
        return numLives;
    }

    public Generic2D<Double> getVelocity() {
        return velocity;
    }

    public Generic2D<Double> getPosition() {
        return position;
    }
}
