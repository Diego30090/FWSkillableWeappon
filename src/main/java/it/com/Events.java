package it.com;



import org.bukkit.Material;
import org.bukkit.entity.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;


public class Events implements Listener {

    @EventHandler
    public void onMobDeath(EntityDeathEvent event){
    event.getDrops().clear();
        LivingEntity e= event.getEntity();

        if(e instanceof Creeper)
            e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(Material.NETHERITE_AXE));
        }
    }




