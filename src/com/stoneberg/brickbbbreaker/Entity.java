package com.stoneberg.brickbbbreaker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.awt.Rectangle;

public abstract class Entity {

    protected BrickBBBreaker theStinkyCheese = null;
    protected Map<String, BufferedImage> sprites;
    protected Generic2D<Double> position;
    protected Generic2D<Double> velocity;

    protected enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE
    }

    abstract void tick();
    abstract void render();
    abstract Rectangle bounds();

    public Entity() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
        sprites = new HashMap<String, BufferedImage>();
        position = new Generic2D<>(0.0, 0.0);
        velocity = new Generic2D<>(0.0, 0.0);
    }

    public final void addSprite(String name, BufferedImage image) {
        sprites.put(name, image);
    }

    public final Map<String, BufferedImage> getSprites() {
        return sprites;
    }
}