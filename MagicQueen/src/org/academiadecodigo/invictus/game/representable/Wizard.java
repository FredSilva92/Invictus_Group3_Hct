package org.academiadecodigo.invictus.game.representable;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Wizard extends Representable {

    private Picture representation;

    public Wizard(Picture representation) {
        super(representation);
        this.representation = representation;
    }

    public Picture getRepresentation() {
        return representation;
    }
}
