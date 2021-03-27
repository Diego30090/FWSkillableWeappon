package it.com;

import it.com.config.Config;
import it.com.generalFunctions.LevelExperience;
import it.com.generalFunctions.Randomizer;
import it.com.generalFunctions.WeaponSelection;
import it.com.weapons.Weapons;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
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

        // System.out.println("Start evento exping");
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        Weapons weapons = new Weapons();
        if (entity instanceof Monster) {
            if (player != null) {
                ItemStack item = player.getItemInHand();
                //System.out.println("Il nome dell'entità è " +player.getName());
                if (item.getItemMeta().hasLore()) {
                    ArrayList<String> lore = new ArrayList<String>(item.getItemMeta().getLore());
                    ItemMeta meta = item.getItemMeta();

                    // System.out.println("L'item che ha in mano ha le seguenti statistiche " + String.valueOf(item.getItemMeta().getLore()));
                    LevelExperience exp = new LevelExperience();
                    double ItemExp = weapons.returnXP(item);
                    double ItemExpMax = weapons.returnMaxXP(item);
                    double level = weapons.returnLevel(item);
                    double expToAdd = w.expToAdd(entity);

                    //System.out.println("ItemExp: "+ ItemExp + " ItemExpMax: " + ItemExpMax + " Level: " + level + " expToAdd: "+ expToAdd);
                    double[] itemStats = exp.newItemAttributes(item, expToAdd);
                    level = itemStats[0];
                    ItemExp = itemStats[1];
                    ItemExpMax = itemStats[2];

                    AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", level, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
                    meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
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

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        Weapons wea = new Weapons();
        if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow)e.getDamager();
            if (arrow.getShooter() instanceof Player) {
               // e.setDamage();
            }
        }
    }
}





