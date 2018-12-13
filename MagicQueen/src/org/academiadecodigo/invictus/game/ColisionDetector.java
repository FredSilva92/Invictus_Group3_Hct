package org.academiadecodigo.invictus.game;

import org.academiadecodigo.invictus.game.representable.Guards;
import org.academiadecodigo.invictus.game.representable.Representable;

import java.util.List;

public class ColisionDetector {

    private List<Guards> guards;
    private List<Representable> walls;

    public ColisionDetector(List<Guards> guards) {
        this.guards = guards;
    }

    public boolean hitsGuard(Representable representable) {
        return check(guards, representable);
    }

    public boolean hitsWall(Representable representable) {
        return check(walls, representable);
    }

    private boolean check(List<? extends Representable> representables, Representable target) {
        for (Representable other : representables) {
            if (target.overlaps(other)) {
                return true;
            }
        }

        return false;
    }

    public void setWalls(List<Representable> walls) {
        this.walls = walls;
    }
}
