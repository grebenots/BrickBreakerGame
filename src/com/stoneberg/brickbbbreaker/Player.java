package com.stoneberg.brickbbbreaker;

public class Player extends Entity {

    private boolean isShooting;

    public Player(double x, double y) {
        super();
        position.set(x,y);
        velocity.set(0.0, 0.0);
        isShooting = false;

//        sprites.put("paddleLeft", theStinkyCheese.getSpriteSheet().getSprite(1,1,32,32));
//        sprites.put("paddleCenter", theStinkyCheese.getSpriteSheet().getSprite(2,1,32,32));
//        sprites.put("paddleRight", theStinkyCheese.getSpriteSheet().getSprite(3,1,32,32));
    }

    public void tick() {
        updateTheCheese();
    }

    public void render() {
        updateTheCheese();




    }
}
