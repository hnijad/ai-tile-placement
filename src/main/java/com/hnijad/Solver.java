package com.hnijad;

import com.hnijad.model.*;
import com.hnijad.util.Util;

import java.util.*;

public class Solver {
    private Input input;

    private Landscape foundSolution;
    private List<Variable> assignedValues;

    public Solver(Input input) {
        this.input = input;
        this.foundSolution = null;
    }

    private boolean backTrackWithConstraint(List<Variable> variables,
                                            Landscape currentState,
                                            int[] availableTiles,
                                            int[] constraint) {
        int ind = getVariableUsingMRV(variables);

        if (ind==-1 && currentState.isConstraintSatisfied(constraint)) {
            this.foundSolution = currentState;
            this.assignedValues = variables;
            return true;
        }

        if (ind == -1) {
            return false;
        }

        List<Variable> copiedVariables = Util.getCopiedVariables(variables);

        for (var domainName : variables.get(ind).getDomain()) {
            var domain = Tile.valueOf(domainName);
            if (availableTiles[domain.getIndex()] > 0) {

                currentState.placeTile(variables.get(ind).getRow(), variables.get(ind).getCol(), domain);
                availableTiles[domain.getIndex()]--;

                copiedVariables.get(ind).setValue(domain.name());
                copiedVariables.get(ind).setDomain(List.of(domain.name()));

                if (!currentState.isConstraintViolated(constraint)) {
                    Landscape newLandscape = new Landscape(currentState);
                    //runAC3(ind, copiedVariables, newLandscape, constraint);

                    if (backTrackWithConstraint(copiedVariables, currentState, availableTiles, constraint)) {
                        return true;
                    }
                }

                currentState.rollBackTilePlacement(variables.get(ind).getRow(), variables.get(ind).getCol());
                availableTiles[domain.getIndex()]++;

                // restore domain
                copiedVariables.get(ind).setValue(null);
                copiedVariables.get(ind).setDomain(variables.get(ind).getDomain());
//                for (int i = 0; i < variables.size(); i++) {
//                    copiedVariables.get(i).setDomain(variables.get(i).getDomain());
//                }
            }
        }

        return false;
    }

    private int getVariableUsingMRV(List<Variable> variables) {
        int index = -1;
        int sz = Integer.MAX_VALUE;
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).getValue() == null) {
                if (variables.get(i).getDomain().size() < sz) {
                    sz = variables.get(i).getDomain().size();
                    index = i;
                }
            }
        }
        return index;
    }

    public void runAC3(int index, List<Variable> variables, Landscape currentState, int[] constraint) {

        int n = variables.size();
        for (int i = 0; i < n; i++) {
            if (i == index) continue;
            enforceArcConsistency(variables.get(index), variables.get(i), currentState, constraint);
        }

//        Queue<Variable> queue = new ArrayDeque<>();
//        queue.add(variables.get(index));
//
//
//
//        int n = variables.size();
//
//        while (!queue.isEmpty()) {
//            var x = queue.poll();
//            for (int i = 0; i < n; i++) {
//                if (variables.get(i).equals(x)) continue;
//                boolean changed = enforceArcConsistency(x, variables.get(i), currentState, constraint);
//                if (changed) {
//                    if (variables.get(i).getDomain().isEmpty()) {
//                        return;
//                    }
//                    queue.add(variables.get(i));
//                }
//            }
//        }
        //System.out.println("After" + variables);
    }

    public boolean enforceArcConsistency(Variable x, Variable y, Landscape landscape, int[] constraint) {
        List<String> newDomain = new ArrayList<>();
        for (var yDomain : y.getDomain()) {
            //newLandscape.placeTile(y.getRow(), y.getCol(), yDomain);
            boolean anyXDomainExists = false;
            for (var xDomain: x.getDomain()) {
                Landscape newLandscape = new Landscape(landscape);
                newLandscape.placeTile(y.getRow(), y.getCol(), Tile.valueOf(yDomain));
                newLandscape.placeTile(x.getRow(), x.getCol(), Tile.valueOf(xDomain));
                if (!newLandscape.isConstraintViolated(constraint)) {
                    anyXDomainExists = true;
                }
                //landscape.rollBackTilePlacement(x.getRow(), y.getCol());
                if (anyXDomainExists) break;
            }
            if (anyXDomainExists) {
                newDomain.add(yDomain);
            }
            //landscape.rollBackTilePlacement(y.getRow(), y.getCol());
        }
        if (newDomain.size() == y.getDomain().size()) {
            return false;
        }
        y.setDomain(newDomain);
        return true;
    }

    public List<Variable> getSolution() {
        Landscape currentState = new Landscape(input.getLandscape());

        List<Variable> variables = VariableMapper.mapInputToVariable(input.getLandscape());


        for (Variable variable : variables) {
            System.out.println(variable);
            sortVariableDomainUsingLCV(variable, currentState);
            System.out.println(variable);
        }



//        if (backTrackWithConstraint(variables, currentState, input.getTiles(), input.getTarget())) {
//            return this.assignedValues;
//        }
        return null;
    }

    private void sortVariableDomainUsingLCV(Variable variable, Landscape landscape) {
        variable.getDomain().sort((d1, d2) -> {
            Tile t1 = Tile.valueOf(d1);
            Tile t2 = Tile.valueOf(d2);
            int cnt1 = (landscape.countBushesVisibleWithTile(variable.getRow(), variable.getCol(), t1));
            int cnt2 = (landscape.countBushesVisibleWithTile(variable.getRow(), variable.getCol(), t2));

            return Integer.compare(cnt2, cnt1);
        });
    }

    private boolean backTrack(Landscape currentState, int[] availableTiles, int[] constraint) {
        int totalTiles = availableTiles[0] + availableTiles[1] + availableTiles[2];

        if (totalTiles == 0 && currentState.isConstraintSatisfied(constraint)) {
            this.foundSolution = currentState;
            return true;
        }

        var coords = currentState.findUnAssignedCoordinates();

        if (coords.isEmpty()) return false; // constraint is not satisfied in the previous step

        int row = coords.get(0);
        int col = coords.get(1);

        for (Tile tile : Tile.values()) {
            if (availableTiles[tile.getIndex()] > 0) {
                currentState.placeTile(row, col, tile);
                availableTiles[tile.getIndex()]--;
                if (backTrack(currentState, availableTiles, constraint)) {
                    return true;
                }
                currentState.rollBackTilePlacement(row, col);
                availableTiles[tile.getIndex()]++;
            }
        }
        return false;
    }

    public Landscape getSolutionUsingSimpleBackTracking() {
        Landscape currentState = new Landscape(input.getLandscape());
        if (backTrack(currentState, input.getTiles(), input.getTarget())) {
            return this.foundSolution;
        }
        return null;
    }
}
