package com.stoneberg.brickbbbreaker;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private BufferedImage spriteSheet;

    public SpriteSheet(String fileName) {
        try {
            this.spriteSheet = loadImageFromFile(fileName);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getSprite(int col, int row, int width, int height) {
        BufferedImage sprite = spriteSheet.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        return sprite;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    private BufferedImage loadImageFromFile(String fileName) throws IOException {
        BufferedImage image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resources/" + fileName));
        return image;
    }
}
