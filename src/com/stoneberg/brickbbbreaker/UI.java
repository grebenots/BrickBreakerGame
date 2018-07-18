package com.stoneberg.brickbbbreaker;

public class UI extends Entity {

    public UI() {
        super();
        position.set(0.0, 0.0);
        velocity.set(0.0, 0.0);

        sprites.put("NW", theStinkyCheese.getSpriteSheet().getSprite(1,3,32,32));
        sprites.put("N", theStinkyCheese.getSpriteSheet().getSprite(2,3,32,32));
        sprites.put("NE", theStinkyCheese.getSpriteSheet().getSprite(3,3,32,32));
        sprites.put("SW", theStinkyCheese.getSpriteSheet().getSprite(1,5,32,32));
        sprites.put("S", theStinkyCheese.getSpriteSheet().getSprite(2,5,32,32));
        sprites.put("SE", theStinkyCheese.getSpriteSheet().getSprite(3,5,32,32));
        sprites.put("W", theStinkyCheese.getSpriteSheet().getSprite(1,4,32,32));
        sprites.put("E", theStinkyCheese.getSpriteSheet().getSprite(3,4,32,32));

        sprites.put("brickBlue", theStinkyCheese.getSpriteSheet().getSprite(1,2,32,32));
        sprites.put("brickGreen", theStinkyCheese.getSpriteSheet().getSprite(2,2,32,32));
        sprites.put("brickRed", theStinkyCheese.getSpriteSheet().getSprite(3,2,32,32));
        sprites.put("brickOrange", theStinkyCheese.getSpriteSheet().getSprite(4,2,32,32));
        sprites.put("brickYellow", theStinkyCheese.getSpriteSheet().getSprite(5,2,32,32));
        sprites.put("brickPurple", theStinkyCheese.getSpriteSheet().getSprite(6,2,32,32));
    }

    public void tick() {

    }

    public void render() {
        updateTheCheese();

        // Render the edge of the screen (13 tiles wide x 17 tiles tall)
        drawSpriteByCoordinate("NW", 0, 0);
        drawSpriteByCoordinate("NE", 12, 0);
        drawSpriteByCoordinate("SW", 0, 16);
        drawSpriteByCoordinate("SE", 12, 16);

        for(int i = 1; i < 16; i++) {
            drawSpriteByCoordinate("W", 0, i);
            drawSpriteByCoordinate("E", 12, i);
        }

        for(int i = 1; i < 12; i++) {
            drawSpriteByCoordinate("N", i, 0);
            drawSpriteByCoordinate("S", i, 16);
        }

        // Render bricks
        for(int i = 1; i < 12; i++) {
            drawBrickByCoordinate("brickRed", i, 3);
            drawBrickByCoordinate("brickBlue", i, 4);

            drawBrickByCoordinate("brickYellow", i, 7);
            drawBrickByCoordinate("brickOrange", i, 8);
        }

        for(int i = 1; i < 3; i++) {
            drawBrickByCoordinate("brickGreen", i, 5);
            drawBrickByCoordinate("brickPurple", i, 6);
        }

        for(int i = 10; i < 12; i++) {
            drawBrickByCoordinate("brickGreen", i, 5);
            drawBrickByCoordinate("brickPurple", i, 6);
        }

    }

    private void drawSpriteByCoordinate(String name, int xCoordinate, int yCoordinate) {
        int x = xCoordinate * theStinkyCheese.SPRITE_SIZE.getX();
        int y = yCoordinate * theStinkyCheese.SPRITE_SIZE.getY();

        theStinkyCheese.getGraphics().drawImage(sprites.get(name), x, y, null);
    }

    private void drawBrickByCoordinate(String name, int xCoordinate, int yCoordinate) {
        int x = xCoordinate * theStinkyCheese.SPRITE_SIZE.getX();
        int y = yCoordinate * theStinkyCheese.SPRITE_SIZE.getY() / 2;

        theStinkyCheese.getGraphics().drawImage(sprites.get(name), x, y, null);
    }
}
