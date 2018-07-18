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
    }

    public void tick() {

    }

    public void render() {
        updateTheCheese();

        // Render the edge of the screen (13 tiles wide x 17 tiles tall)
        drawTileByCoordinate("NW", 0, 0);
        drawTileByCoordinate("NE", 12, 0);
        drawTileByCoordinate("SW", 0, 16);
        drawTileByCoordinate("SE", 12, 16);

//        for(int i = 0; i < 17; i++) {
//            drawTileByCoordinate("W", 0, i);
//            drawTileByCoordinate("E", 12, i);
//        }
//
//        for(int i = 0; i < 13; i++) {
//            drawTileByCoordinate("N", i, 0);
//            drawTileByCoordinate("S", i, 12);
//        }
    }

    private void drawTileByCoordinate(String name, int xCoordinate, int yCoordinate) {
        int x = xCoordinate * theStinkyCheese.SPRITE_SIZE.getX();
        int y = yCoordinate * theStinkyCheese.SPRITE_SIZE.getY();

        theStinkyCheese.getGraphics().drawImage(sprites.get(name), x, y, null);
    }
}
