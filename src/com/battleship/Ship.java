package com.battleship;

public abstract class Ship {

    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hits = new boolean[5];

    public abstract String getTypeOfShip();

    // returns true if every segment of ship is sunk
    public boolean isSunk() {
        for(boolean hit : hits) {
            if (!hit)
                return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return isSunk() ? "X" : "S";
    }

    public int getBowRow() {
        return this.bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public int getBowColumn() {
        return this.bowColumn;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return this.horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean[] getHits() {
        return this.hits;
    }

    public void setHits(boolean[] hits) {
        this.hits = hits;
    }

    private boolean isShipAbsent(Ocean ocean, int row, int column) {
        if(row < 0 || column < 0 || row > 9 || column > 9)
            return false;
        return ocean.getShips()[row][column] == null || ocean.getShips()[row][column].getTypeOfShip() == null ||
                ocean.getShips()[row][column].getTypeOfShip().equals("empty");
    }

    public boolean isValidPositionOfPlacingShip(int bowRow, int bowColumn, boolean horizontal, Ocean ocean) {
        if(horizontal) {
            if(bowColumn + getLength() > 10)
                return false;
            for(int i = bowRow - 1; i < bowRow + 2; i++) {
                for(int j = bowColumn - 1; j < bowColumn + getLength() + 2; j++) {
                    if(!isShipAbsent(ocean, i, j))
                        return false;
                }
            }
        } else {
            if(bowRow + getLength() > 10)
                return false;
            for(int i = bowRow - 1; i < bowRow + getLength() + 2; i++) {
                for(int j = bowColumn - 1; j < bowColumn + 2; j++)
                    if(!isShipAbsent(ocean, i, j))
                        return false;
            }
        }

        return true;
    }

    public void placeShip(int bowRow, int bowColumn, boolean horizontal, Ocean ocean) {
        this.bowRow = bowRow;
        this.bowColumn = bowColumn;
        this.horizontal = horizontal;

        if(horizontal) {
            for(int j = bowColumn; j < bowColumn + this.length; j++)
                ocean.getShips()[bowRow][j] = this;
        } else {
            for(int i = bowRow; i < bowRow + this.length; i++)
                ocean.getShips()[i][bowColumn] = this;
        }
    }

    public boolean wasShotAt(int row, int column) {
        if(horizontal)
            return hits[column - this.bowColumn];

        return hits[row - this.bowRow];
    }

    public boolean shootAt(int row, int column) {
        if(!isSunk()) {
            if(horizontal) {
                if(row == this.bowRow && column < this.bowColumn + this.length) {
                    hits[column - this.bowColumn] = true;
                    return true;
                }
            } else {
                if(column == this.bowColumn && row < this.bowRow + this.length) {
                    hits[row - this.bowRow] = true;
                    return true;
                }
            }
        }

        return false;
    }

}
