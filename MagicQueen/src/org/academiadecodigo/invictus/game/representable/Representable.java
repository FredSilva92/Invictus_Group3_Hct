package org.academiadecodigo.invictus.game.representable;


import org.academiadecodigo.invictus.game.Game;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.List;

public class Representable implements Instantiables {

    protected Picture representation;
    public Representable(Picture representation) {
        this.representation = representation;

    }

    public void setRepresentation(Picture representation) {
        this.representation = representation;
    }

    public Picture getRepresentation() {
        return representation;
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

    public boolean overlaps(List<? extends Representable> other) {
        int count = 0;
        for (Representable representable : other) {
            if (representation.getX() > representable.representation.getX() && representation.getX() < representable.representation.getMaxX() && representation.getY() > representable.representation.getY() && representation.getY() < representable.representation.getMaxY()) {
                count++;
            }

            if (representation.getMaxX() > representable.representation.getX() && representation.getMaxX() < representable.representation.getMaxX() && representation.getY() > representable.representation.getY() && representation.getY() < representable.representation.getMaxY()) {
                count++;
            }

            if (representation.getX() > representable.representation.getX() && representation.getX() < representable.representation.getMaxX() && representation.getMaxY() > representable.representation.getY() && representation.getMaxY() < representable.representation.getMaxY()) {
                count++;
            }

            if (representation.getMaxX() > representable.representation.getX() && representation.getMaxX() < representable.representation.getMaxX() && representation.getMaxY() > representable.representation.getY() && representation.getMaxY() < representable.representation.getMaxY()) {
                count++;
            }

        }
        if(count != 0){
            return true;
        }
        return false;
    }
}
