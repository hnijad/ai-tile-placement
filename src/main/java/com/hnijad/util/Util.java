package com.hnijad.util;

import com.hnijad.model.Variable;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private Util() {}

    public static List<Variable> getCopiedVariables(List<Variable> variables) {
        List<Variable> copiedVariables = new ArrayList<>();
        for (var variable : variables) {
            copiedVariables.add(new Variable(variable));
        }
        return copiedVariables;
    }
}
