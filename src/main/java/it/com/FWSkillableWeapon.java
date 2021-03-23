package it.com;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public final class FWSkillableWeapon extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup login
        getServer().getConsoleSender().sendMessage(org.bukkit.ChatColor.GREEN + "Inizio Test per i drop a scelta\n\n");
        getServer().getPluginManager().registerEvents(new Events(),this);
        loadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(org.bukkit.ChatColor.RED + "Fine Test per i drop a scelta\n\n");
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    //drop dell'oggetto da parte del mob

    /*
    @EventHandler
    public void onMobDeath(){
        if(event.getEntity().getKiller() instanceof Player){
            if(event.getEntity().getType()== EntityType.CREEPER){
                event.getDrops().add(new ItemStack(NETHERITE_AXE,1));
            }
        }
    }

*/

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
       if(label.equalsIgnoreCase("hello")){
           if(sender instanceof Player) {
               //player
               Player player = (Player) sender;
               if (player.hasPermission("hello.use")) {
                   player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to the server");
                   return true;
               }
               player.sendMessage(ChatColor.RED + "You don't have permission!");
               return true;
           }
           else{
               //console
               sender.sendMessage("Hey console!");
               return true;
           }
       }
        return false;
    }



}
