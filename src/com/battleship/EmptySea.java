package com.battleship;

import java.util.Arrays;

public class EmptySea extends Ship {

    public EmptySea() {
        this.setLength(1);
        this.setHits(new boolean[1]);
        Arrays.fill(this.getHits(), false);
    }

    @Override
    public String getTypeOfShip() {
        return "empty";
    }

    @Override
    public boolean shootAt(int row, int column) {
        this.getHits()[0] = true;
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

    @Override
    public String toString() {
        return this.getHits()[0] ? "-" : ".";
    }
}
