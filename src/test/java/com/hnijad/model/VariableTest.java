package com.hnijad.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static com.hnijad.model.Tile.*;
import static org.junit.jupiter.api.Assertions.*;

class VariableTest {
    private Variable variable;

    @BeforeEach
    void setUp() {
        variable = new Variable(
                0,
                0, 0,
                new ArrayList<>(Arrays.asList(FULL_BLOCK.name(), BOUNDARY.name(), EL_SHAPE.name())));
    }

    @Test
    void getNeighboursIndex() {
        // given
        int size = 25; // number of variables
        // when
        var neighbours = variable.getNeighboursIndex(size);

        // then
        Assertions.assertEquals(2, neighbours.size()); // first var has 2 neighbours one square right and one square down
    }

    @Test
    void getNeighboursIndexNoNeighbours() {
        // given
        int size = 1; // number of variables
        // when
        var neighbours = variable.getNeighboursIndex(size);

        // then
        Assertions.assertEquals(0, neighbours.size()); // when there is one variable no neighbour exist
    }
}