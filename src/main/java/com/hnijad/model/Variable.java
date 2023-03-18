package com.hnijad.model;

import java.util.List;

public class Variable {
    private final int row;

    private final int col;

    private String value;

    private List<String> domain;

    public Variable(int row, int col, List<String> domain) {
        this.row = row;
        this.col = col;
        this.value = null;
        this.domain = domain;
    }

    public Variable(Variable variable) {
        this.col = variable.getCol();
        this.row = variable.getRow();
        this.value = variable.getValue();
        this.domain = variable.getDomain();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable variable)) return false;
        return row == variable.row && col == variable.col;
    }
}
