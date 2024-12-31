package org.example;

import java.util.LinkedList;

public class HillClimbingSearch implements SearchAlgorithm {

    public int[][] initialState;
    public int currentHeuristic;

    public HillClimbingSearch(int[][] initialState) {
        this.initialState = initialState;
    }

    public int[][] getNeighbors(int[][] state) {
        int[][] neighbors = new int[state.length][4];

        for (int i = 0, neighborIndex = 0; i < state.length; i++, neighborIndex++) {
            for (int j = 0; j < state[i].length; j++) {
                for(int color = 0; color < 4; color++) {
                    neighbors[neighborIndex] = new int[] {i, j, color, neighborHChange(state, i, j, color)};
                }
            }
        }
        return neighbors;
    }

    public int neighborHChange(int[][] state, int i, int j, int newColor) {
        int nodeH = 0;
        if (i < state.length - 1 && state[i][j] == state[i + 1][j])
            nodeH++;
        if(i > 0 && state[i][j] == state[i - 1][j])
            nodeH++;
        if(j < state.length - 1 && state[i][j] == state[i][j + 1])
            nodeH++;
        if (j > 0 && state[i][j] == state[i][j - 1])
            nodeH++;

        int newNodeH = 0;
        if (i < state.length - 1 && newColor == state[i + 1][j])
            newNodeH++;
        if(i > 0 && newColor == state[i - 1][j])
            newNodeH++;
        if(j < state.length - 1 && newColor == state[i][j + 1])
            newNodeH++;
        if (j > 0 && newColor == state[i][j - 1])
            newNodeH++;

        return newNodeH - nodeH;

    }

    public int currentBoxH(int[][] state, int i, int j) {
        int h = 0;

        if (i < state.length - 1 && state[i][j] == state[i + 1][j])
            h++;
        if(i > 0 && state[i][j] == state[i - 1][j])
            h++;
        if(j < state.length - 1 && state[i][j] == state[i][j + 1])
            h++;
        if (j > 0 && state[i][j] == state[i][j - 1])
            h++;

        return h;
    }

    @Override
    public void search() {
        int[][] currentState = initialState;
        int count = 0;
        while(true) {

            if(++count % 100000 == 0)
                System.out.println(count);

            if(hFunc(currentState) == 0){
                System.out.println("I've found a solution");
                break;
            }

            int[] bestNeighbor = new int[] {0 ,0 ,0 ,0};
            int bestNeighborH = 4;
            int[][] neighbors = getNeighbors(currentState);

            for(int i = 0; i < neighbors.length; i++) {
                if(neighbors[i][3] < bestNeighborH) {
                    bestNeighbor = neighbors[i];
                }
            }
            currentState[bestNeighbor[0]][bestNeighbor[1]] = bestNeighbor[2];


        }
    }

    @Override
    public int hFunc(int[][] state) {
        int h = 0;
        for(int i = 0; i < state.length; i++) {
            for(int j = 0; j < state[i].length; j++) {
                h+= currentBoxH(state, i, j);
            }
        }
        return h;
    }
}
