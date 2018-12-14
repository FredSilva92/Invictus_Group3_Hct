package org.academiadecodigo.invictus.game.representable;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Key implements Instantiables {

    private Picture representation;

    public Key(Picture representation) {
        this.representation = representation;
    }

    @Override
    public void show() {
        representation.draw();
    }

    @Override
    public void hide() {
        representation.delete();
    }

    @Override
    public boolean overlaps(Representable representable) {
        if (representation.getX() > representable.representation.getX() && representation.getX() < representable.representation.getMaxX() && representation.getY() > representable.representation.getY() && representation.getY() < representable.representation.getMaxY()) {
            return true;
        }

        if (representation.getMaxX() > representable.representation.getX() && representation.getMaxX() < representable.representation.getMaxX() && representation.getY() > representable.representation.getY() && representation.getY() < representable.representation.getMaxY()) {
            return true;
        }

        if (representation.getX() > representable.representation.getX() && representation.getX() < representable.representation.getMaxX() && representation.getMaxY() > representable.representation.getY() && representation.getMaxY() < representable.representation.getMaxY()) {
            return true;
        }

        if (representation.getMaxX() > representable.representation.getX() && representation.getMaxX() < representable.representation.getMaxX() && representation.getMaxY() > representable.representation.getY() && representation.getMaxY() < representable.representation.getMaxY()) {
            return true;
        }
        return false;
    }
}
