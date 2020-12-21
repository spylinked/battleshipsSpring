package com.verzakov.Battleships;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Player{
    String name;
    private final BattleField field;

    public Human(String name) {
        this.name = name;
        field = new BattleField();
    }

    @Override
    public int shoot(Player player) {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    public BattleField getField() {
        return field;
    }

    @Override
    public void placeShips() {
        List<Cell> freeCells = new ArrayList<Cell>();
        for (Ship ship : field.getShipsList()) {
            freeCells = getFreeCells(field);
            Collections.shuffle(freeCells);
            for (int i = 0; i < freeCells.size(); i++) {
                List<Integer> dirList = new ArrayList<>();
                if (freeCells.get(i).canPlace(ship.getSize(),0)) //UP
                    dirList.add(0);
                if (freeCells.get(i).canPlace(ship.getSize(),1)) //RIGHT
                    dirList.add(1);
                if (freeCells.get(i).canPlace(ship.getSize(),2)) //DOWN
                    dirList.add(2);
                if (freeCells.get(i).canPlace(ship.getSize(),3)) //LEFT
                    dirList.add(3);
                if(dirList.size() > 0){
                    int[][] shipPos = new int[ship.getSize()][2];
                    Collections.shuffle(dirList);
                    for (int j = 0; j < ship.getSize(); j++) {
                        if(dirList.get(0)==0) {
                            shipPos[j][0] = freeCells.get(i).getPosX();
                            shipPos[j][1] = freeCells.get(i).getPosY()-j;
                        }
                        if(dirList.get(0)==1) {
                            shipPos[j][0] = freeCells.get(i).getPosX()+j;
                            shipPos[j][1] = freeCells.get(i).getPosY();
                        }
                        if(dirList.get(0)==2) {
                            shipPos[j][0] = freeCells.get(i).getPosX();
                            shipPos[j][1] = freeCells.get(i).getPosY()+j;
                        }
                        if(dirList.get(0)==3) {
                            shipPos[j][0] = freeCells.get(i).getPosX()-j;
                            shipPos[j][1] = freeCells.get(i).getPosY();
                        }
                    }
                    ship.setShipCells(shipPos);
                    break;
                }
            }
        }
    }
    public List getFreeCells(BattleField field){ //0 - своё  1-противника
        List<Cell> freeCells = new ArrayList<Cell>();
        List<Cell> occupiedCells = new ArrayList<Cell>();
        for (int i = 0; i < field.getCellGrid().length; i++) {
            for (int j = 0; j < field.getCellGrid()[i].length; j++) {
                if (field.getCellGrid()[i][j].isContainShip()) {
                    occupiedCells.add(field.getCellGrid()[i][j]);
                    for (Cell neiCell: field.getCellGrid()[i][j].getNeighbours()) {
                        if(!neiCell.isContainShip()){
                            occupiedCells.add(neiCell);
                        }
                    }
                }
                freeCells.add(field.getCellGrid()[i][j]);
            }
        }
        freeCells.removeAll(occupiedCells);
        return freeCells;
    }
}
