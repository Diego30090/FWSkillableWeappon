package it.com.weapons;

import it.com.config.Config;
import it.com.generalFunctions.LevelExperience;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CustomItems implements Listener {



    public void ItemStarter(Material weapon, LivingEntity e){
        ItemStack item= new ItemStack(weapon,1);
        ItemMeta meta = item.getItemMeta();
        LevelExperience expManager=new LevelExperience();
        ArrayList<String> lore = new ArrayList<String>();
        int level=1;
        double maxExperience= expManager.ExperiencetoLevel(Config.getDouble("leveling.exp_coefficient"),level);
        meta.setUnbreakable(true);
        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        lore.add("Expable Item");
        lore.add("§7XP §f" + (double) 0 + " §7/ §f" +  maxExperience);
        lore.add("§7Level §f" + (double) level);
        meta.setLore(lore);
        item.setItemMeta(meta);
        e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(item));

    }






}
