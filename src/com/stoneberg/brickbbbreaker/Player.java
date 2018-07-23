package com.stoneberg.brickbbbreaker;

import java.awt.*;

public class Player extends Entity {

    private boolean isShooting;
    private int currentLevel;
    private int currentScore;
    private int numLives;
    private Direction horizontalDirection;
    private float moveEase;

    public Player(double x, double y) {
        super();
        position.set(x,y);
        velocity.set(0.0, 0.0);
        isShooting = false;
        currentScore = 0;
        currentLevel = 0;
        numLives = 3;
        horizontalDirection = Direction.NONE;
        moveEase = 0.49f;

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

        if(x <= theStinkyCheese.SPRITE_SIZE.getX() / 2 + theStinkyCheese.SPRITE_SIZE.getX())
            x = theStinkyCheese.SPRITE_SIZE.getX() / 2 + theStinkyCheese.SPRITE_SIZE.getX();  // Restricts the player from moving off the left of screen

        if(x >= theStinkyCheese.WINDOW_SIZE.getX() - theStinkyCheese.SPRITE_SIZE.getX() * 2.5)
            x = theStinkyCheese.WINDOW_SIZE.getX() - theStinkyCheese.SPRITE_SIZE.getX() * 2.5;  // Restricts the player from moving off the right of screen

        position.set(x,y);

        System.out.println("moveEase == " + moveEase);
        moveEase = easeInOutQuint(moveEase);
    }

    public void render() {
        updateTheCheese();

        double x = position.getX();
        double y = position.getY();

        theStinkyCheese.getGraphics().drawImage(sprites.get("paddleLeft"), (int)x - 32, (int)y, null);
        theStinkyCheese.getGraphics().drawImage(sprites.get("paddleCenter"),(int)x,(int)y,null);
        theStinkyCheese.getGraphics().drawImage(sprites.get("paddleRight"), (int)x + 32, (int)y, null);

        Rectangle rect = bounds();
        theStinkyCheese.getGraphics().setColor(Color.RED);
        theStinkyCheese.getGraphics().drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    public void moveLeft() {
        horizontalDirection = Direction.LEFT;
        velocity.setX(-5.0);
    }

    public void moveRight() {
        horizontalDirection = Direction.RIGHT;
        velocity.setX(5.0);
    }

    public void stopMoving() {
        horizontalDirection = Direction.NONE;
        velocity.setX(0.0);
    }


    // Not sure if I will implement this here or not
    //public void fireBullet() {
        // NEED TO MOVE CODE FOR FIRING FROM THE GAME CONTROLLER TO HERE
        //game.getGameController().addBullet(new Bullet(game.getPlayer().getPosition().getX(), game.getPlayer().getPosition().getY(), game.getEntitySheet()));
    //}


    public Rectangle bounds() {
        return new Rectangle(position.getX().intValue() - 16, position.getY().intValue() + 8, 64, 16);
    }

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

    private float easeInOutQuint(float t) {
        return t<.5 ? 16*easeInOutQuint(t)*easeInOutQuint(t)*easeInOutQuint(t)*easeInOutQuint(t)*easeInOutQuint(t) : 1+16*(--t)*easeInOutQuint(t)*easeInOutQuint(t)*easeInOutQuint(t)*easeInOutQuint(t);
    }
}
