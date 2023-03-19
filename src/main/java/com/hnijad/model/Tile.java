package com.hnijad.model;

public enum Tile {
    FULL_BLOCK(new boolean[][]{
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false}
    }, 0),
    EL_SHAPE(new boolean[][]{
            {false, true, true, true},
            {false, true, true, true},
            {false, true, true, true},
            {false, false, false, false}
    }, 2),
    BOUNDARY(new boolean[][]{
            {false, false, false, false},
            {false, true, true, false},
            {false, true, true, false},
            {false, false, false, false}
    }, 1);

    private final boolean[][] visibility;
    private final int index;

    Tile(boolean[][] visibility, int index) {
        this.visibility = visibility;
        this.index = index;
    }

    public boolean getVisibility(int row, int col) {
        return visibility[row][col];
    }

    public int getIndex() {
        return index;
    }
}