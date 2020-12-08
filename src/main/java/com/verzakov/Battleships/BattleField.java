package com.verzakov.Battleships;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    private Cell[][] cellGrid = new Cell[10][10];
    private List<Ship> shipsList= new ArrayList<Ship>();

    public BattleField() {
        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[i].length; j++) {
                cellGrid[i][j] = new Cell(this,i,j);
            }
        }
        for(Cell[] cellsX: cellGrid)
        {
            for(Cell cell: cellsX)
                cell.fillNeighbours();
        }

        for (ShipType shipType: ShipType.values()) {
            for (int i = 0; i < shipType.getNeedCount(); i++) {
                shipsList.add(new Ship(shipType, this));
            }
        }
    }
    public Cell[][] getCellGrid() {
        return cellGrid;
    }
}
