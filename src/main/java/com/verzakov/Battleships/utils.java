package com.verzakov.Battleships;

public final class utils {
    public static int transCharToInt(char ch) {
        int rInt;
        switch (ch){
            case 'A':
                rInt = 0;
                break;
            case 'B':
                rInt = 1;
                break;
            case 'C':
                rInt = 2;
                break;
            case 'D':
                rInt = 3;
                break;
            case 'E':
                rInt = 4;
                break;
            case 'F':
                rInt = 5;
                break;
            case 'G':
                rInt = 6;
                break;
            case 'H':
                rInt = 7;
                break;
            case 'I':
                rInt = 8;
                break;
            case 'J':
                rInt = 9;
                break;
            default:
                rInt = 11;
        }
        return rInt;
    }
}
