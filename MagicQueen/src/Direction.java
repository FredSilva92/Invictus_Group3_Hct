package org.academiadecodigo.invictus.game;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public Direction oppositeDirection() {

        Direction opposite = null;

        switch (this) {
            case UP:
                opposite = Direction.DOWN;
                break;
            case DOWN:
                opposite = Direction.UP;
                break;
            case LEFT:
                opposite = Direction.RIGHT;
                break;
            case RIGHT:
                opposite = Direction.LEFT;
                break;
        }

        return opposite;
    }

    public boolean isOpposite(Direction dir) {
        return dir.equals(oppositeDirection());
    }

}
