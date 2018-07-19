package com.stoneberg.brickbbbreaker;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class UI {

    protected BrickBBBreaker theStinkyCheese = null;
    private Map<String, BufferedImage> sprites;

    public abstract void render();

    public UI() {
        updateTheCheese();

        // Add UI sprites to main UI class
        sprites = new HashMap<String, BufferedImage>();
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

    protected Font createNewFont(String name, int style, int size) {
        return new Font(name, style, size);
    }

    protected void drawSpriteByCoordinate(String name, int xCoordinate, int yCoordinate) {
        int x = xCoordinate * theStinkyCheese.SPRITE_SIZE.getX();
        int y = yCoordinate * theStinkyCheese.SPRITE_SIZE.getY();

        theStinkyCheese.getGraphics().drawImage(sprites.get(name), x, y, null);
    }

    protected void drawBrickByCoordinate(String name, int xCoordinate, int yCoordinate) {
        int x = xCoordinate * theStinkyCheese.SPRITE_SIZE.getX();
        int y = yCoordinate * theStinkyCheese.SPRITE_SIZE.getY() / 2;

        theStinkyCheese.getGraphics().drawImage(sprites.get(name), x, y, null);
    }

    protected void drawString(String text, int x, int y, Font font, Color color) {
        theStinkyCheese.getGraphics().setFont(font);
        theStinkyCheese.getGraphics().setColor(color);
        theStinkyCheese.getGraphics().drawString(text, x, y);
    }

    protected void drawCenteredString(String text, int y, Font font, Color color) {
        theStinkyCheese.getGraphics().setFont(font);
        theStinkyCheese.getGraphics().setColor(color);

        // Calculate center
        FontMetrics metrics = theStinkyCheese.getGraphics().getFontMetrics(font);
        int titleWidth = metrics.stringWidth(text);
        int x = theStinkyCheese.WINDOW_SIZE.getX().intValue() / 2 - titleWidth / 2;

        theStinkyCheese.getGraphics().drawString(text, x, y);
    }

    protected void setOpacity(float amount) {
        AlphaComposite alphaC = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, amount);
        Graphics2D g2 = (Graphics2D)theStinkyCheese.getGraphics();
        g2.setComposite(alphaC);
    }

    protected Color randomColor() {
        Random random = new Random();
        Color color = Color.WHITE;

        switch (random.nextInt(5)) {
            case 0:
                color = Color.WHITE;
                break;
            case 1:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.RED;
                break;
            case 3:
                color = Color.BLUE;
                break;
            case 4:
                color = Color.CYAN;
                break;
            case 5:
                color = Color.MAGENTA;
        }

        return color;
    }

    protected final void updateTheCheese() {
        theStinkyCheese = BrickBBBreaker.getCurrentGame();
    }

    protected final void addSprite(String name, BufferedImage image) {
        sprites.put(name, image);
    }

    protected final Map<String, BufferedImage> getSprites() {
        return sprites;
    }
}