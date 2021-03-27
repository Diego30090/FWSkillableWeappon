package it.com.generalFunctions;


import it.com.config.Config;

public class WeaponSelection {
    Config config = new Config();
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
















}
