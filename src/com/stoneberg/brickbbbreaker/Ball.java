package com.stoneberg.brickbbbreaker;

import com.sun.tools.javah.Gen;

import java.awt.*;
import java.util.Map;

public class Ball extends Entity {

    private Direction verticalDirection;
    private Direction horizontalDirection;
    private Generic2D<Double> defaultVelocity;
    private Generic2D<Double> minVelocity;
    private Generic2D<Double> maxVelocity;

    public Ball() {
        super();
        position.set(0.0, 0.0);
        velocity.set(0.0, 0.0);
        minVelocity = new Generic2D<Double>(2.0, 4.0);
        maxVelocity = new Generic2D<Double>(8.0, 8.0);
        verticalDirection = Direction.UP;
        horizontalDirection = Direction.RIGHT;
        defaultVelocity = new Generic2D<Double>(4.0, -4.0);

        sprites.put("ballSmall", theStinkyCheese.getSpriteSheet().getSprite(6,1,32,32));
    }

    public void beginLevel() {
        Generic2D<Double> playerPosition = theStinkyCheese.getPlayer().getPosition();
        position.set(playerPosition.getX(), playerPosition.getY() - 16);
        velocity = new Generic2D<Double>(defaultVelocity.getX(), defaultVelocity.getY());
    }


    public void tick() {
        updateTheCheese();

        double x = position.getX();
        double y = position.getY();

        x += velocity.getX();
        y += velocity.getY();

        if(horizontalDirection == Direction.LEFT) {
            if (x <= theStinkyCheese.SPRITE_SIZE.getX() / 2 + 4) {
                x = theStinkyCheese.SPRITE_SIZE.getX() / 2 + 4;
                horizontalDirection = Direction.RIGHT;
                velocity.setX(velocity.getX() * -1);
            }
        }

        if(horizontalDirection == Direction.RIGHT) {
            if (x >= theStinkyCheese.WINDOW_SIZE.getX() - theStinkyCheese.SPRITE_SIZE.getX() * 1.5 - 4) {
                x = theStinkyCheese.WINDOW_SIZE.getX()  - theStinkyCheese.SPRITE_SIZE.getX() * 1.5 - 4;
                horizontalDirection = Direction.LEFT;
                velocity.setX(velocity.getX() * -1);
            }
        }

        if(verticalDirection == Direction.UP) {
            if (y <= theStinkyCheese.SPRITE_SIZE.getY() * 1.5 + 4) {
                y = theStinkyCheese.SPRITE_SIZE.getY() * 1.5 + 4;
                verticalDirection = Direction.DOWN;
                velocity.setY(velocity.getY() * -1);
            }
        }

        if(verticalDirection == Direction.DOWN) {
            if (y >= theStinkyCheese.WINDOW_SIZE.getY() + theStinkyCheese.SPRITE_SIZE.getY()) {
                y = theStinkyCheese.WINDOW_SIZE.getY()  + theStinkyCheese.SPRITE_SIZE.getY();
                verticalDirection = Direction.UP;
                velocity.setY(velocity.getY() * -1);
            }
        }

        position.set(x,y);

        checkCollisions();
    }

    private void checkCollisions() {
        // Check for player
        if(bounds().intersects(theStinkyCheese.getPlayer().bounds())) {
            double x = velocity.getX();
            double y = velocity.getY();
            double ease = theStinkyCheese.getInputController().getEasement();

            double newX = x;
            double newY = y * -1;

            if(getHorizontalDirection() == theStinkyCheese.getPlayer().getHorizontalDirection()) {
                newX *= ((ease / 1.0) * 2.5);
            } else {
                newX *= ((ease / 1.0) * .4);
            }

            if(Math.abs(newX) > maxVelocity.getX()) {
                newX = Math.abs(newX) == newX ? maxVelocity.getX() : maxVelocity.getX() * -1;
            }
            if(Math.abs(newY) > maxVelocity.getY()) {
                newY = Math.abs(newY) == newY ? maxVelocity.getY() : maxVelocity.getY() * -1;
            }

            if(Math.abs(newX) < minVelocity.getX()) {
                newX = Math.abs(newX) == newX ? minVelocity.getX() : minVelocity.getX() * -1;
            }
            if(Math.abs(newY) < minVelocity.getY()) {
                newY = Math.abs(newY) == newY ? minVelocity.getY() : minVelocity.getY() * -1;
            }

            velocity.set(newX, newY);
            position.setY(theStinkyCheese.getPlayer().getPosition().getY() - 8);
            verticalDirection = verticalDirection == Direction.DOWN ? Direction.UP : Direction.DOWN;

        }

        // Check for bricks
        Level currentLevel = theStinkyCheese.getLevels().get(theStinkyCheese.getPlayer().getCurrentLevel());
        Map<Generic2D<Integer>, Brick> currentBricks = currentLevel.getBricks();

        for(Generic2D<Integer> key : currentBricks.keySet()) {
            Brick brick = currentBricks.get(key);
            Generic2D<Integer> currentCoordinate = brick.getWallCoordinate();

            if(bounds().intersects(brick.bounds())) {
                velocity.setY(velocity.getY() * -1);
                verticalDirection = verticalDirection == Direction.DOWN ? Direction.UP : Direction.DOWN;
                theStinkyCheese.getLevels().get(theStinkyCheese.getPlayer().getCurrentLevel()).removeBrick(currentCoordinate);
                break;
            }
        }
    }

    public void render() {
        updateTheCheese();

        double x = position.getX();
        double y = position.getY();

        theStinkyCheese.getGraphics().drawImage(sprites.get("ballSmall"), (int)x, (int)y, null);

        Rectangle rect = bounds();
        theStinkyCheese.getGraphics().setColor(Color.RED);
        theStinkyCheese.getGraphics().drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    public Rectangle bounds() {
        return new Rectangle(position.getX().intValue() + 12, position.getY().intValue() + 12, 8, 8);
    }

    public Direction getHorizontalDirection() {
        return horizontalDirection;
    }

    public Generic2D<Double> getVelocity() {
        return velocity;
    }

    public Generic2D<Double> getPosition() {
        return position;
    }
}