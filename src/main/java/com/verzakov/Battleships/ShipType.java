package com.verzakov.Battleships;

import java.util.regex.Pattern;

public enum ShipType {
    BATTLESHIP(4,1,"^([A-J]([1-9]|10));([A-J]([1-9]|10));([A-J]([1-9]|10));([A-J]([1-9]|10))$"),
    CRUISER(3,2, "^([A-J]([1-9]|10));([A-J]([1-9]|10));([A-J]([1-9]|10))$"),
    DESTROYER(2,3, "^([A-J]([1-9]|10));([A-J]([1-9]|10))$"),
    BOAT(1,4, "^([A-J]([1-9]|10))$");

    private final int size;
    private final int needCount;
    private final Pattern pattern;

    ShipType(int size, int needCount, String pattern) {
        this.size = size;
        this.needCount = needCount;
        this.pattern = Pattern.compile(pattern);
    }

    public int getSize() {
        return size;
    }

    public int getNeedCount() {
        return needCount;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
