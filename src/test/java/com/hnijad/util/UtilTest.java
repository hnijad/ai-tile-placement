package com.hnijad.util;

import com.hnijad.model.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hnijad.model.Tile.*;


class UtilTest {

    @Test
    void getCopiedVariables() {
        // given
        List<Variable> variables = new ArrayList<>();
        variables.add(new Variable(
                0,
                0, 0,
                new ArrayList<>(Arrays.asList(FULL_BLOCK.name(), BOUNDARY.name(), EL_SHAPE.name())))
        );
        variables.add(new Variable(
                1,
                0, 1,
                new ArrayList<>(Arrays.asList(FULL_BLOCK.name(), BOUNDARY.name(), EL_SHAPE.name())))
        );

        // when

        var copied = Util.getCopiedVariables(variables);

        // test
        Assertions.assertNotSame(copied, variables); // assert that references are different
        Assertions.assertNotSame(copied.get(0).getDomain(), variables.get(0).getDomain());
    }
}