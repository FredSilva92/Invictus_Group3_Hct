package org.academiadecodigo.invictus.game.representable;

import org.academiadecodigo.invictus.game.ColisionDetector;
import org.academiadecodigo.invictus.game.Direction;
import org.academiadecodigo.invictus.game.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Guards extends Representable {

    private static final int SPEED = 2;
    private Direction direction;
    private ColisionDetector colisionDetector;

    public Guards(String imagePath, ColisionDetector colisionDetector) {
        super(new Picture(400, 400, imagePath));
        this.colisionDetector = colisionDetector;
    }

    public void move() {
        walk();
    }

    public void walk() {
        direction = Direction.values()[(int) (Math.random() * Direction.values().length)];

        if (direction == null) {
            return;
        }


        switch (direction) {
            case UP:
                representation.translate(0, -SPEED);

                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                    representation.translate(0, SPEED);
                }
                break;
            case DOWN:
                representation.translate(0, SPEED);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                    representation.translate(0, -SPEED);
                }
                break;
            case LEFT:
                representation.translate(-SPEED, 0);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                    representation.translate(SPEED, 0);
                }
                break;
            case RIGHT:
                representation.translate(SPEED, 0);
                if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                    representation.translate(-SPEED, 0);
                }
                break;
        }
    }
}
