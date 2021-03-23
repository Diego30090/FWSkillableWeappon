package it.com;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CustomItems implements Listener {



    public void itemDescription(Material weapon, LivingEntity e){
        ItemStack item= new ItemStack(weapon,1);
        ItemMeta meta = item.getItemMeta();

        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "Level 1");
        lore.add(ChatColor.WHITE + "exp: 0 / 500" );
        lore.add(ChatColor.WHITE + "Damage: 1" );
        meta.setLore(lore);

        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attackDamage", +1, AttributeModifier.Operation.ADD_NUMBER));

        item.setItemMeta(meta);


        e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(item));
    }




}
