package it.com.generalFunctions;



public class Randomizer {

    public double Random(){
        double delta;
        delta = Math.random() * 100;
        delta = (float) delta;
    return delta;
    }

    public double Random(double maxValue){
        double delta;
        delta = Math.random() * maxValue;
        delta = (float) delta;
        return delta;
    }
}
