package org.academiadecodigo.invictus.game;

import org.academiadecodigo.invictus.game.levels.Level;
import org.academiadecodigo.invictus.game.representable.Guards;
import org.academiadecodigo.invictus.game.representable.Player;
import org.academiadecodigo.invictus.keyboard.InputHandler;
import org.academiadecodigo.invictus.keyboard.Key;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Game implements InputHandler {

    public static final int PADDING = 10;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private static final String PLAYER_IMAGE = "/Users/codecadet/Hackhaton/Invictus_Group3_Hct/MagicQueen/resources/assets/fan.png";
    private static final int PLAYER_ONE_INITIAL_X = 50;
    private static final int PLAYER_ONE_INITIAL_Y = 50;

    private ColisionDetector collisionDetector;
    private Player playerOne;
    private List<Guards> guards;
    private Level level;

    public void init() {
        guards = new LinkedList<Guards>();
        collisionDetector = new ColisionDetector(guards);

        playerOne = new Player(PLAYER_ONE_INITIAL_X, PLAYER_ONE_INITIAL_Y, PLAYER_IMAGE, collisionDetector);

        level = Level.LEVEL1;
    }

    public void start(){
        initWalls();

        playerOne.show();
        while (!playerOne.isCaught()) {
            movePlayers();
            moveGuards();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void initWalls() {
        level = level.getNext();
        collisionDetector.setWalls(level.getWalls());
        level.show();
    }

    private void movePlayers() {
        playerOne.move();

    }

    private void moveGuards() {
        Iterator<Guards> iterator = guards.iterator();

        while (iterator.hasNext()) {
            Guards guard = iterator.next();
            guard.move();

        }
    }

    public void reset() {
        playerOne.reset(PLAYER_ONE_INITIAL_X, PLAYER_ONE_INITIAL_Y);

        for (Guards guard : guards) {
            guard.hide();
        }

        guards.clear();
        level.hide();
    }

    public void press(Key key) {
        switch (key) {
            case UP:
                playerOne.setDirection(Direction.UP);
                break;
            case DOWN:
                playerOne.setDirection(Direction.DOWN);
                break;
            case LEFT:
                playerOne.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                playerOne.setDirection(Direction.RIGHT);
                break;
                /*
            case W:
                playerTwo.setDirection(Direction.UP);
                break;
            case S:
                playerTwo.setDirection(Direction.DOWN);
                break;
            case A:
                playerTwo.setRotation(Direction.LEFT);
                break;
            case D:
                playerTwo.setRotation(Direction.RIGHT);
                break;
            case Q:
                playerTwo.prepareShot();
                break;
                */
        }

    }

    public void release(Key key) {
        switch (key) {
            case UP:
                playerOne.setDirection(null);
                break;
            case DOWN:
                playerOne.setDirection(null);
                break;
            case RIGHT:
                playerOne.setDirection(null);
                break;
            case LEFT:
                playerOne.setDirection(null);
                break;
                /*
            case W:
            case S:
                playerTwo.setDirection(null);
                break;
            case A:
            case D:
                playerTwo.setRotation(null);
                break;
                */
        }
    }
}
