package com.hnijad;

import com.hnijad.model.Landscape;
import com.hnijad.model.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hnijad.model.Constants.TILES_SIZE;
import static com.hnijad.model.Tile.FULL_BLOCK;
import static com.hnijad.model.Tile.OUTER_BOUNDARY;
import static com.hnijad.model.Tile.EL_SHAPE;

public class VariableMapper {
    private VariableMapper() {
    }

    public static List<Variable> mapInputToVariable(int[][] landscape) {
        List<Variable> variables = new ArrayList<>();
        int n = landscape.length;
        for (int i = 0; i < n; i += TILES_SIZE) {
            for (int j = 0; j < n; j += TILES_SIZE) {
                var variable = new Variable(i, j, Arrays.asList(FULL_BLOCK.name(), OUTER_BOUNDARY.name(), EL_SHAPE.name()));
                variables.add(variable);
            }
        }
        return variables;
    }

    public void sortDomain(Variable variable, Landscape landscape) {

    }
}
