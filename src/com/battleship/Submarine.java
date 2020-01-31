package com.battleship;

import java.util.Arrays;

public class Submarine extends Ship {

    public Submarine() {
        this.setLength(3);
        this.setHits(new boolean[3]);
        Arrays.fill(this.getHits(), false);
    }

    @Override
    public String getTypeOfShip() {
        return "submarine";
    }
}
