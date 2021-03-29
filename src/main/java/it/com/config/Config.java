package it.com.config;

import it.com.FWSkillableWeapon;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public static FileConfiguration config;

    public static void updateConfig() {
        config = FWSkillableWeapon.plugin.getConfig();
    }

    public static double getDouble(String string) {
        updateConfig();
        return config.getDouble(string);
    }


}
