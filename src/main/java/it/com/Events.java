package it.com;

import it.com.generalFunctions.LevelExperience;
import it.com.generalFunctions.Randomizer;
import it.com.generalFunctions.WeaponSelection;
import it.com.weapons.CustomItems;
import it.com.weapons.Weapons;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;


public class Events implements Listener {
    Randomizer random = new Randomizer();
    Material[] Weapons = new Material[]{Material.NETHERITE_SWORD, Material.NETHERITE_AXE, Material.BOW, Material.CROSSBOW};
    CustomItems c = new CustomItems();
    String[] value = {"sword", "axe", "bow", "crossbow"};
    WeaponSelection w = new WeaponSelection();

    @EventHandler
    public void ItemDrop(EntityDeathEvent event) {
        //start declaring stuff
        double MobPercentage = random.Random();
        LivingEntity e = event.getEntity();
        double[] percentage = w.getPercentage(value, "drops_chances.");
        double total = w.getTotalPercentage(percentage);
        int weaponSelect = w.weaponSelection(value, percentage, total);
        //end declaring stuff
        
        if (e instanceof Monster) {
            if (MobPercentage < total)
                c.ItemStarter(Weapons[weaponSelect], e);
        }
    }

    @EventHandler
    public void expWeaponOnDeath(EntityDeathEvent event) {
        //start declaring stuff
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        //start check
        if (entity instanceof Monster) {
            if (player != null ) {
                ItemStack item = player.getItemInHand();
                if (item.getItemMeta().hasLore()) {
                    if(item.getItemMeta().getLore().contains("Expable Item")) {
                      //end check
                        ItemMeta meta = item.getItemMeta();
                        ArrayList<String> lore = new ArrayList<String>(meta.getLore());

                        LevelExperience exp = new LevelExperience();
                        double expToAdd = w.expToAdd(entity);
                        double[] itemStats = exp.newItemAttributes(item, expToAdd);
                        double ItemExp = itemStats[1];
                        double ItemExpMax = itemStats[2];
                        double level = itemStats[0];
                        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", level, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                        //end declaring stuff
                        //start using stuff
                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
                        if(item.getType().toString().contains("BOW") || item.getType().toString().contains("CROSSBOW")){
                            meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                        }
                        //lore to set
                        lore.clear();
                        lore.add("Expable Item");
                        lore.add("§7XP §f" + ItemExp + " §7/ §f" + ItemExpMax);
                        lore.add("§7Level §f" + (double) level);
                        //effective lore and meta setting
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        //end declaring stuff
                    }
                }
            }
        }

    }



        @EventHandler
        public void onArrowHit(EntityDamageByEntityEvent e) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                //start declaring stuff
                Arrow a = (Arrow) e.getDamager();
                Weapons weapons = new Weapons();
                Player player = (Player) a.getShooter();
                ItemStack item = player.getItemInHand();
                //end declaring stuff
                    if (item.getItemMeta().hasLore()) {
                        if(item.getItemMeta().getLore().contains("Expable Item"))
                            e.setDamage(weapons.returnLevel(item));
                    }
            }
        }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event){
       //start declaring stuff
        Entity entity= event.getDamager();
        if (entity instanceof Player) {
            Weapons weapons = new Weapons();
            Player player = (Player) entity;
            ItemStack item = player.getItemInHand();
            //end declaring stuff
            if (item.getItemMeta().hasLore()) {
                if(item.getItemMeta().getLore().contains("Expable Item"))
                    event.setDamage(weapons.returnLevel(item));
            }
        }
    }

}





