package org.academiadecodigo.invictus.game.representable;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class FakeWall {

    protected Picture representation;


    public FakeWall(Picture representation) {
        this.representation = representation;

    }

    public void show(){
        representation.draw();
    }

    public void delete(){
        representation.delete();
    }
}
