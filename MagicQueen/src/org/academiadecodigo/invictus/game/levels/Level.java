package org.academiadecodigo.invictus.game.levels;

import org.academiadecodigo.invictus.game.Game;
import org.academiadecodigo.invictus.game.representable.Key;
import org.academiadecodigo.invictus.game.representable.Queen;
import org.academiadecodigo.invictus.game.representable.Representable;
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
    private static final String KEY_PATH = "assets/key.png";
    private static final int WALL_SIZE = 24;
    private List<Representable> walls;
    private Queen queen;
    private Key key;


    Level(String path) {
        walls = new LinkedList<Representable>();
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
                        walls.add(new Representable(new Picture(
                                Game.PADDING + i * WALL_SIZE,
                                Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE,
                                BRICK_IMAGE_PATH
                        )));
                    }
                    if (chars[i].equals("4")) {
                        key = new Key(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, KEY_PATH));
                    }
                    if (path.contains("level1")) {
                        if (chars[i].equals("3")) {
                            queen = new Queen(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, ROGER_TAYLOR_PATH));
                        }
                    }
                    if (path.contains("level2")) {
                        if (chars[i].equals("3")) {
                            queen = new Queen(new Picture(Game.PADDING + i * WALL_SIZE, Game.PADDING + (reader.getLineNumber() - 1) * WALL_SIZE, ROGER_TAYLOR_PATH));
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
            queen.show();
            key.show();
    }

    public void hide() {
        for (Representable wall : walls) {
            wall.hide();
        }
            queen.hide();
            key.hide();
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
}