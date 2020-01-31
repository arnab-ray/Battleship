package com.battleship;

import java.util.Arrays;

public class BattleShip extends Ship {

    public BattleShip() {
        this.setLength(4);
        this.setHits(new boolean[4]);
        Arrays.fill(this.getHits(), false);
    }

    @Override
    public String getTypeOfShip() {
        return "battleship";
    }
}
