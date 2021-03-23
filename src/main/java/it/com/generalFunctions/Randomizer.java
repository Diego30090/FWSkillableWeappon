package it.com.generalFunctions;



public class Randomizer {

    //funzione per i valori random
    public double Random(){
        double delta;
        delta = Math.random() * 100;
        //diminuzione dimensione
        delta = (float) delta;
    return delta;
    }
}
