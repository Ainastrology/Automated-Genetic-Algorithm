package com.algoclass;

public class GeneAlgo {

    public static void main(String[] args) {
        int numberofgeneration = 3; // Number of generations
        int popsize = 2;     // Population size
        System.out.println("\tAutomation of Realizing DataSet with lowest fitness error in various Generations for Line Slope Equation\n\n");
        Gene gene = new Gene();
        gene.input();
        for (int generation = 1; generation <= numberofgeneration; generation++) {
//        	 gene.input();
            double[] mValues = gene.getMValues();
            double[] cValues = gene.getCValues();
            double minFitness = Double.MAX_VALUE;
            int bestIndex = 0;

            // Calculate fitness for each individual in the population
            for (int i = 0; i < popsize; i++) {
                double fitness = gene.calculateFitness(mValues[i], cValues[i]);
                if (fitness < minFitness) {
                    minFitness = fitness;
                    bestIndex = i;
                }
            }

            // Update the best fitness in the Gene class
            gene.setMinimumFitness(minFitness);

            // Print the best fit for this generation
            gene.printBestFit(generation, mValues[bestIndex], cValues[bestIndex]);

            // Perform mutation
            gene.mutation(mValues, cValues);
        }
        
    }
}
