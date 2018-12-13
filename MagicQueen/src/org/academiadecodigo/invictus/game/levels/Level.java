package org.academiadecodigo.invictus.game.levels;

import org.academiadecodigo.invictus.game.Game;
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
    private static final int WALL_SIZE = 25;
    private List<Representable> walls;

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
    }

    public void hide() {
        for (Representable wall : walls) {
            wall.hide();
        }
    }

    public Level getNext() {
        return values()[ordinal() + 1 == values().length ? 0 : ordinal() + 1];
    }

    public List<Representable> getWalls() {
        return walls;
    }
}