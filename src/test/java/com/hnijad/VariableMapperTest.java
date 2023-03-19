package com.hnijad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VariableMapperTest {

    @Test
    void mapInputToVariable() {
        // given
        int n = 20;

        // when
        var varList = VariableMapper.mapInputToVariable(n);

        //then
        Assertions.assertEquals(25, varList.size());
        Assertions.assertTrue(varList.get(0).getDomain().contains("FULL_BLOCK"));
        Assertions.assertTrue(varList.get(0).getDomain().contains("BOUNDARY"));
        Assertions.assertTrue(varList.get(0).getDomain().contains("EL_SHAPE"));
    }
}