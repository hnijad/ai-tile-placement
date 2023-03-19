package com.hnijad;

import com.hnijad.model.Input;
import com.hnijad.model.Landscape;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {
    @Test
    void getSolution() {
        // given

        int[][] bushGrid = {
                {0, 1, 2, 3},
                {1, 0, 2, 1},
                {3, 3, 3, 4},
                {4, 4, 0, 2}};
        int[] tiles = {1, 0, 0};
        int[] target = {0, 0, 0, 0, 0};
        var input = new Input(bushGrid, tiles, target);
        var solver = new Solver(input);

        // when
        var sol = solver.getSolution();

        // then
        Assertions.assertNotNull(sol);
    }

    @Test
    void getSolutionUsingSimpleBackTracking() {
        // given
        int[][] bushGrid = {
                {0, 1, 2, 3},
                {1, 0, 2, 1},
                {3, 3, 3, 4},
                {4, 4, 0, 2}};
        int[] tiles = {1, 0, 0};
        int[] target = {0, 0, 2, 0, 0};
        var input = new Input(bushGrid, tiles, target);
        var solver = new Solver(input);

        // when
        var sol = solver.getSolutionUsingSimpleBackTracking();

        // then
        Assertions.assertNull(sol); // no solution
    }
}