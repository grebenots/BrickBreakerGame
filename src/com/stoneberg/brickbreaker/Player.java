package com.stoneberg.brickbreaker;

import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Player extends Entity {

    // Physics
    private Generic2D<Double> position;
    private Generic2D<Double> velocity;

    public Player(double x, double y, SpriteSheet spriteSheet) {
        super();
        position = new Generic2D<>(x,y);
        velocity = new Generic2D<>(0.0,0.0);

        BufferedImage test = spriteSheet.getSprite(1,1,32,32);
        sprites.put("paddleLeft", test);
        sprites.put("paddleCenter", spriteSheet.getSprite(2,1,32,32));
        sprites.put("paddleRight", spriteSheet.getSprite(3,1,32,32));
    }

    public void tick() {
        double x = position.getX();
        double y = position.getY();

        x += velocity.getX();
        y += velocity.getY();

        if(x <= BrickBreakerGame.SPRITE_SIZE.getX() / 2)
            x = BrickBreakerGame.SPRITE_SIZE.getX() / 2;  // Restricts the player from moving off the left of screen

        if(x >= BrickBreakerGame.WINDOW_SIZE.getX() - BrickBreakerGame.SPRITE_SIZE.getX())
            x = BrickBreakerGame.WINDOW_SIZE.getX() - BrickBreakerGame.SPRITE_SIZE.getX();  // Restricts the player from moving off the right of screen

        position.set(x,y);
    }

    public void render(Graphics graphics) {
        // To keep things looking clean
        double x = position.getX();
        double y = position.getY();

        graphics.drawImage(sprites.get("paddleLeft"), (int)x - 32, (int)y, null);
        graphics.drawImage(sprites.get("paddleCenter"),(int)x,(int)y,null);
        graphics.drawImage(sprites.get("paddleRight"), (int)x + 32, (int)y, null);
    }
}
