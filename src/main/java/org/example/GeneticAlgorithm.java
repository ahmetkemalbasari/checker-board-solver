package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm implements SearchAlgorithm {

    private int row;
    private int col;
    private List<int[][]> parents;
    private List<int[][]> selectedPopulation;
    private List<int[][]> generatedPopulation;


    private void generateFirstPopulation(int boardSize, int count) {
        while (count-- > 0) {
            selectedPopulation.add(Main.randomizer(boardSize));
        }
    }

    private void selectParents(int size) {
        parents.clear();
        int[] cumulativeH = new int[selectedPopulation.size()];
        int[] bestToWorst = new int[selectedPopulation.size()];
        int index = 0;
        int totalH = 0;

        for (int[][] individual : selectedPopulation) {
            totalH += hFunc(individual);
            cumulativeH[index++] = totalH;

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

    private void mutateIndividuals(){
        Random r = new Random();

        for(int[][] individual : generatedPopulation){
            int i = r.nextInt( row);
            int j = r.nextInt( col);
            individual[i][j] = r.nextInt(4);
        }
    }

    private int[][] crossover(int[][] parent0, int[][] parent1) {
        int[][] p = SearchAlgorithm.deepCopy(parent0);
        for(int i = row/2; i < row; i++){
            for(int j = 0; j < col; j++){
                p[i][j] = parent1[i][j];
            }
        }
        return p;
    }


    @Override
    public void search() {
        boolean found = false;
        while(true){
            for (int i = 0; i < selectedPopulation.size(); i++) {
                if(hFunc(selectedPopulation.get(i)) == 0)
                    found = true;
            }

            if(found){
                System.out.println("I've found a solution");
                break;
            }


            selectParents(10);
            generatedPopulation.clear();

            for(int i = 0; i < parents.size()/2; i+=2){
                generatedPopulation.add(crossover(parents.get(i), parents.get(i + 1)));
                generatedPopulation.add(crossover(parents.get(i+1), parents.get(i)));
            }
            mutateIndividuals();

            selectedPopulation.clear();
            selectedPopulation = generatedPopulation;

        }

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
