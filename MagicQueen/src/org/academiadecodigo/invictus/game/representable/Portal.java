package org.academiadecodigo.invictus.game.representable;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Portal extends Representable {

    private Picture representation;

    public Portal(Picture representation) {
        super(representation);
        this.representation = representation;
    }

    public Picture getRepresentation() {
        return representation;
    }
}
