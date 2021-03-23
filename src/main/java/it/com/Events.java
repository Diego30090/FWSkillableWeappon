package it.com;

import it.com.generalFunctions.Randomizer;
import org.bukkit.*;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;



public class Events implements Listener {
    Randomizer random =new Randomizer();
    Material[] Weapons= new Material[]{Material.NETHERITE_AXE, Material.NETHERITE_SWORD, Material.CROSSBOW,Material.BOW};
    CustomItems c= new CustomItems();


    @EventHandler
    public void ItemDrop(EntityDeathEvent event){
        double MobPercentage= random.Random();
        int probability=90; //in seguito da mettere da config
        LivingEntity e= event.getEntity();
        double WeaponPercentage=(int)(100/Weapons.length);
        double EffectivePercentage = random.Random();

        int weaponSelect=(int)(EffectivePercentage/WeaponPercentage);

        if(e instanceof Monster) {
            if (MobPercentage < probability)
                c.itemDescription(Weapons[weaponSelect],e);
        }
    }

    /*
    @EventHandler
    public void expWeapon(EntityDeathEvent event){
        //evento che permette di expare l'arma quando il mob muore
    }

    @EventHandler
    public void onHit(PlayerInteractEvent event){
        Player player =event.getPlayer();

        //evento onHit per il danno


    }
    */
}




