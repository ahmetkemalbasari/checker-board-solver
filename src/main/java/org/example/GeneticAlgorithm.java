package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm implements SearchAlgorithm {

    private List<int[][]> parents;
    private List<int[][]> selectedPopulation;
    private List<int[][]> generatedPopulation;

    private void generateFirstPopulation(int boardSize, int count) {
        while (count-- > 0) {
            selectedPopulation.add(Main.randomizer(boardSize));
        }
    }

    private void selectParents(int size) {
        parents = new ArrayList<int[][]>();
        int[] cumulativeH = new int[selectedPopulation.size()];
        int[] bestToWorst = new int[selectedPopulation.size()];
        int index = 0;
        int totalH = 0;

        for (int[][] individual : selectedPopulation) {
            cumulativeH[index++] = hFunc(individual);
            totalH += cumulativeH[index-1];
        }

        Random r = new Random();
        for (int i = 0; i < selectedPopulation.size(); i++) {
            int randomNumb = r.nextInt(0, totalH);
            for(int j = 0; j < selectedPopulation.size(); j++){
                if(randomNumb <= cumulativeH[j]){
                    parents.add(selectedPopulation.get(i));
                    break;
                }
            }
        }
    }

    private int[][] generateIndividual(int[][] parent) {
        return parent;
    }


    @Override
    public void search() {

    }

    @Override
    public int hFunc(int[][] state) {
        int totalH = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                totalH += currentBoxH(state, i, j);
            }
        }
        return totalH;
    }

    public int currentBoxH(int[][] state, int i, int j) {
        int h = 0;

        if (i < state.length - 1 && state[i][j] == state[i + 1][j])
            h++;
        if (i > 0 && state[i][j] == state[i - 1][j])
            h++;
        if (j < state.length - 1 && state[i][j] == state[i][j + 1])
            h++;
        if (j > 0 && state[i][j] == state[i][j - 1])
            h++;

        return h;
    }
}
