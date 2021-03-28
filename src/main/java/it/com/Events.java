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
        double MobPercentage = random.Random();
        LivingEntity e = event.getEntity();

        double[] percentage = w.getPercentage(value, "drops_chances.");

        double total = w.getTotalPercentage(percentage);

        int weaponSelect = w.weaponSelection(value, percentage, total);
        if (e instanceof Monster) {
            if (MobPercentage < total)
                c.ItemStarter(Weapons[weaponSelect], e);
        }
    }

    @EventHandler
    public void expWeaponOnDeath(EntityDeathEvent event) {

        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        Weapons weapons = new Weapons();

        if (entity instanceof Monster) {
            if (player != null ) {
                ItemStack item = player.getItemInHand();
                System.out.println("Item in mano del player è riconosciuto");



                if (item.getItemMeta().hasLore()) {

                    if(item.getItemMeta().getLore().contains("Expable Item")) {


                        ArrayList<String> lore = new ArrayList<String>(item.getItemMeta().getLore());
                        ItemMeta meta = item.getItemMeta();


                        LevelExperience exp = new LevelExperience();
                        double ItemExp = weapons.returnXP(item);
                        double ItemExpMax = weapons.returnMaxXP(item);
                        double level = weapons.returnLevel(item);
                        double expToAdd = w.expToAdd(entity);
                        double[] itemStats = exp.newItemAttributes(item, expToAdd);
                        level = itemStats[0];
                        ItemExp = itemStats[1];
                        ItemExpMax = itemStats[2];

                        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", level, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
                        if(item.getType().toString().contains("BOW") || item.getType().toString().contains("CROSSBOW")){
                            meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                            System.out.println("Il danno dall'arco/balestra è rimosso");
                        }
                        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        lore.clear();
                        lore.add("Expable Item");
                        lore.add("§7XP §f" + ItemExp + " §7/ §f" + ItemExpMax);
                        lore.add("§7Level §f" + (double) level);
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        meta.setLore(lore);
                        item.setItemMeta(meta);

                    }
                }
            }
        }

    }



        @EventHandler
        public void onArrowHit(EntityDamageByEntityEvent e) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                Arrow a = (Arrow) e.getDamager();
                Weapons weapons = new Weapons();
                Player player = (Player) a.getShooter();
                ItemStack item = player.getItemInHand();
                //i brackets interni nell'ultimo if ci sono perchè si
                    if (item.getItemMeta().hasLore()) {
                        if(item.getItemMeta().getLore().contains("Expable Item")){
                            e.setDamage(weapons.returnLevel(item));
                        }

                    }

            }
        }

    @EventHandler
    public void zombieDamage(EntityDamageByEntityEvent event){
        Entity entity= event.getDamager();
        if (entity instanceof Player) {
            Weapons weapons = new Weapons();
            Player player = (Player) entity;
            ItemStack item = player.getItemInHand();
            ((Player) event.getDamager()).sendMessage("Player recognised!");
            if (item.getItemMeta().hasLore()) {
                if(item.getItemMeta().getLore().contains("Item damage Set!")){
                    event.setDamage(weapons.returnLevel(item));
                }

            }
        }
    }

}





