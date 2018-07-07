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

    public BufferedImage uiBorderLeft;
    public BufferedImage uiBorderRight;
    public BufferedImage uiBorderTop;
    public BufferedImage uiBorderTopLeft;
    public BufferedImage uiBorderTopRight;

    public BufferedImage brick1;
    public BufferedImage brick2;

    private BufferedImageLoader loader;
    private SpriteSheet entitySheet;
    private SpriteSheet uiSheet;
    private SpriteSheet backgroundSheet;

    public Textures() {
        loader = new BufferedImageLoader();
        loadSheets();
        loadTextures();
    }

    private void loadSheets() {
        try {
            entitySheet = new SpriteSheet(loader.loadImage("entitySheet.png"));
            uiSheet = new SpriteSheet(loader.loadImage("uiSheet.png"));
            backgroundSheet = new SpriteSheet(loader.loadImage("backgroundSheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTextures() {
        playerCenter = entitySheet.grabImage(2,1,32,32);
        playerLeft = entitySheet.grabImage(1,1,32,32);
        playerRight = entitySheet.grabImage(3,1,32,32);
        playerBullet = entitySheet.grabImage(4,1,32,32);
        background = backgroundSheet.grabSheetImage();
        blackBackground = new BufferedImage(BrickBBBreaker.WIDTH, BrickBBBreaker.HEIGHT, BufferedImage.TYPE_INT_RGB);

//        uiBorderLeft = entitySheet.grabImage(1,4,32,32);
//        uiBorderTop = entitySheet.grabImage(2,3,32,32);
//        uiBorderTopLeft = entitySheet.grabImage(1,3,32,32);

        brick1 = entitySheet.grabImage(1,2,32,32);
        brick2 = entitySheet.grabImage(2,2,32,32);

    }

}
