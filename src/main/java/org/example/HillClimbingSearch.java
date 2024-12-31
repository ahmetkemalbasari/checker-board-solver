package org.example;

import java.util.LinkedList;

public class HillClimbingSearch implements SearchAlgorithm {

    public int[][] initialState;
    public int currentHeuristic;

    public HillClimbingSearch(int[][] initialState) {
        this.initialState = initialState;
    }

    public int[][] getNeighbors(int[][] state) {
        int[][] neighbors = new int[state.length * state[0].length * 4][4];

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                for(int color = 0; color < 4; color++) {
                    neighbors[(i* state.length + j)*4 + color] = new int[] {i, j, color, neighborHChange(state, i, j, color)};
                }
            }
        }
        return neighbors;
    }

    public int neighborHChange(int[][] state, int i, int j, int newColor) {
        int[][] neighbor = SearchAlgorithm.deepCopy(state);
        neighbor[i][j] = newColor;
        int nodeH = currentBoxH(state, i, j);
        int neighH = currentBoxH(neighbor, i, j);



        return neighH - nodeH;

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
                //System.out.println("index:" + i + ", i: " + neighbors[i][0] + ", j: " + neighbors[i][1] + ", c: " + neighbors[i][2] + ", h: " + neighbors[i][3]);
                if(neighbors[i][3] < bestNeighborH) {
                    bestNeighbor = neighbors[i];
                    bestNeighborH = neighbors[i][3];
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
