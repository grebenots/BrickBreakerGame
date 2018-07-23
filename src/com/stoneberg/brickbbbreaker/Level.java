package com.stoneberg.brickbbbreaker;

import java.util.Map;
import java.util.HashMap;

public class Level {
    private Map<Generic2D<Integer>, Brick> bricks = null;
    int levelNumber;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        bricks = new HashMap<>();
    }

    protected void addBrick(int x, int y, Brick.BrickType brickType) {
        Brick brick = new Brick(x,y, brickType);
        bricks.put(brick.getWallCoordinate(), brick);
    }

    protected void removeBrick(Generic2D<Integer> brickCoordinate) {
        bricks.remove(brickCoordinate);
    }

    public Map<Generic2D<Integer>, Brick> getBricks() {
        return bricks;
    }

}
