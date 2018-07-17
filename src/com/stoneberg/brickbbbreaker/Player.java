package com.stoneberg.brickbbbreaker;

public class Player extends Entity {

    private boolean isShooting;

    public Player(double x, double y) {
        super();
        position.set(x,y);
        velocity.set(0.0, 0.0);
        isShooting = false;

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
}
