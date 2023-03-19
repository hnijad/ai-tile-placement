package com.hnijad.model;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    private final int index;
    private final int row;

    private final int col;

    private String value;

    private List<String> domain;

    public Variable(int index, int row, int col, List<String> domain) {
        this.index = index;
        this.row = row;
        this.col = col;
        this.value = null;
        this.domain = domain;
    }

    public Variable(Variable variable) {
        this.index = variable.getIndex();
        this.col = variable.getCol();
        this.row = variable.getRow();
        this.value = variable.getValue();
        this.domain = new ArrayList<>();
        for (var x : variable.getDomain()) {
            domain.add(x);
        }
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "row=" + row +
                ", col=" + col +
                ", value=" + value +
                ", domain=" + domain +
                '}';
    }

    public void print() {
        System.out.println("row = " + row + ", col = " + col + ", index =" + index + ", value = " + value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable variable)) return false;
        return row == variable.row && col == variable.col;
    }

    public List<Integer> getNeighboursIndex(int size) {
        List<Integer> neighbours = new ArrayList<>();
        if (index - 1 >= 0) neighbours.add(index - 1);
        if (index + 1 < size * size) neighbours.add(index + 1);
        if (index - size >= 0) neighbours.add(index - size);
        if (index + size < size * size) neighbours.add(index + size);
        return neighbours;
    }
}
