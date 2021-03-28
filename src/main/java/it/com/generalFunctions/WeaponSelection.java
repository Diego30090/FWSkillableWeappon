package it.com.generalFunctions;


import it.com.FWSkillableWeapon;
import it.com.config.Config;
import org.bukkit.entity.Entity;

public class WeaponSelection {
    public double[] getPercentage(String[] value, String configValue) {

        double[] percentage = new double[value.length];
        for (int i = 0; i < percentage.length; i++) {
            percentage[i] = Config.getDouble(configValue+value[i]);
        }
        return percentage;
    }

    public double getTotalPercentage(double[] percentage) {
        double total = 0;
        for (int i = 0; i < percentage.length; i++) {
            if (i == 0) total = 0;
            total += percentage[i];
        }
        return total;
    }


    public int weaponSelection(String[] value,double[] percentage, double total) {
        Randomizer random = new Randomizer();

        int weaponSelect = 0;
        double weaponPercentage = 0;

        double effectiveValue = random.Random(total);
        for(
                int i = 0;
                i<value.length;i++)
        {
            if (i == 0) weaponPercentage = 0;
            if (effectiveValue <= weaponPercentage + percentage[i]) {
                weaponSelect = i;
                break;
            }
            weaponPercentage += percentage[i];
        }
        return weaponSelect;
    }

    public double expToAdd (Entity e){
        String standardMob="leveling.mob_experiences.";
        double addingExperience=0;
        //i brackets nell'ifelse sono stati aggiunti perchè si
        if(FWSkillableWeapon.plugin.getConfig().contains(standardMob+"custom." + e.getName().toUpperCase())){
            addingExperience = Config.getDouble(standardMob+"custom." + e.getName().toUpperCase());
        }
        else {
            addingExperience = Config.getDouble(standardMob + "default");
        }


        return addingExperience;
    }














}
