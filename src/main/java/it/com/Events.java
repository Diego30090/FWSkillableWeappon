package it.com;



import it.com.generalFunctions.Randomizer;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;


public class Events implements Listener {
    Randomizer random =new Randomizer();
    Material[] Weapons= new Material[]{Material.NETHERITE_AXE, Material.NETHERITE_SWORD, Material.CROSSBOW,Material.BOW};


    @EventHandler
    public void onMobDeath(EntityDeathEvent event){
        double MobPercentage= random.Random();
        double WeaponPercentage=random.Random();
        int probability=90; //in seguito da mettere da config
        LivingEntity e= event.getEntity();

        double WeaponPercentage2=100/Weapons.length;
        double EffectivePercentage = random.Random();

        int weaponSelect=(int)(EffectivePercentage/WeaponPercentage2);

        if(e instanceof Monster) {
            if (MobPercentage < probability)
                e.getLocation().getWorld().dropItem(e.getLocation(), new ItemStack(Weapons[weaponSelect]));
        }
    }


}




