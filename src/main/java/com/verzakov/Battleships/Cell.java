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

    public String getStatus(){
        if(isShot()){
            if(isContainShip())
                return "shot";
            else
                return "miss";
        } else {
            if(isContainShip())
                return "ship";
            else
                return "empty";
        }
    }
    public String getRivalStatus(){
        if(isShot()){
            if(isContainShip())
                return "shot";
            else
                return "miss";
        } else {
                return "empty";
        }
    }

    public Boolean canPlace(int size, int direction) { //direction 0-UP, 1-RIGHT, 2-DOWN, 3-LEFT
        if(checkPlace()){
            if(size>1){
                for (Cell neiCell: neighbours) {
                    switch(direction) {
                        case 0:
                            if(neiCell.getPosX() == posX && neiCell.getPosY() == posY-1) {
                                return neiCell.canPlace(size-1, direction);
                            }
                            break;
                        case 1:
                            if(neiCell.getPosX() == posX+1 && neiCell.getPosY() == posY) {
                                return neiCell.canPlace(size-1, direction);
                            }
                            break;
                        case 2:
                            if(neiCell.getPosX() == posX && neiCell.getPosY() == posY+1) {
                                return neiCell.canPlace(size-1, direction);
                            }
                            break;
                        case 3:
                            if(neiCell.getPosX() == posX-1 && neiCell.getPosY() == posY) {
                                return neiCell.canPlace(size-1, direction);
                            }
                            break;
                        default:
                            break;
                    }
                }
            } else {
                return true;
            }
        }
        return false;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public Ship getShip() {
        return this.ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean isContainShip() {
        if(ship!=null){
            return true;
        }
        return false;
    }

    public List<Cell> getNeighbours() {
        return neighbours;
    }

    public boolean checkPlace(){
        if (this.isContainShip())
            return false;
        for (Cell neighbourCell: neighbours) {
            if(neighbourCell.isContainShip())
                return false;
        }
        return true;
    }

    public void fillDiagonal(){
        for (Cell neiCell: neighbours) {
            if ((neiCell.getPosX() == posX-1 && neiCell.getPosY() == posY-1) ||
                    (neiCell.getPosX() == posX+1 && neiCell.getPosY() == posY-1) ||
                    (neiCell.getPosX() == posX-1 && neiCell.getPosY() == posY+1) ||
                    (neiCell.getPosX() == posX+1 && neiCell.getPosY() == posY+1)){
                neiCell.setShot(true);
            }
        }
    }

    public boolean isPossibleShip(){
        if(!isShot()){
            for (Cell neiCell: neighbours) {
                if(neiCell.isShot() && neiCell.isContainShip()){
                    return true;
                }
            }
        }
        return false;
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




}