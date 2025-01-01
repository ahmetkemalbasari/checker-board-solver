package org.example;

import java.util.Random;

public class Main {

    public static int[][] randomizer(int n) {
        int[][] state = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                state[i][j] = rand.nextInt(4);
            }
        }
        return state;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        int[][] initialState = randomizer(10);
        /*initialState = new int[][]{
                {0, 1, 2, 3, 0, 1, 2, 3, 0, 1},
                {1, 2, 3, 0, 1, 2, 3, 0, 0, 0},
                {2, 3, 0, 1, 2, 3, 0, 2, 2, 3},
                {3, 2, 1, 2, 3, 0, 1, 2, 3, 0},
                {0, 2, 2, 3, 0, 1, 2, 3, 0, 1},
                {1, 2, 2, 0, 1, 2, 3, 3, 1, 0},
                {2, 2, 2, 0, 0, 3, 3, 1, 2, 3},
                {2, 2, 2, 0, 3, 3, 1, 2, 3, 0},
                {2, 1, 2, 3, 3, 1, 1, 1, 0, 1},
                {2, 2, 2, 0, 3, 2, 1, 0, 1, 2},
        };*/

                /*initialState = new int[][]{
                {0, 1, 2, 3, 0, 1, 2, 3, 0, 1},
                {1, 2, 3, 0, 1, 2, 3, 0, 0, 0},
                {2, 3, 0, 1, 2, 3, 0, 1, 2, 3},
                {3, 0, 1, 2, 3, 0, 1, 2, 3, 0},
                {0, 1, 2, 3, 0, 1, 2, 3, 0, 1},
                {1, 2, 3, 0, 1, 2, 3, 0, 1, 0},
                {2, 3, 0, 1, 2, 3, 0, 1, 2, 3},
                {3, 0, 1, 2, 3, 0, 1, 2, 3, 0},
                {0, 1, 2, 3, 0, 1, 2, 3, 0, 1},
                {1, 2, 3, 0, 1, 2, 3, 0, 1, 2},
        };*/
        //HillClimbingSearch hillClimbingSearch = new HillClimbingSearch(initialState);
        //hillClimbingSearch.search();

        GeneticAlgorithm gA = new GeneticAlgorithm(10, 10, 10);
        gA.search();
    }
}