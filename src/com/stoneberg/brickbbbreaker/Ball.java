package com.stoneberg.brickbbbreaker;

public class Ball extends Entity {

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private Direction verticalDirection;
    private Direction horizontalDirection;

    public Ball() {
        super();
        position.set(0.0, 0.0);
        velocity.set(0.0, 0.0);
        verticalDirection = Direction.UP;
        horizontalDirection = Direction.RIGHT;

        sprites.put("ballSmall", theStinkyCheese.getSpriteSheet().getSprite(6,1,32,32));
    }

    public void beginLevel() {
        Generic2D<Double> playerPosition = theStinkyCheese.getPlayer().getPosition();
        position.set(playerPosition.getX(), playerPosition.getY() - 16);
        velocity = new Generic2D<Double>(4.0, -4.0);
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
    }

    public void render() {
        updateTheCheese();

        double x = position.getX();
        double y = position.getY();

        theStinkyCheese.getGraphics().drawImage(sprites.get("ballSmall"), (int)x, (int)y, null);
    }

    public Generic2D<Double> getVelocity() {
        return velocity;
    }

    public Generic2D<Double> getPosition() {
        return position;
    }
}