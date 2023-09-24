package com.algoclass;
import java.util.Scanner;

public class Gene {

    private int popsize = 2 ;
    private double x1 = 1, y1 = 5, x2 = 3, y2 = 9 ;
    private double[] mValues;
    private double[] cValues;
    private double minimumFitness = Double.MAX_VALUE;
    private Scanner scn = new Scanner(System.in);

    public Gene() {
        mValues = new double[popsize];
        cValues = new double[popsize];
    }

    public void input() {
        for (int i = 0; i < popsize; i++) {
            System.out.println("Enter random values for 'm' & 'c' for DataSet " + (i + 1) + ":");
            System.out.print("m = ");
            mValues[i] = scn.nextDouble();
            System.out.print("c = ");
            cValues[i] = scn.nextDouble();
        }
    }
    public double[] getMValues() {
        return mValues;
    }

    public double[] getCValues() {
        return cValues;
    }

    public double calculateFitness(double m, double c) {
        double constraint1 = (m * x1) + c;
        double constraint2 = (m * x2) + c;
        double errorfunction1 = y1 - constraint1;
        double errorfunction2 = y2 - constraint2;
        double squarederrorfunction1 = Math.pow(errorfunction1, 2);
        double squarederrorfunction2 = Math.pow(errorfunction2, 2);
        double fitness = squarederrorfunction1 + squarederrorfunction2;
        return fitness ;
    }
    

    public double getMinimumFitness() {
        return minimumFitness;
    }

    public void setMinimumFitness(double minimumFitness) {
        this.minimumFitness = minimumFitness;
    }

    public void printBestFit(int generation, double m, double c) {
        System.out.println("\n\nPopulation or Generation with less fitness error (Generation " + generation + "):");
        System.out.println("m = " + m + ", c = " + c);
        System.out.println("Fitness error = " + minimumFitness + "\n\n");
    }

    public void mutation(double[] mValues, double[] cValues) {
    	System.out.println("\nProcess of Mutation for both DataSets\n");
        for (int i = 0; i < popsize; i++) {
        	
        	 System.out.println("Value of m for mutation : "+ mValues[i]);
        	 System.out.println("Value of c for mutation : "+ cValues[i]);
        	 
            String mbin = Integer.toBinaryString((int) mValues[i]);
            String cbin = Integer.toBinaryString((int) cValues[i]);
            
            System.out.println("m in binary : "+ mbin );
            System.out.println("c in binary : "+ cbin ); 
            
            // Ensure the binary strings have the same length
            int maxLength = Math.max(mbin.length(), cbin.length());
            mbin = String.format("%" + maxLength + "s", mbin).replace(' ', '0');
            cbin = String.format("%" + maxLength + "s", cbin).replace(' ', '0');
            
            System.out.println("m after bits completion : "+mbin );
            System.out.println("c after bits completion : "+cbin );
            
            // Perform mutation on a random bit
            int mutationIndex = (int) (Math.random() * maxLength);
            char mChar = mbin.charAt(mutationIndex);
            char cChar = cbin.charAt(mutationIndex);

            // Flip the bit for mutation
            mChar = (mChar == '0') ? '1' : '0';
            cChar = (cChar == '0') ? '1' : '0';
           
            // Update the values with the mutated bits
            mbin = mbin.substring(0, mutationIndex) + mChar + mbin.substring(mutationIndex + 1);
            cbin = cbin.substring(0, mutationIndex) + cChar + cbin.substring(mutationIndex + 1);
            System.out.println("After mutation m : "+mbin );
            System.out.println("After mutation c : "+cbin );
            // Convert binary strings back to double
            mValues[i] = Integer.parseInt(mbin, 2);
            cValues[i] = Integer.parseInt(cbin, 2);
            System.out.println("m in decimal : "+mValues[i] );
            System.out.println("c in decimal : "+cValues[i] );
            calculateFitness(mValues[i] , cValues[i]);
            
        }
    }
}

