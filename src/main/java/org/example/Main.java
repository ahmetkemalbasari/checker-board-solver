package org.example;

import java.util.Random;

public class Main {

    public static int[][] randomizer(int n){
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
        int[][] initialState = randomizer(10);
        HillClimbingSearch hillClimbingSearch = new HillClimbingSearch(initialState);
        hillClimbingSearch.search();
    }
}