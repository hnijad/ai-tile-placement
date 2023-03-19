package com.hnijad;

import com.hnijad.model.Constants;
import com.hnijad.model.Input;
import com.hnijad.model.Tile;
import com.hnijad.model.Variable;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hnijad.model.Tile.*;

public class Main {
    public static void main(String[] args) {
        try {
            String filePath = args[0];
            solveWithAC3(filePath);
        } catch (Exception e) {
            System.out.println("Exception happened:  " + e);
        }
    }


    private static void solveWithAC3(String filename) throws Exception {
        Input input = FileParser.getInput(filename);
        System.out.println("Finished parsing file=" + filename);

        Solver solver = new Solver(input);

        LocalDateTime start = LocalDateTime.now();

        var solution = solver.getSolution();

        LocalDateTime end = LocalDateTime.now();

        if (solution != null) {
            System.out.println("Solution found using AC3 in " + Duration.between(start, end).getSeconds() + " seconds");
            solver.print();
        } else {
            System.out.println("No solution found using AC3");
        }
    }
}