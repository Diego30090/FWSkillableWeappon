package it.com;

import com.destroystokyo.paper.ClientOption;
import com.destroystokyo.paper.Title;
import com.destroystokyo.paper.block.TargetBlockInfo;
import com.destroystokyo.paper.entity.TargetEntityInfo;
import com.destroystokyo.paper.profile.PlayerProfile;
import it.com.config.Config;
import it.com.generalFunctions.LevelExperience;
import it.com.generalFunctions.Randomizer;
import it.com.weapons.Weapons;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.*;

import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.InetSocketAddress;
import java.util.*;


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

            if (e instanceof Monster) {
                if (MobPercentage < probability)
                    c.ItemStarter(Weapons[weaponSelect], e);
            }

    }

    @EventHandler
    public void expWeaponOnDeath (EntityDeathEvent event){
        System.out.println("Start evento exping");
        LivingEntity entity = event.getEntity();
        Player player = entity.getKiller();
        Weapons weapons =new Weapons();
        if (player != null) {
            ItemStack item = player.getItemInHand();
            System.out.println("Il nome dell'entità è " +player.getName());
            if(item.getItemMeta().hasLore()) {
                ArrayList<String> lore = new ArrayList<String>(item.getItemMeta().getLore());
                ItemMeta meta = item.getItemMeta();

               // System.out.println("L'item che ha in mano ha le seguenti statistiche " + String.valueOf(item.getItemMeta().getLore()));
                LevelExperience exp= new LevelExperience();
                double ItemExp= weapons.returnXP(item);
                double ItemExpMax= weapons.returnMaxXP(item);
                double level=weapons.returnLevel(item);
                double expToAdd= Config.getDouble("leveling.mob_experiences.default");
                //System.out.println("ItemExp: "+ ItemExp + " ItemExpMax: " + ItemExpMax + " Level: " + level + " expToAdd: "+ expToAdd);
                AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",level, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

                    if(ItemExp + expToAdd >ItemExpMax){


                        level +=1;
                        ItemExp=ItemExp + expToAdd-ItemExpMax;
                        ItemExpMax= exp.ExperiencetoLevel(5, level);
                        meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
                        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
                        lore.clear();
                        lore.add("Expable Item");
                        lore.add("§7XP §f" + ItemExp + " §7/ §f" +  ItemExpMax);
                        lore.add("§7Level §f" + (double) level);
                        meta.setLore(lore);
                        item.setItemMeta(meta);

                        meta.setLore(lore);
                        item.setItemMeta(meta);
                    }
                    else{

                        ItemExp+=expToAdd;
                        lore.clear();
                        lore.add("Expable Item");
                        lore.add("§7XP §f" + ItemExp + " §7/ §f" +  ItemExpMax);
                        lore.add("§7Level §f" + (double) level);

                        meta.setLore(lore);
                        item.setItemMeta(meta);
                    }



            }
        }

    }
    /*
    @EventHandler
    public void expWeapon(EntityDeathEvent event){
        //evento che permette di expare l'arma quando il mob muore
        Player player = (Player) event.getEntity();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        Weapons weapons =new Weapons();


        System.out.println("Item check Lore");
        //base su cui lavorare la parte dell'exp dell'item
        if(meta.hasLore()){


            LevelExperience exp= new LevelExperience();
            double ItemExp= weapons.returnXP(item);
            double ItemExpMax= weapons.returnMaxXP(item);
            double level=weapons.returnLevel(item);
            double expToAdd= Config.getDouble("leveling.mob_experiences.default");

            System.out.println("ItemExp: "+ ItemExp + "ItemExpMax: " + ItemExpMax + "Level: " + level + "expToAdd: "+ expToAdd);
            if(ItemExp + expToAdd >ItemExpMax){
                AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage",level-1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);

                level +=1;
                ItemExp+=expToAdd-ItemExpMax;
                ItemExpMax= exp.ExperiencetoLevel(5, level);
                meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
                lore.clear();
                lore.add("");
                lore.add("§7XP §f" + ItemExp + " §7/ §f" +  ItemExpMax);
                lore.add("§7Level §f" + (double) level);
                meta.setLore(lore);
                item.setItemMeta(meta);

                meta.setLore(lore);
                item.setItemMeta(meta);
            }
                else{
                    ItemExp+=expToAdd;
                    lore.clear();
                    lore.add("");
                    lore.add("§7XP §f" + ItemExp + " §7/ §f" +  ItemExpMax);
                    lore.add("§7Level §f" + (double) level);

                    meta.setLore(lore);
                    item.setItemMeta(meta);
                }
        }

    }

     */
    /*
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        Player player= null;
        ItemStack item= player.getInventory().getItemInMainHand();
        double damage=0; //
        double ItemDamage=item.;
        if (item.hasItemMeta()){
            event.setDamage(ItemDamage);
        }




    }

*/

}




