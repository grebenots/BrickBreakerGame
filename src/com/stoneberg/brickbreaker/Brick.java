package com.stoneberg.brickbreaker;

import java.awt.*;

public class Brick extends Entity {

    // Physics
    private Generic2D<Integer> wallCoordinate;
    private Generic2D<Double> position;

    public Brick(int x, int y, SpriteSheet spriteSheet) {
        super();
        wallCoordinate = new Generic2D<>(x,y);
        convertCoordinates();

        sprites.put("blue", spriteSheet.getSprite(1,2,32,32));
        sprites.put("green", spriteSheet.getSprite(2,2,32,32));
        sprites.put("red", spriteSheet.getSprite(3,2,32,32));
        sprites.put("orange", spriteSheet.getSprite(4,2,32,32));
        sprites.put("yellow", spriteSheet.getSprite(5,2,32,32));
        sprites.put("pink", spriteSheet.getSprite(6,2,32,32));
        sprites.put("poison", spriteSheet.getSprite(7,2,32,32));
        sprites.put("bomb", spriteSheet.getSprite(8,2,32,32));
    }

    private void convertCoordinates() {
        double x = /*BrickBreakerGame.SPRITE_SIZE.getX() + */wallCoordinate.getX() * BrickBreakerGame.BRICK_SIZE.getX();
        double y = wallCoordinate.getY() * BrickBreakerGame.BRICK_SIZE.getY();
        position = new Generic2D<>(x,y);
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        // To keep things looking clean
        double x = position.getX();
        double y = position.getY();

        graphics.drawImage(sprites.get("poison"),(int)x,(int)y,null);
    }
}