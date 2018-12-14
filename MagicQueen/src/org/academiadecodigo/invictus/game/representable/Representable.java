package org.academiadecodigo.invictus.game.representable;


import org.academiadecodigo.invictus.game.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Representable implements Instantiables {

    protected Picture representation;

    public Representable(Picture representation) {
        this.representation = representation;

    }

    public void show() {
        representation.draw();
    }

    public void hide() {
        representation.delete();
    }

    public boolean isOutOfBounds() {
        return representation.getX() < Game.PADDING ||
                representation.getX() + representation.getWidth() > Game.PADDING + Game.WIDTH ||
                representation.getY() < Game.PADDING ||
                representation.getY() + representation.getHeight() > Game.PADDING + Game.HEIGHT;
    }

    public boolean overlaps(Representable other) {
        if (representation.getX() > other.representation.getX() && representation.getX() < other.representation.getMaxX() && representation.getY() > other.representation.getY() && representation.getY() < other.representation.getMaxY()) {
            return true;
        }

        if (representation.getMaxX() > other.representation.getX() && representation.getMaxX() < other.representation.getMaxX() && representation.getY() > other.representation.getY() && representation.getY() < other.representation.getMaxY()) {
            return true;
        }

        if (representation.getX() > other.representation.getX() && representation.getX() < other.representation.getMaxX() && representation.getMaxY() > other.representation.getY() && representation.getMaxY() < other.representation.getMaxY()) {
            return true;
        }

        if (representation.getMaxX() > other.representation.getX() && representation.getMaxX() < other.representation.getMaxX() && representation.getMaxY() > other.representation.getY() && representation.getMaxY() < other.representation.getMaxY()) {
            return true;
        }
        return false;
    }
}
