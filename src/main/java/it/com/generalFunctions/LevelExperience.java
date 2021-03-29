package it.com.generalFunctions;

import it.com.config.Config;
import it.com.weapons.Weapons;
import org.bukkit.inventory.ItemStack;

public class LevelExperience {


    Weapons weapons = new Weapons();



    public double ExperiencetoLevel(double coefficient,double level){

        double endingValue=0;
        endingValue=level*(coefficient*level);
        return endingValue;
    }



    public double[] newItemAttributes(ItemStack item, double expToAdd){
        double ItemExp = weapons.returnXP(item);
        double ItemExpMax = weapons.returnMaxXP(item);
        double level = weapons.returnLevel(item);

        ItemExp += expToAdd;
        do {
            if (ItemExp  > ItemExpMax) {
                level += 1;
                ItemExp = ItemExp -ItemExpMax;
                ItemExpMax = ExperiencetoLevel(Config.getDouble("leveling.exp_coefficient"), level);
            }
        }while(ItemExp >ItemExpMax);

        double[] ItemReturn= {level,ItemExp,ItemExpMax};

        return ItemReturn;


    }



}
