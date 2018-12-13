package org.academiadecodigo.invictus.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;

public enum  Key {

        UP(KeyboardEvent.KEY_UP),
        DOWN(KeyboardEvent.KEY_DOWN),
        LEFT(KeyboardEvent.KEY_LEFT),
        RIGHT(KeyboardEvent.KEY_RIGHT),
        L(KeyboardEvent.KEY_L),
        W(KeyboardEvent.KEY_W),
        A(KeyboardEvent.KEY_A),
        S(KeyboardEvent.KEY_S),
        D(KeyboardEvent.KEY_D),
        Q(KeyboardEvent.KEY_Q);

        private int keyCode;

        Key(int keyCode) {
            this.keyCode = keyCode;
        }

        public int getKeyCode() {
            return keyCode;
        }
}
