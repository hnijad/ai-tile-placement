package com.hnijad;

import com.hnijad.model.Input;

import java.io.*;
import java.util.*;

import static com.hnijad.Mapper.mapTilesToArray;
import static com.hnijad.Mapper.mapLandscapeTo2DArray;
import static com.hnijad.Mapper.mapTargetStringsToArray;
import static com.hnijad.model.Constants.MAX_BUSH_NUM;
import static com.hnijad.model.Constants.MAX_TILES_NUM;

public class FileParser {
    public FileParser() {
    }

    public static Input getInput(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner reader = new Scanner(file);
        int n = reader.nextInt();
        int[][] landscape = new int[n][n];
        int[] tiles = new int[MAX_TILES_NUM];
        int[] target = new int[MAX_BUSH_NUM];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                landscape[i][j] = reader.nextInt();
            }
        }

        for (int i = 0; i < MAX_TILES_NUM; i++) {
            tiles[i] = reader.nextInt();
        }

        for (int i = 1; i < MAX_BUSH_NUM; i++) {
            target[i] = reader.nextInt();
        }

        reader.close();
        return new Input(landscape, tiles, target);
    }

    public static Input parseFile(String fileName) {
        int type = -1;
        List<String> landscapeStr = new ArrayList<>();
        List<String> targetStrs = new ArrayList<>();
        String tileStr = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                if (line.startsWith("# Landscape")) {
                    type = 1;
                    continue;
                } else if (line.startsWith("# Tiles")) {
                    type = 2;
                    continue;
                } else if (line.startsWith("# Targets")) {
                    type = 3;
                    continue;
                }
                if (type == 1) {
                    landscapeStr.add(line);
                }
                if (type == 2) {
                    tileStr = line;
                }
                if (type == 3) {
                    targetStrs.add(line);
                }
            }
        } catch (IOException ex) {
            System.out.println("Exception happened in FileParser.parseFile");
            ex.printStackTrace();
        }
        int[] tileArr = mapTilesToArray(tileStr);
        int[] target = mapTargetStringsToArray(targetStrs);
        int[][] landscape = mapLandscapeTo2DArray(landscapeStr);
        return new Input(landscape, tileArr, target);
    }
}