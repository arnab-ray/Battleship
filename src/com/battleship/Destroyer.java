package com.battleship;

import java.util.Arrays;

public class Destroyer extends Ship {

    public Destroyer() {
        this.setLength(2);
        this.setHits(new boolean[2]);
        Arrays.fill(this.getHits(), false);
    }

    @Override
    public String getTypeOfShip() {
        return "destroyer";
    }
}
