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

    private static final String PLAYER_ONE_IMAGE = "IMAGEM PLAYER";
    private static final int PLAYER_ONE_INITIAL_X = 50;
    private static final int PLAYER_ONE_INITIAL_Y = 50;

    private ColisionDetector collisionDetector;
    private Player playerOne;
    private List<Guards> guards;
    private Level level;

    public void init() {
        guards = new LinkedList<Guards>();
        collisionDetector = new ColisionDetector(guards);

        playerOne = new Player(PLAYER_ONE_INITIAL_X, PLAYER_ONE_INITIAL_Y, PLAYER_ONE_IMAGE, collisionDetector);

        level = Level.LEVEL1;
    }

    public void start(){
        initWalls();

        playerOne.show();
        while (playerOne.isCaught()) {
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

    }

    public void release(Key key) {

    }
}
