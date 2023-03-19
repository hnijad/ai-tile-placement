package com.hnijad.model;

public class CellState {
    private final int bushNumber;
    private String cover;
    private boolean isVisible;

    public CellState(int bushNumber) {
        this.bushNumber = bushNumber;
        this.isVisible = true;
        this.cover = null;
    }

    public CellState(CellState other) {
        this.bushNumber = other.bushNumber;
        this.cover = other.cover;
        this.isVisible = other.isVisible;
    }

    public String getCover() {
        return cover;
    }

    public int getBushNumber() {
        return bushNumber;
    }
    public boolean isVisible() {
        return isVisible;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public String toString() {
        if (isVisible) {
            return String.valueOf(bushNumber);
        }
        return cover.substring(0, 1);
    }
}
