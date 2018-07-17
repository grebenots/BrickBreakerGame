package com.stoneberg.brickbbbreaker;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class Entity {

    protected BrickBBBreaker theStinkyCheese = null;
    protected Map<String, BufferedImage> sprites;
    protected Generic2D<Double> position;
    protected Generic2D<Double> velocity;

    abstract void tick();
    abstract void render();

    public Entity() {
        updateTheCheese();
        sprites = new HashMap<String, BufferedImage>();
        position = new Generic2D<>(0.0, 0.0);
        velocity = new Generic2D<>(0.0, 0.0);
    }

    protected final void updateTheCheese() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
    }

    public final void addSprite(String name, BufferedImage image) {
        sprites.put(name, image);
    }

    public final Map<String, BufferedImage> getSprites() {
        return sprites;
    }
}