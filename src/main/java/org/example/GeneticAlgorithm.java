package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm implements SearchAlgorithm {

    private int row;
    private int col;
    private List<int[][]> parents = new ArrayList<>();
    private List<int[][]> selectedPopulation = new ArrayList<>();
    private List<int[][]> generatedPopulation = new ArrayList<>();

    public GeneticAlgorithm(int row, int col, int popSize) {
        this.row = row;
        this.col = col;
        generateFirstPopulation(row, popSize);
    }


    private void generateFirstPopulation(int boardSize, int count) {
        while (count-- > 0) {
            selectedPopulation.add(Main.randomizer(boardSize));
        }
    }

    private void selectParents(int size) {
        parents.clear();
        float[] cumulativeH = new float[selectedPopulation.size()];
        int[] bestToWorst = new int[selectedPopulation.size()];
        int index = 0;
        float upperLim = 0;

        for (int[][] individual : selectedPopulation) {
            float a = hFunc(individual);
            upperLim += 1/a;
            cumulativeH[index++] = upperLim;

        }

        Random r = new Random();
        for (int i = 0; i < selectedPopulation.size(); i++) {
            float randomNumb = r.nextFloat(0, upperLim);
            for(int j = 0; j < selectedPopulation.size(); j++){
                if(randomNumb <= cumulativeH[j]){
                    parents.add(selectedPopulation.get(j));
                    //System.out.println("a");
                    break;
                }
               // System.out.println("b");
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
        for(int i = 2* row / 3; i < row; i++){
            for(int j = 0; j < col; j++){
                p[i][j] = parent1[i][j];
            }
        }
        return p;
    }


    @Override
    public void search() {
        boolean found = false;
        int a = 0;
        while(true){

            if(++a % 100000 == 0){
                System.out.println(a + " H: " + hFunc(selectedPopulation.get(0)));
                for(int i = 0; i < row; i++){
                    for(int j = 0; j < col; j++){
                        System.out.print(selectedPopulation.get(0)[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println();
            }

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
