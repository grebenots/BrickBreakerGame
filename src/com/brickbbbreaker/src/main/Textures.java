package com.brickbbbreaker.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {

    public BufferedImage playerCenter;
    public BufferedImage playerLeft;
    public BufferedImage playerRight;
    public BufferedImage playerBullet;
    public BufferedImage blackBackground;
    public BufferedImage background;

    private BufferedImageLoader loader;
    private SpriteSheet characterSheet;
    private SpriteSheet uiSheet;
    private SpriteSheet backgroundSheet;

    public Textures() {
        loader = new BufferedImageLoader();
        loadSheets();
        loadTextures();
    }

    private void loadSheets() {
        try {
            characterSheet = new SpriteSheet(loader.loadImage("characterSheet.png"));
            uiSheet = new SpriteSheet(loader.loadImage("uiSheet.png"));
            backgroundSheet = new SpriteSheet(loader.loadImage("backgroundSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTextures() {
        playerCenter = characterSheet.grabImage(2,1,32,32);
        playerLeft = characterSheet.grabImage(1,1,32,32);
        playerRight = characterSheet.grabImage(3,1,32,32);
        playerBullet = characterSheet.grabImage(4,1,32,32);
        background = backgroundSheet.grabSheetImage();
        blackBackground = new BufferedImage(BrickBBBreaker.WIDTH, BrickBBBreaker.HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

}
