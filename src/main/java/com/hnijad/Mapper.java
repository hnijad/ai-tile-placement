package com.hnijad;

import com.hnijad.model.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.hnijad.model.Constants.*;
import static com.hnijad.model.Tile.FULL_BLOCK;
import static com.hnijad.model.Tile.BOUNDARY;
import static com.hnijad.model.Tile.EL_SHAPE;

public class Mapper {
    private Mapper() {
    }

    public static List<Variable> mapInputToVariable(int n) {
        List<Variable> variables = new ArrayList<>();
        int varIndex = 0;
        for (int i = 0; i < n; i += TILES_SIZE) {
            for (int j = 0; j < n; j += TILES_SIZE) {
                var variable = new Variable(
                        varIndex++,
                        i, j,
                        new ArrayList<>(Arrays.asList(FULL_BLOCK.name(), BOUNDARY.name(), EL_SHAPE.name()))
                );
                variables.add(variable);
            }
        }
        return variables;
    }

    public static int[] mapTargetStringsToArray(List<String> targetStrings) {
        int[] target = new int[MAX_BUSH_NUM];
        for (var x : targetStrings) {
            String[] keyValue = x.split(":");
            String key = keyValue[0];
            String value = keyValue[1];
            target[Integer.parseInt(key)] = Integer.parseInt(value);
        }
        return target;
    }

    public static int[] mapTilesToArray(String tiles) {
        tiles = tiles.replaceAll("[{} ]", "");
        String[] pairs = tiles.split(",");
        HashMap<String, Integer> map = new HashMap<>();
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            Integer value = Integer.parseInt(keyValue[1]);
            map.put(key, value);
        }
        int[] tileArr = new int[MAX_TILES_NUM];
        tileArr[0] = map.get("FULL_BLOCK");
        tileArr[1] = map.get("OUTER_BOUNDARY");
        tileArr[2] = map.get("EL_SHAPE");
        return tileArr;
    }

    public static int[][] mapLandscapeTo2DArray(List<String> landscape) {


        int gridSize = landscape.size();

        if (gridSize % 4 != 0) {
            throw new IllegalStateException("Grid size should be divisible by 4");
        }

        int[][] grid = new int[gridSize][gridSize];
        int gridI = 0;
        int gridJ = 0;

        for (String line : landscape) {
            StringBuilder sb = new StringBuilder();
            char prev = ' ';
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    sb.append(line.charAt(i));
                    prev = line.charAt(i);
                } else if (line.charAt(i) == ' ') {
                    if (prev == ' ') {
                        sb.append('0');
                        prev = '0';
                    } else {
                        sb.append(' ');
                        prev = ' ';
                    }
                }
            }
            while (sb.length() != (2 * gridSize) ) {
                if (prev == ' ') {
                    sb.append('0');
                    prev = '0';
                } else {
                    sb.append(' ');
                    prev = ' ';
                }
            }
            String rowStr = sb.toString();
            for (int i = 0; i < rowStr.length(); i++) {
                if (Character.isDigit(rowStr.charAt(i))) {
                    grid[gridI][gridJ++] = rowStr.charAt(i) - '0';
                }
            }
            gridJ = 0;
            gridI++;
        }
        return grid;
    }

}
