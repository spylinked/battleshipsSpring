package com.verzakov.Battleships;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;

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

    public boolean isPlaced() {
        return placed;
    }
    public int getSize() {
        return type.getSize();
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    public boolean isDestroyed() { //TODO переделать на расчет после выстрелов
        for (Cell cell: shipCells) {
            if(!cell.isShot()) {
                return false;
            }
        }
        return true;
    }

    public void fillAreaAround() {
        for (int i = 0; i < shipCells.length; i++) {
            for (int j = 0; j < shipCells[i].getNeighbours().size(); j++) {
                shipCells[i].getNeighbours().get(j).setShot(true);
            }
        }
    }

    public void placeShip() {
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("Расположите " + type.getSize() + "-х палубный корабль: ");
            String line = scan.nextLine();
            Matcher m = type.getPattern().matcher(line);
            String[] cells = line.split(";");
            boolean isError = false;
            if (m.find()) {
                int[][] shipPos = new int[cells.length][2];
                boolean horizontal = true;
                boolean vertical = true;
                boolean badShip = false;
                boolean occupied = false;
                for (int i = 0; i < cells.length; i++) {
                    shipPos[i][0] = Integer.parseInt(cells[i].replaceAll("[\\D]", ""))-1;
                    shipPos[i][1] = utils.transCharToInt(cells[i].charAt(0));
                    if(!field.getCellGrid()[shipPos[i][0]][shipPos[i][1]].checkPlace()) {
                        System.out.println("Невозможно расположить на " + cells[i] + " рядом другой корабль");
                        occupied = true;
                        break;
                    }
                }
                if(occupied){
                    continue;
                }
                for (int i = 1; i < shipPos.length; i++) {
                    if(shipPos[i][0]!=shipPos[i-1][0]) {
                        horizontal = false;
                    }
                    if(shipPos[i][1]!=shipPos[i-1][1]) {
                        vertical = false;
                    }
                }
                if (!horizontal && !vertical) {
                    System.out.println("Корабль должен распологаться вертикально или горизонтально.");
                    continue;
                }
                if (vertical){
                    Arrays.sort(shipPos, Comparator.comparingInt(o -> o[0]));
                    for (int i = 1; i < shipPos.length; i++) {
                        if (shipPos[i-1][0]+1 != shipPos[i][0]) {
                            badShip = true;
                            break;
                        }
                    }
                }
                if (horizontal && !badShip){
                    Arrays.sort(shipPos, Comparator.comparingInt(o -> o[1]));
                    for (int i = 1; i < shipPos.length; i++) {
                        if (shipPos[i-1][1]+1 != shipPos[i][1]) {
                            badShip = true;
                            break;
                        }
                    }
                }
                if(badShip){
                    System.out.println("Клетки корабля не соединены или несколько одинаковых клеток.");
                    continue;
                }
                setShipCells(shipPos);
                break;

            } else {
                System.out.println("Не правильный формат строки: " + line);
                continue;
            }
        }
    }
    public void setShipCells(int[][] shipPos){
        for (int i = 0; i < shipPos.length; i++) {
            field.getCellGrid()[shipPos[i][0]][shipPos[i][1]].setShip(this);
            shipCells[i] = field.getCellGrid()[shipPos[i][0]][shipPos[i][1]];
        }
    }
}
