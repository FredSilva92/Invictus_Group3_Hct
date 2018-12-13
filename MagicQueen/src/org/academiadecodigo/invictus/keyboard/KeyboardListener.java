package org.academiadecodigo.invictus.keyboard;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardListener implements KeyboardHandler {

    private InputHandler handler;

    public void init() {
        Keyboard keyboard = new Keyboard(this);

        for (Key key : Key.values()) {
            for (KeyboardEventType type : KeyboardEventType.values()) {
                subscribe(keyboard, key.getKeyCode(), type);
            }
        }
    }

    private void subscribe(Keyboard keyboard, int keyCode, KeyboardEventType type) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(keyCode);
        event.setKeyboardEventType(type);
        keyboard.addEventListener(event);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        handler.press(decode(keyboardEvent.getKey()));
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        handler.release(decode(keyboardEvent.getKey()));
    }

    private Key decode(int keyCode) {

        for (Key key : Key.values()) {
            if (key.getKeyCode() == keyCode) {
                return key;
            }
        }

        throw new IllegalArgumentException("unknown key code");
    }

    public void setHandler(InputHandler handler) {
        this.handler = handler;
    }
}
