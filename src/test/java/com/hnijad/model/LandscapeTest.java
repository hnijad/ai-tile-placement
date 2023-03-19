package com.hnijad.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.hnijad.model.Tile.FULL_BLOCK;
import static com.hnijad.model.Tile.BOUNDARY;
import static com.hnijad.model.Tile.EL_SHAPE;

class LandscapeTest {
    private Landscape landscape;

    @BeforeEach
    void setUp() {
        int[][] bushGrid = {
                {0, 1, 2, 3},
                {1, 0, 2, 1},
                {3, 3, 3, 4},
                {4, 4, 0, 2}};
        landscape = new Landscape(bushGrid);
    }

    @Test
    void isConstraintSatisfied() {
        // given
        int[] constraint1 = {0, 3, 3, 4, 3}; // satisfied constraint
        int[] constraint2 = {0, 3, 3, 4, 6}; // unsatisfied constraint

        // when
        boolean satisfied1 = landscape.isConstraintSatisfied(constraint1);
        boolean satisfied2 = landscape.isConstraintSatisfied(constraint2);

        // then
        Assertions.assertTrue(satisfied1);
        Assertions.assertFalse(satisfied2);
    }

    @Test
    void isConstraintViolated() {
        // given
        int[] constraint1 = {0, 12, 6, 4, 3}; // violated constraint
        int[] constraint2 = {0, 1, 1, 1, 1}; // un-violated constraint

        // when
        boolean satisfied1 = landscape.isConstraintViolated(constraint1);
        boolean satisfied2 = landscape.isConstraintViolated(constraint2);

        // then
        Assertions.assertTrue(satisfied1);
        Assertions.assertFalse(satisfied2);
    }

    @Test
    void placeTile() {
        // given
        int row = 0;
        int col = 0;
        Tile tile = FULL_BLOCK;

        // when
        landscape.placeTile(row, col, tile);
        int [] bushes = landscape.getCountOfEachVisibleBush();

        // then

        // no type 1 bush should be visible after full block
        Assertions.assertEquals(0, bushes[1]);
        Assertions.assertEquals(0, bushes[2]);
        Assertions.assertEquals(0, bushes[3]);
        Assertions.assertEquals(0, bushes[4]);
    }

    @Test
    void countBushesVisibleWithTile() {
        // given
        int row = 0;
        int col = 0;
        Tile tile = BOUNDARY;

        // when
        landscape.placeTile(row, col, tile);
        int cnt = landscape.countBushesVisibleWithTile(row, col, tile);

        // then

        /*
        *   After outer boundary is applied
        *   B B B B
        *   B 0 2 B
        *   B 3 3 B
        *   B B B B
        */
        Assertions.assertEquals(3, cnt);
    }

    @Test
    void rollBackTilePlacement() {
        // given
        int row = 0;
        int col = 0;
        Tile tile = EL_SHAPE;

        // when
        landscape.placeTile(row, col, tile);
        landscape.rollBackTilePlacement(row, col);
        int [] bushes = landscape.getCountOfEachVisibleBush();

        // then
        Assertions.assertEquals(3, bushes[1]);
        Assertions.assertEquals(3, bushes[2]);
        Assertions.assertEquals(4, bushes[3]);
        Assertions.assertEquals(3, bushes[4]);
    }

    @Test
    void findUnAssignedCoordinates() {
        // when
        var cords = landscape.findUnAssignedCoordinates();

        // then
        Assertions.assertEquals(2, cords.size());
        Assertions.assertEquals(0, cords.get(0));
        Assertions.assertEquals(0, cords.get(1));
    }

    @Test
    void findUnAssignedCoordinates_NoCoords() {
        // when
        landscape.placeTile(0, 0, FULL_BLOCK);
        var cords = landscape.findUnAssignedCoordinates();

        // then
        Assertions.assertEquals(0, cords.size());
    }

    @Test
    void getCurrentBushCountByBushNumber() {
        // when
        int type1 = landscape.getCurrentBushCountByBushNumber(1);
        int type2 = landscape.getCurrentBushCountByBushNumber(2);
        int type3 = landscape.getCurrentBushCountByBushNumber(3);
        int type4 = landscape.getCurrentBushCountByBushNumber(4);

        // then
        Assertions.assertEquals(3, type1);
        Assertions.assertEquals(3, type2);
        Assertions.assertEquals(4, type3);
        Assertions.assertEquals(3, type4);
    }

    @Test
    void getCountOfEachVisibleBush() {
        // when
        int [] bushesCnt = landscape.getCountOfEachVisibleBush();

        // then
        Assertions.assertEquals(3, bushesCnt[1]);
        Assertions.assertEquals(3, bushesCnt[2]);
        Assertions.assertEquals(4, bushesCnt[3]);
        Assertions.assertEquals(3, bushesCnt[4]);
    }
}