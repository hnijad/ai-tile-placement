package com.hnijad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class MapperTest {

    @Test
    void mapInputToVariable() {
        // given
        int n = 20;

        // when
        var varList = Mapper.mapInputToVariable(n);

        //then
        Assertions.assertEquals(25, varList.size());
        Assertions.assertTrue(varList.get(0).getDomain().contains("FULL_BLOCK"));
        Assertions.assertTrue(varList.get(0).getDomain().contains("BOUNDARY"));
        Assertions.assertTrue(varList.get(0).getDomain().contains("EL_SHAPE"));
    }

    @Test
    void mapTargetStringsToArray() {
        // given
        var targetStrings = List.of("1:12", "2:11", "3:20", "4:10");

        // when

        int[] bushes = Mapper.mapTargetStringsToArray(targetStrings);

        // then
        Assertions.assertEquals(12, bushes[1]);
        Assertions.assertEquals(11, bushes[2]);
        Assertions.assertEquals(20, bushes[3]);
        Assertions.assertEquals(10, bushes[4]);
    }

    @Test
    void mapTilesToArray() {
        // given
        String tileStr = "{OUTER_BOUNDARY=7, EL_SHAPE=8, FULL_BLOCK=10}";

        // when
        int [] res = Mapper.mapTilesToArray(tileStr);

        // then
        Assertions.assertEquals(10, res[0]);
        Assertions.assertEquals(7, res[1]);
        Assertions.assertEquals(8, res[2]);
    }

    @Test
    void mapLandscapeTo2DArray() {
        // given
        var landscapeString = List.of("4", "  3 4", "3 2", "4 3 2");

        // when

        int[][] landscape = Mapper.mapLandscapeTo2DArray(landscapeString);

        // then
        Assertions.assertEquals(4, landscape[0][0]);
        Assertions.assertEquals(0, landscape[0][1]);
        Assertions.assertEquals(0, landscape[0][2]);
        Assertions.assertEquals(0, landscape[0][3]);

        Assertions.assertEquals(0, landscape[1][0]);
        Assertions.assertEquals(3, landscape[1][1]);
        Assertions.assertEquals(4, landscape[1][2]);
        Assertions.assertEquals(0, landscape[1][3]);

        Assertions.assertEquals(3, landscape[2][0]);
        Assertions.assertEquals(2, landscape[2][1]);
        Assertions.assertEquals(0, landscape[2][2]);
        Assertions.assertEquals(0, landscape[2][3]);

        Assertions.assertEquals(4, landscape[3][0]);
        Assertions.assertEquals(3, landscape[3][1]);
        Assertions.assertEquals(2, landscape[3][2]);
        Assertions.assertEquals(0, landscape[3][3]);
    }
}