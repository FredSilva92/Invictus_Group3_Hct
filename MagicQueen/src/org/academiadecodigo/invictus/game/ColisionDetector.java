package org.academiadecodigo.invictus.game;

import org.academiadecodigo.invictus.game.representable.*;

import java.util.List;

public class ColisionDetector {

    private List<Guards> guards;
    private List<Representable> walls;
    private Queen queen;
    private Key key;

    public ColisionDetector(List<Guards> guards, Queen queen, Key key) {
        this.guards = guards;
        this.queen = queen;
        this.key = key;
    }

    public boolean hitsGuard(Representable representable) {
        return check(guards, representable);
    }

    public boolean hitsWall(Representable representable) {
        return check(walls, representable);
    }

    public boolean hitsQueen(Player player) {
        if (queen.overlaps(player)) {
            return true;
        }
        return false;
    }

    public boolean hitKey(Player player){
        if (key.overlaps(player)) {
            return true;
        }
        return false;
    }

    private boolean check(List<? extends Representable> representables, Representable target) {
        for (Representable other : representables) {
            if (other.overlaps(target)) {
                return true;
            }
        }

        return false;
    }

    public void setWalls(List<Representable> walls) {
        System.out.println("ENTROU@@@@@@@@@@@@@@@@");
        this.walls = walls;
    }
}
