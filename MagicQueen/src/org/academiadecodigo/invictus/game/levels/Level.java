package org.academiadecodigo.invictus.game.levels;

import org.academiadecodigo.invictus.game.Game;
import org.academiadecodigo.invictus.game.representable.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;

public enum Level {
    LEVEL1("level1"),

    LEVEL2("level2"),

    LEVEL3("level3");


    private static final String BRICK_IMAGE_PATH = "assets/wall.jpg";
    private static final String FILE_PATH = "resources/levels/";
    private static final String ROGER_TAYLOR_PATH = "assets/rogertaylor.png";
    private static final String BRIAN_MAY_PATH = "assets/brianmay.png";
    private static final String KEY_PATH = "assets/key.png";
    private static final String WIZARD_PATH = "assets/wizard.png";
    private static final String PORTAL_PATH = "assets/portal.png";
    private static final int WALL_SIZE = 24;
    private List<Representable> walls;
    private List<FakeWall> fakeWalls;
    private Queen queen;
    private Key key;
    private Portal portal;
    private Wizard wizard;
    private int portalX;
    private int portalY;


    Level(String path) {
        walls = new LinkedList<Representable>();
        fakeWalls = new LinkedList<FakeWall>();
        load(FILE_PATH + path);
    }

    private void load(String path) {
        try {
            LineNumberReader reader = new LineNumberReader(new BufferedReader(new FileReader(path)));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] chars = line.split("");

                for (int i = 0; i < chars.length; i++) {
                    if (chars[i].equals("1")) {
                        Representable wall = new Representable(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, BRICK_IMAGE_PATH));
                        wall.setRepresentation(new Picture(
                                Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, BRICK_IMAGE_PATH));
                        walls.add(wall);
                    }
                    if (chars[i].equals("4")) {
                        key = new Key(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, KEY_PATH));
                    }
                    if (chars[i].equals("5")) {
                        wizard = new Wizard(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, WIZARD_PATH));
                    }
                    if (path.contains("level1")) {
                        if (chars[i].equals("6")) {
                            FakeWall fakeWall = new FakeWall(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, BRICK_IMAGE_PATH));
                            fakeWalls.add(fakeWall);
                            portalX = Game.PADDING + i * WALL_SIZE;
                            portalY = Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE;
                            portal = new Portal(new Picture(portalX, portalY, PORTAL_PATH));


                        }
                    }
                    if (path.contains("level2")) {
                        if (chars[i].equals("6")) {
                            FakeWall fakeWall = new FakeWall(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, BRICK_IMAGE_PATH));
                            fakeWalls.add(fakeWall);
                            portalX = Game.PADDING + i * WALL_SIZE;
                            portalY = Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE;
                            portal = new Portal(new Picture(portalX, portalY, PORTAL_PATH));
                        }
                    }
                    if (path.contains("level1")) {
                        if (chars[i].equals("3")) {
                            queen = new Queen(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, ROGER_TAYLOR_PATH));


                        }
                    }
                    if (chars[i].equals("8")) {
                        FakeWall fakeWall = new FakeWall(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, BRICK_IMAGE_PATH));
                        fakeWalls.add(fakeWall);
                    }
                    if (path.contains("level2")) {
                        if (chars[i].equals("3")) {
                            queen = new Queen(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, BRIAN_MAY_PATH));

                        }
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error generating level: " + e.getMessage());
        }
    }

    public void show() {
        for (Representable wall : walls) {
            wall.show();
        }
        for (FakeWall wall : fakeWalls) {
            wall.show();
        }
        queen.show();
        key.show();
        wizard.show();
    }

    public void hide() {
        for (Representable wall : walls) {
            wall.hide();
        }
        for (FakeWall wall : fakeWalls) {
            wall.delete();
        }
        queen.hide();
        key.hide();
        portal.hide();
        wizard.hide();
    }

    public Level getNext() {
        walls.clear();
        return values()[ordinal() + 1 == values().length ? 0 : ordinal() + 1];
    }

    public List<Representable> getWalls() {
        return walls;
    }

    public Queen getQueen() {
        return queen;
    }

    public Key getKey() {
        return key;
    }

    public void keyPicked() {
        key.hide();
    }

    public Portal getPortal() {
        return portal;
    }

    public Wizard getWizard() {
        return wizard;
    }

    public int getPortalX() {
        return portalX;
    }

    public int getPortalY() {
        return portalY;
    }
}