package com.battleship;

import java.util.Arrays;

public class Carrier extends Ship {

    public Carrier() {
        this.setLength(5);
        this.setHits(new boolean[5]);
        Arrays.fill(this.getHits(), false);
    }

    @Override
    public String getTypeOfShip() {
        return "carrier";
    }
}
