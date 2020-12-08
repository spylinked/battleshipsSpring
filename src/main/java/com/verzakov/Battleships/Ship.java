package com.verzakov.Battleships;

public class Ship {
    private final ShipType type;
    private boolean placed;
    private final BattleField field;
    private Cell[] shipCells;

    public Ship(ShipType type, BattleField field) {
        this.type = type;
        this.field = field;
        this.placed = false;
        this.shipCells = new Cell[type.getSize()];
    }
}
