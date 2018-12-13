package org.academiadecodigo.invictus.game.representable;


import org.academiadecodigo.invictus.game.ColisionDetector;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends Representable {

    private ColisionDetector colisionDetector;
    private boolean key;
    private boolean caught;

    public Player(int x, int y, String imagePath, ColisionDetector colisionDetector) {
        super(new Picture(x, y, imagePath));

        this.colisionDetector = colisionDetector;
        caught = false;
    }

    public void reset(int x , int y){

    }

    public void move(){

    }

    public boolean isCaught(){

        return false;
    }

    public boolean hasKey(){

        return false;
    }


}
