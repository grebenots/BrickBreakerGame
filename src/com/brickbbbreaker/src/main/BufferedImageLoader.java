package com.brickbbbreaker.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BufferedImageLoader {

    private BufferedImage image;
    private String folderName = "resources";

    public BufferedImage loadImage(String path) throws IOException {
        image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(folderName + "/" + path));
        return image;
    }
}
