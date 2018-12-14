package org.academiadecodigo.invictus.game.representable;

import org.academiadecodigo.invictus.game.ColisionDetector;
import org.academiadecodigo.invictus.game.Direction;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Guards extends Representable {

    private static final int SPEED = 5;
    private Direction direction;
    private ColisionDetector colisionDetector;
    Direction currentDirection;

    public Guards(String imagePath, ColisionDetector colisionDetector) {
        super(new Picture(400, 400, imagePath));
        this.colisionDetector = colisionDetector;
        currentDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];
    }

    public void move() {
        walk();
    }

    public void walk() {
//        direction = Direction.values()[(int) (Math.random() * Direction.values().length)];



        switch (chooseDirection()) {
            case UP:
                representation.translate(0, -SPEED);

                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
//                    representation.translate(0, SPEED);
                        currentDirection = currentDirection.oppositeDirection();
                    }
                break;
            case DOWN:
                representation.translate(0, SPEED);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
//                    representation.translate(0, -SPEED);
                    currentDirection = currentDirection.oppositeDirection();
                }
                break;
            case LEFT:
                representation.translate(-SPEED, 0);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
//                    representation.translate(SPEED, 0);
                    currentDirection = currentDirection.oppositeDirection();
                }
                break;
            case RIGHT:
                representation.translate(SPEED, 0);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
//                    representation.translate(-SPEED, 0);
                    currentDirection = currentDirection.oppositeDirection();
                }
                break;
        }
    }

    public Direction chooseDirection() {

        // Let's move in the same direction by default
        Direction newDirection = currentDirection;

        // Sometimes, we want to change direction...
        if (Math.random() >  0.8) {//20% prob changing direction
            newDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];
        }

        return newDirection;

    }


}
