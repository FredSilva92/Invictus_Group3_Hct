package org.academiadecodigo.invictus.game.representable;

import org.academiadecodigo.invictus.game.ColisionDetector;
import org.academiadecodigo.invictus.game.Direction;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Guards extends Representable {

    private static final int SPEED = 7;
    private ColisionDetector colisionDetector;
    private Direction currentDirection;
    private Point point;

    public Guards(Picture representation, ColisionDetector colisionDetector) {
        super(representation);
        this.colisionDetector = colisionDetector;
        this.currentDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void move() {
        walk();
    }

    public void walk() {


        switch (chooseDirection()) {
            case UP:
                representation.translate(0, -SPEED);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                    currentDirection = currentDirection.oppositeDirection();
                    representation.translate(0, SPEED);
                }
                break;
            case DOWN:
                representation.translate(0, SPEED);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                    currentDirection = currentDirection.oppositeDirection();
                    representation.translate(0, -SPEED);
                }
                break;
            case LEFT:
                representation.translate(-SPEED, 0);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                    currentDirection = currentDirection.oppositeDirection();
                    representation.translate(SPEED, 0);
                }
                break;
            case RIGHT:
                representation.translate(SPEED, 0);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                    currentDirection = currentDirection.oppositeDirection();
                    representation.translate(-SPEED, 0);
                }
                break;
        }
    }

    public Direction chooseDirection() {

        // Let's move in the same direction by default
        Direction newDirection = currentDirection;

        // Sometimes, we want to change direction...
        if (Math.random() > 0.6) {
            newDirection = Direction.values()[(int) (Math.random() * Direction.values().length)];
            if (newDirection.isOpposite(currentDirection)) {
                return chooseDirection();
            }
        }

        return newDirection;

    }


}
