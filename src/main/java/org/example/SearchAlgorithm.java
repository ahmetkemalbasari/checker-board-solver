package org.example;

import java.util.Arrays;

public interface SearchAlgorithm {

    void search();

    int hFunc(int[][] state);

    static int[][] deepCopy(int[][] pieces) {
        return Arrays.stream(pieces).map(int[]::clone).toArray(int[][]::new);
    }

}
