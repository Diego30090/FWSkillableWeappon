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

    public static boolean getBool(String string) {
        updateConfig();
        return config.getBoolean(string);
    }

    public static String getString(String string) {
        updateConfig();
        return config.getString(string);
    }

    public void setDouble(String string, Object newValue) {
        updateConfig();
        config.set(string,newValue);
    }

    public void setBool(String string, Object newValue) {
        updateConfig();
         config.set(string,newValue);
    }

    public void setString(String string, Object newValue) {
        updateConfig();
         config.set(string,newValue);
    }
}
