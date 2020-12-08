package com.verzakov.Battleships;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private boolean shot = false;
    private boolean containShip = false;
    private final BattleField field;
    private final int posX;
    private final int posY;
    private Ship ship;
    private final List<Cell> neighbours = new ArrayList<Cell>();

    public Cell(BattleField field, int posX, int posY) {
        this.field = field;
        this.posX = posX;
        this.posY = posY;

    }

    public String getXY(){
        return posX+"_"+posY;
    }

    public void fillNeighbours(){
        if(posX>0) {
            this.field.getCellGrid()[posX - 1][posY].getNeighbours().add(this);
            if(posY>0)
                this.field.getCellGrid()[posX - 1][posY - 1].getNeighbours().add(this);
            if(posY<9)
                this.field.getCellGrid()[posX - 1][posY + 1].getNeighbours().add(this);
        }
        if(posX<9) {
            this.field.getCellGrid()[posX+1][posY].getNeighbours().add(this);
            if(posY>0)
                this.field.getCellGrid()[posX+1][posY-1].getNeighbours().add(this);
            if(posY<9)
                this.field.getCellGrid()[posX+1][posY+1].getNeighbours().add(this);

        }
        if(posY>0) {
            this.field.getCellGrid()[posX][posY - 1].getNeighbours().add(this);
        }
        if(posY<9) {
            this.field.getCellGrid()[posX][posY + 1].getNeighbours().add(this);
        }
    }
    public List<Cell> getNeighbours() {
        return neighbours;
    }
}
