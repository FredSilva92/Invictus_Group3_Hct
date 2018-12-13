package org.academiadecodigo.invictus.game.representable;


import org.academiadecodigo.invictus.game.ColisionDetector;
import org.academiadecodigo.invictus.game.Direction;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends Representable {

    private static final int SPEED = 2;
    private ColisionDetector colisionDetector;
    private boolean key;
    private boolean caught;
    private Direction direction;

    public Player(int x, int y, String imagePath, ColisionDetector colisionDetector) {
        super(new Picture(x, y, imagePath));

        this.colisionDetector = colisionDetector;
        caught = false;
        key = false;
    }

    public void reset(int x, int y) {
        representation.delete();
        representation.translate(x - representation.getX(), y - representation.getY());
        caught = false;
        direction = null;
    }

    public void move() {
        walk();

        if (colisionDetector.hitsGuard(this)) {
            caught = true;
        }
    }

    private void walk() {
        if (direction == null) {
            return;
        }

        int modifier = direction == Direction.UP ? 1 : -1;

        representation.translate( SPEED * modifier,  SPEED * modifier);

        if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
            representation.translate( SPEED * -modifier, SPEED * -modifier);
        }
    }

    public boolean isCaught() {

        return caught;
    }

    public boolean hasKey() {
        return key;
    }

    public void pickKey() {
        key = true;
    }


}
