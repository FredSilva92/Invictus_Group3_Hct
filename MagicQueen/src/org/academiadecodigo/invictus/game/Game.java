package org.academiadecodigo.invictus.game;

import org.academiadecodigo.invictus.game.levels.Level;
import org.academiadecodigo.invictus.game.representable.*;
import org.academiadecodigo.invictus.keyboard.InputHandler;
import org.academiadecodigo.invictus.keyboard.Key;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Game implements InputHandler {

    public static final int PADDING = 10;
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 850;

    private static final String PLAYER_IMAGE = "resources/assets/fan.png";
    private static final String GUARD_IMAGE = "resources/assets/guard.png";
    private static final int PLAYER_ONE_INITIAL_X = 50;
    private static final int PLAYER_ONE_INITIAL_Y = 50;
    private static final int NUM_GUARDS = 3;

    private ColisionDetector collisionDetector;
    private Player playerOne;
    private List<Guards> guards;
    private Level level;
    private Queen queen;
    private Portal portal;

    public void init() {
        guards = new LinkedList<>();
        playerOne = new Player(PLAYER_ONE_INITIAL_X, PLAYER_ONE_INITIAL_Y, PLAYER_IMAGE, collisionDetector);

        level = Level.LEVEL1;
        queen = level.getQueen();
        portal = level.getPortal();
        collisionDetector = new ColisionDetector(guards, level.getQueen(), level.getKey(), level.getPortal(), level.getWizard());
        playerOne = new Player(PLAYER_ONE_INITIAL_X, PLAYER_ONE_INITIAL_Y, PLAYER_IMAGE, collisionDetector);

    }

    public void start() {
        initWalls();

        createGuards(level);

        playerOne.show();


/*
        for (Guards guard : guards) {
            guard.show();
        }
        */

        while (!playerOne.isGameOver()) {
            if (playerOne.isCaught()) {
                resetPlayer();
            }
            if (collisionDetector.hitsKey(playerOne)) {
                playerOne.pickKey();
                level.keyPicked();
            }
            if (collisionDetector.hitsQueen(playerOne)) {
                if (playerOne.hasKey()) {
                    resetPlayer();

                    initNextLevelWalls();

                }
            }
            if (collisionDetector.hitsPortal(playerOne)) {

                playerOne.teleport(queen.getRepresentation().getX(), queen.getRepresentation().getY());
            }

            if (collisionDetector.hitsWizard(playerOne)) {
                portal.show();
                playerOne.teleport(portal.getRepresentation().getX(), portal.getRepresentation().getY());
            }
            movePlayers();
            moveGuards();

            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void createGuards(Level level) {
        for (int i = 0; i < NUM_GUARDS; i += 1) {

            Point point = getNewPoint();
            Guards guard = new Guards(new Picture( point.getX(), point.getY(), GUARD_IMAGE),collisionDetector);

            boolean test = true;
            while (test) {
                point = getNewPoint();

                guard.setRepresentation(new Picture(point.getX(), point.getY(), GUARD_IMAGE));
                if (!guard.overlaps(level.getWalls())) {
                    test = false;
                }
            }
            guards.add(guard);
            guard.show();
        }

    }


    private Point getNewPoint() {

        int temp_x = (int) (Math.random() * (Game.WIDTH - PADDING - Game.PADDING)) + Game.PADDING * 3;
        int temp_y = (int) (Math.random() * (Game.HEIGHT - PADDING - Game.PADDING)) + Game.PADDING;
        return new Point(temp_x, temp_y);
    }

    private void initWalls() {
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

    public void initNextLevelWalls() {
        level.hide();
        level = level.getNext();
        collisionDetector = new ColisionDetector(guards, level.getQueen(), level.getKey(), level.getPortal(), level.getWizard());
        collisionDetector.setWalls(level.getWalls());
        playerOne.setColisionDetector(collisionDetector);
        level.show();
    }

    public void resetGame() {

        playerOne.reset(PLAYER_ONE_INITIAL_X, PLAYER_ONE_INITIAL_Y);

        for (Guards guard : guards) {
            guard.hide();
        }

        guards.clear();
        level = Level.LEVEL2;
        collisionDetector.setWalls(level.getWalls());
        createGuards(level);
    }

    public void resetPlayer() {
        playerOne.reset(PLAYER_ONE_INITIAL_X, PLAYER_ONE_INITIAL_Y);
        playerOne.show();
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

    public Level getLevel() {
        return level;
    }
}
