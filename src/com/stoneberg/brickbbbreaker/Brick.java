package com.stoneberg.brickbbbreaker;

import java.awt.*;
import java.util.Random;

public class Brick extends Entity {

    // Physics
    private Generic2D<Integer> wallCoordinate;
    private BrickType brickType;
    private String brickName;

    public enum BrickType {
        BLUE,
        GREEN,
        RED,
        ORANGE,
        YELLOW,
        PURPLE,
        POISON,
        BOMB,
        RANDOM
    }

    public Brick(int x, int y, BrickType brickType) {
        super();
        wallCoordinate = new Generic2D<>(x,y);
        setBrickType(brickType);

        position.setX(wallCoordinate.getX().doubleValue() * theStinkyCheese.SPRITE_SIZE.getX());
        position.setY(wallCoordinate.getY().doubleValue() * theStinkyCheese.SPRITE_SIZE.getY() / 2);
    }

    private void setBrickType(BrickType brickType) {

        this.brickType = brickType;

        switch(brickType) {
            case BLUE:
                brickName = "brickBlue";
                break;
            case GREEN:
                brickName = "brickGreen";
                break;
            case RED:
                brickName = "brickRed";
                break;
            case ORANGE:
                brickName = "brickOrange";
                break;
            case YELLOW:
                brickName = "brickYellow";
                break;
            case PURPLE:
                brickName = "brickPurple";
                break;
            case POISON:
                brickName = "brickPoison";
                break;
            case BOMB:
                brickName = "brickBomb";
                break;
            case RANDOM:
                setRandomType();
                break;
        }
    }

    private void setRandomType() {
        Random random = new Random();

        switch(random.nextInt(6)) {
            case 0:
                brickType = BrickType.BLUE;
                brickName = "brickBlue";
                break;
            case 1:
                brickType = BrickType.GREEN;
                brickName = "brickGreen";
                break;
            case 2:
                brickType = BrickType.RED;
                brickName = "brickRed";
                break;
            case 3:
                brickType = BrickType.ORANGE;
                brickName = "brickOrange";
                break;
            case 4:
                brickType = BrickType.YELLOW;
                brickName = "brickYellow";
                break;
            case 5:
                brickType = BrickType.PURPLE;
                brickName = "brickPurple";
                break;
            case 6:
                brickType = BrickType.POISON;
                brickName = "brickPoison";
                break;
            case 7:
                brickType = BrickType.BOMB;
                brickName = "brickBomb";
                break;
        }
    }

    public void tick() {
        updateTheCheese();
    }

    public void render() {
        updateTheCheese();
        theStinkyCheese.getGameUI().drawBrickByCoordinate("brickBlue", wallCoordinate.getX(), wallCoordinate.getY());
    }

    public Rectangle bounds() {
        return new Rectangle(position.getX().intValue(), position.getY().intValue(), 32, 16);
    }

    public Generic2D<Integer> getWallCoordinate() {
        return wallCoordinate;
    }

    public String getBrickName() {
        return brickName;
    }
}