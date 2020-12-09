package com.verzakov.Battleships;

import java.util.ArrayList;
import java.util.List;

public class BattleField{
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

    public List<Ship> getShipsList() {
        return shipsList;
    }
    /*
        public void place(int X, int Y){
            if(cellGrid[Y][X].checkPlace()){
                cellGrid[Y][X].setContainShip(true);
            } else {
                System.out.println("Нельзя поставить");
            }
        }
    */
    public int shoot(int X, int Y) {
        if (this.getCellGrid()[X][Y].isShot()) {
            System.out.println("Эта клетка уже открыта!");
            return -1;
        }
        this.getCellGrid()[X][Y].setShot(true);
        if (this.getCellGrid()[X][Y].isContainShip()){
            if (this.getCellGrid()[X][Y].getShip().isDestroyed()){
                this.getCellGrid()[X][Y].getShip().fillAreaAround();
                System.out.println("Убит!");
                if(getAliveShips() == 0) {
                    System.out.println("Вы победили!");
                    return 2;
                }
            } else {
                this.getCellGrid()[X][Y].fillDiagonal();
                System.out.println("Ранен!");
            }
            return 1;
        }
        System.out.println("Мимо!");
        return 0;

    }
    public int getAliveShips(){
        int aliveShipsCount = 0;
        for (Ship ship: shipsList) {
            if(!ship.isDestroyed()) {
                aliveShipsCount++;
            }
        }
        return aliveShipsCount;
    }
    public void printMyField(){
        //System.out.println("   A   B  C  D   E  F  G   H  I   J");
        System.out.println("   A  B  C D  E  F  G H  I J");
        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[i].length; j++) {
                if(j==0) { System.out.printf("%2d ",i+1);}
                if(cellGrid[i][j].isShot()) {
                    if(cellGrid[i][j].isContainShip()) {
                        System.out.print("❌ ");
                    }
                    else {
                        //System.out.print("▪️ ");
                        System.out.print("▪ ");
                    }
                } else {
                    if(cellGrid[i][j].isContainShip()) {
                        System.out.print("\uD83D\uDD32 ");
                    } else {
                        System.out.print("⬜ ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void printEnemyField(){
        //System.out.println("   A   B  C  D   E  F  G   H  I   J");
        System.out.println("   A  B  C D  E  F  G H  I J");
        for (int i = 0; i < cellGrid.length; i++) {
            for (int j = 0; j < cellGrid[i].length; j++) {
                if(j==0) { System.out.printf("%2d ",i+1);}
                if(cellGrid[i][j].isShot()) {
                    if(cellGrid[i][j].isContainShip()) {
                        System.out.print("❌ ");
                    }
                    else {
                        //System.out.print("▪️ ");
                        System.out.print("▪ ");
                    }
                } else {
                    System.out.print("⬜ ");
                }
            }
            System.out.println();
        }
    }
}
