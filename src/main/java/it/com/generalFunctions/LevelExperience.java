package it.com.generalFunctions;

public class LevelExperience {

    public double ExperiencetoLevel(double coefficient,double level){

        double endingValue=0;
        endingValue=level*(coefficient*level);
        return endingValue;
    }




}
