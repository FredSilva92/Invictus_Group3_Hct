package org.academiadecodigo.invictus.game.representable;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Queen extends Representable {

    private Picture representation;

    public Queen(Picture representation) {
        super(representation);
        this.representation = representation;
    }

    public Picture getRepresentation() {
        return representation;
    }
}
