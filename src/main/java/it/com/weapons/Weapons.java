package it.com.weapons;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;


public class Weapons implements Listener {



    public static double returnXP(ItemStack is) {
        double xp = Double.valueOf(is.getItemMeta().getLore().get(1).split("/")[0].substring(7).replace(" §7", ""));
        return xp;
    }
    public static double returnLevel(ItemStack is) {
        double level = Double.valueOf(is.getItemMeta().getLore().get(2).substring(10));
        return level;
    }
    public static double returnMaxXP(ItemStack is) {
        double maxXp = Double.valueOf(is.getItemMeta().getLore().get(1).split("/")[1].substring(3));
        return maxXp;
    }



}

