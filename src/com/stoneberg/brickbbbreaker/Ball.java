package com.stoneberg.brickbbbreaker;

public class Ball extends Entity {

    public Ball() {
        super();
        position.set(0.0, 0.0);
        velocity.set(0.0, 0.0);

        sprites.put("ballSmall", theStinkyCheese.getSpriteSheet().getSprite(6,1,32,32));
    }

    public void beginLevel() {
        Generic2D<Double> playerPosition = theStinkyCheese.getPlayer().getPosition();
        position.set(playerPosition.getX(), playerPosition.getY() - 16);
        velocity = new Generic2D<Double>(2.0, -2.0);
    }


    public void tick() {
        updateTheCheese();

        double x = position.getX();
        double y = position.getY();

        x += velocity.getX();
        y += velocity.getY();
//
//        if(x <= theStinkyCheese.SPRITE_SIZE.getX() / 2)
//            x = theStinkyCheese.SPRITE_SIZE.getX() / 2;  // Restricts the player from moving off the left of screen
//
//        if(x >= theStinkyCheese.WINDOW_SIZE.getX() - theStinkyCheese.SPRITE_SIZE.getX())
//            x = theStinkyCheese.WINDOW_SIZE.getX() - theStinkyCheese.SPRITE_SIZE.getX();  // Restricts the player from moving off the right of screen
//
        position.set(x,y);
    }

    public void render() {
        updateTheCheese();

        double x = position.getX();
        double y = position.getY();

        theStinkyCheese.getGraphics().drawImage(sprites.get("ballSmall"), (int)x, (int)y, null);
    }

    public Generic2D<Double> getVelocity() {
        return velocity;
    }

    public Generic2D<Double> getPosition() {
        return position;
    }
}