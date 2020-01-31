package com.battleship;

import java.util.Random;

public class Ocean {

    private Ship[][] ships = new Ship[10][10]; // finding position of ships
    private int shotsFired; // number of shots fired
    private int hitCount; // count the number of hits, multiple hits in same location isn't beneficial
    private int shipsSunk; // count of enemy ships sunk by the user

    public Ocean() {
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                EmptySea emptySea = new EmptySea();
                emptySea.placeShip(i, j, true, this);
            }
        }
    }

    public Ship[][] getShips() {
        return ships;
    }

    public void setShips(Ship[][] ships) {
        this.ships = ships;
    }

    public int getShotsFired() {
        return this.shotsFired;
    }

    public void setShotsFired(int shotsFired) {
        this.shotsFired = shotsFired;
    }

    public int getHitCount() {
        return this.hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public int getShipsSunk() {
        return this.shipsSunk;
    }

    public void setShipsSunk(int shipsSunk) {
        this.shipsSunk = shipsSunk;
    }

    public boolean isGameOver() {
        return this.shipsSunk == 5;
    }

    // Returns if a position contains ship
    public boolean isOccupiedPosition(int row, int column) {
        return !ships[row][column].getTypeOfShip().equals("empty");
    }


    public boolean shootAtPos(int row, int column) {
        this.shotsFired++;
        if(isOccupiedPosition(row, column)) {
            if(ships[row][column].shootAt(row, column)) {
                if(ships[row][column].isSunk()) {
                    System.out.println("You just sunk a " + ships[row][column].getTypeOfShip());
                    shipsSunk++;
                }

                hitCount++;
                return true;
            }

            return false;
        } else {
            ships[row][column].shootAt(row, column);
        }

        return false;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");

        for(int i = 0; i < 10; i++)
            stringBuilder.append(String.format("%3d", i));
        stringBuilder.append("\n");

        for(int i = 0; i < 10; i++) {
            stringBuilder.append(String.format("%2d ", i));
            for (int j = 0; j < 10; j++) {
                if (!ships[i][j].wasShotAt(i, j))  // never been fired
                    stringBuilder.append(".");
                else
                    stringBuilder.append(ships[i][j].toString());

                stringBuilder.append("  ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


    public void placeAllShipsRandomly() {
        Random random = new Random();
//		random.setSeed(10);
        Ship[] ships = new Ship[5];
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                ships[i] = new BattleShip();
            } else if (i == 1) {
                ships[i] = new Carrier();
            } else if (i == 2) {
                ships[i] = new Cruiser();
            } else if (i == 3) {
                ships[i] = new Destroyer();
            } else if (i == 4) {
                ships[i] = new Submarine();
            }
        }

        for (Ship ship : ships) {
            while (true) {
                int row = random.nextInt(10);
                int column = random.nextInt(10);
                boolean horizontal = random.nextBoolean();
//				System.out.println(ship.getShipType() + " "+ row + " " + column + " horizontal? " + horizontal + " ok? "+ ship.okToPlaceShipAt(row, column, horizontal, this));
                if (ship.isValidPositionOfPlacingShip(row, column, horizontal, this)) {
                    ship.placeShip(row, column, horizontal, this);
                    break;
                }
            }
        }
    }

}
