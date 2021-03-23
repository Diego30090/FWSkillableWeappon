package it.com.generalFunctions;

import java.util.Random;

public class Randomizer {
    public double Random(){
        double[] list = new double[1000];
        double mean = 1.0, std = 0.5;
        double delta=0;
        Random rng = new Random();

        for(int i = 0;i<list.length;i++) {
            list[i] = mean + std * rng.nextGaussian();
        }
        for(int i = 0;i<list.length;i++) {
            delta+=list[i];
        }
    return delta;
    }
}
