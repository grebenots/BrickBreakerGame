package com.stoneberg.brickbreaker;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics;

public abstract class Entity {

    protected Map<String, BufferedImage> sprites;
    abstract void tick();
    abstract void render(Graphics graphics);

    public void Entity() {
        sprites = new HashMap<>();
    }

    public final void addSprite(String name, BufferedImage image) {
        sprites.put(name, image);
    }

    public final Map<String, BufferedImage> getSprites() {
        return sprites;
    }
}

