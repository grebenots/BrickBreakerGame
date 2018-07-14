package com.stoneberg.brickbreaker;

import java.awt.Graphics;

public class Bullet extends Entity {

    private Generic2D<Double> position;

    public Bullet(double x, double y, SpriteSheet spriteSheet) {
        super();
        position = new Generic2D<>(x,y);
        sprites.put("bullet", spriteSheet.getSprite(4,1,32,32));
    }

    public void tick() {
        double y = position.getY();
        y -= 10;
        position.setY(y);
    }

    public void render(Graphics graphics) {
        // To keep things looking clean
        double x = position.getX();
        double y = position.getY();

        graphics.drawImage(sprites.get("bullet"), (int)x, (int)y, null);
    }

    public Generic2D<Double> getPosition() {
        return position;
    }
}
