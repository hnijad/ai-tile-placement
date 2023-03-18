package com.hnijad.model;

import java.util.List;

public class Landscape {
    private final CellState[][] cellStates;

    private final int[] currentBushCount;

    public Landscape(int[][] bushGrid) {
        int n = bushGrid.length;
        this.cellStates = new CellState[n][n];
        this.currentBushCount = new int[Constants.MAX_BUSH_NUM];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cellStates[i][j] = new CellState(bushGrid[i][j]);
                currentBushCount[bushGrid[i][j]]++;
            }
        }

    }

    public Landscape(Landscape other) {
        int n = other.getCellStates().length;
        this.cellStates = new CellState[n][n];
        this.currentBushCount = new int[Constants.MAX_BUSH_NUM];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.cellStates[i][j] = new CellState(other.getCellState(i, j));
            }
        }
        for (int i = 0; i < Constants.MAX_BUSH_NUM; i++) {
            this.currentBushCount[i] = other.getCurrentBushCountByBushNumber(i);
        }
    }

    public boolean isConstraintSatisfied(int[] constraint) {
        return currentBushCount[1] == constraint[1] &&
                currentBushCount[2] == constraint[2] &&
                currentBushCount[3] == constraint[3] &&
                currentBushCount[4] == constraint[4];
    }

    public boolean isConstraintViolated(int[] constraint) {
        return currentBushCount[1] < constraint[1] ||
                currentBushCount[2] < constraint[2] ||
                currentBushCount[3] < constraint[3] ||
                currentBushCount[4] < constraint[4];
    }

    public void placeTile(int row, int col, Tile tile) {
        for (int i = 0; i < Constants.TILES_SIZE; i++) {
            for (int j = 0; j < Constants.TILES_SIZE; j++) {
                var cellState = this.cellStates[i + row][j + col];
                cellState.setVisible(tile.getVisibility(i, j));
                cellState.setCover(tile.name());
                if (!cellState.isVisible()) {
                    currentBushCount[cellState.getBushNumber()]--;
                }
                this.cellStates[i + row][j + col] = cellState;
            }
        }
    }

    public int countBushesVisibleWithTile(int row, int col, Tile tile) {
        int cnt = 0;
        for (int i = 0; i < Constants.TILES_SIZE; i++) {
            for (int j = 0; j < Constants.TILES_SIZE; j++) {
                var cellState = this.cellStates[i + row][j + col];
                if (cellState.getBushNumber() > 0 && tile.getVisibility(i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public void rollBackTilePlacement(int row, int col) {
        for (int i = 0; i < Constants.TILES_SIZE; i++) {
            for (int j = 0; j < Constants.TILES_SIZE; j++) {
                var cellState = this.cellStates[i + row][j + col];
                if (!cellState.isVisible()) {
                    cellState.setVisible(true);
                    currentBushCount[cellState.getBushNumber()]++;
                }
                cellState.setCover(null);
                this.cellStates[i + row][j + col] = cellState;
            }
        }
    }

    public List<Integer> findUnAssignedCoordinates() {
        int n = this.cellStates.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cellStates[i][j].getCover() == null) {
                    return List.of(i, j);
                }
            }
        }
        return List.of();
    }

    public void print() {
        int n = this.cellStates.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(cellStates[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getCurrentBushCountByBushNumber(int num) {
        return currentBushCount[num];
    }

    public CellState[][] getCellStates() {
        return cellStates;
    }

    public CellState getCellState(int i, int j) {
        return this.cellStates[i][j];
    }

    public int[] getCountOfEachVisibleBush() {
        int[] bushes = new int[Constants.MAX_BUSH_NUM];

        int n = this.cellStates.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                var cellState = this.cellStates[i][j];
                bushes[cellState.getBushNumber()] += (cellState.isVisible()) ? 1 : 0;
            }
        }
        return bushes;
    }
}
