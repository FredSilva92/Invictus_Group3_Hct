package org.academiadecodigo.invictus.game.representable;


import org.academiadecodigo.invictus.game.ColisionDetector;
import org.academiadecodigo.invictus.game.Direction;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player extends Representable {

    private static final int SPEED = 10;
    private ColisionDetector colisionDetector;
    private boolean key;
    private boolean caught;
    private boolean gameOver;
    private Direction direction;

    public Player(int x, int y, String imagePath, ColisionDetector colisionDetector) {
        //super(new Picture(x, y, imagePath));

        representation = new Picture(x, y, imagePath);
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

       switch (direction){
           case UP:
               representation.translate( 0,  -SPEED);

               if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                   representation.translate( 0, SPEED);
               }
               break;
           case DOWN:
               representation.translate( 0 ,  SPEED);
               if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                   representation.translate( 0, -SPEED);
               }
               break;
           case LEFT:
               representation.translate( -SPEED,  0);
               if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                   representation.translate( SPEED, 0);
               }
               break;
           case RIGHT:
               representation.translate( SPEED,  0);
               if (colisionDetector.hitsWall(this) || isOutOfBounds()) {
                   representation.translate( -SPEED, 0);
               }
               break;
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

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
