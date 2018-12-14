package org.academiadecodigo.invictus.game;

import org.academiadecodigo.invictus.game.representable.*;

import javax.sound.sampled.Port;
import java.util.List;

public class ColisionDetector {

    private List<Guards> guards;
    private List<Representable> walls;
    private Queen queen;
    private Key key;
    private Portal portal;
    private Wizard wizard;

    public ColisionDetector(List<Guards> guards, Queen queen, Key key, Portal portal, Wizard wizard) {
        this.guards = guards;
        this.queen = queen;
        this.key = key;
        this.portal = portal;
        this.wizard = wizard;
    }

    public void setWalls(List<Representable> walls) {
        this.walls = walls;
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

    public boolean hitsKey(Player player){
        if (key.overlaps(player)) {
            return true;
        }
        return false;
    }

    public boolean hitsWizard(Player player){
        if (wizard.overlaps(player)) {
            return true;
        }
        return false;
    }

    public boolean hitsPortal(Player player){
        if (portal.overlaps(player)) {
            return true;
        }
        return false;
    }

    private boolean check(List<? extends Representable> representables, Representable target) {
        for (Representable other : representables) {
            if (target.overlaps(other)) {
                return true;
            }
        }

        return false;
    }


}
