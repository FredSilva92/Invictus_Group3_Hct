package org.academiadecodigo.invictus;

import org.academiadecodigo.invictus.game.Game;
import org.academiadecodigo.invictus.keyboard.KeyboardListener;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Manager {

    private Game game;
    private KeyboardListener listener;

    public void init() {
        Rectangle border = new Rectangle(Game.PADDING, Game.PADDING, Game.WIDTH, Game.HEIGHT);
        border.setColor(Color.DARK_GRAY);
        border.draw();

        listener = new KeyboardListener();
        listener.init();

        game = new Game();
        game.init();
    }

    public void start() {
        while (true) {
            startGame();
            reset();
        }
    }

    private void startGame() {
        listener.setHandler(game);
        game.start();
    }

    private void reset() {
        game.reset();
    }
}
