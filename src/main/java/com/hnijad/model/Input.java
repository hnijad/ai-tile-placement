package com.hnijad.model;

import java.util.Arrays;

import static com.hnijad.model.Constants.MAX_BUSH_NUM;

public class Input {
    private int[][] landscape;

    private int[] tiles;

    private int[] target;

    public Input(int[][] landscape, int[] tiles, int[] target) {
        this.landscape = landscape;
        this.tiles = tiles;
        this.target = target;
    }

    public int[][] getLandscape() {
        return landscape;
    }

    public int[] getTiles() {
        return tiles;
    }

    public int[] getTarget() {
        return target;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < landscape.length; i++) {
            sb.append(Arrays.toString(landscape[i]));
            sb.append(System.lineSeparator());
        }
        sb.append(Arrays.toString(tiles));
        sb.append(System.lineSeparator());
        sb.append("[");
        for (int i = 1; i < MAX_BUSH_NUM; i++) {
            if (i + 1 == MAX_BUSH_NUM) sb.append(this.target[i]).append("]");
            else sb.append(this.target[i]).append(", ");
        }
        return "Input{\n" + sb + "\n}";
    }
}
