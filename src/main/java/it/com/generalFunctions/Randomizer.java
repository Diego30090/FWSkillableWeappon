package it.com.generalFunctions;

import java.util.Random;

public class Randomizer {
    public double Random(){
        double delta=0;
        delta = Math.random() * 100;
        //diminuzione dimensione
        delta = (float) delta;
    return delta;
    }
}
