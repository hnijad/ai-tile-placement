package com.hnijad;

import com.hnijad.model.Input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.hnijad.model.Constants.MAX_BUSH_NUM;
import static com.hnijad.model.Constants.MAX_TILES_NUM;

public class FileParser {
    private FileParser() {
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
}