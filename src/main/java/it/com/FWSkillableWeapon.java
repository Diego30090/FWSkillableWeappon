package it.com;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class FWSkillableWeapon extends JavaPlugin {


    public static FWSkillableWeapon plugin;

    @Override
    public void onEnable() {
        // Plugin startup login
        plugin =this;
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Events(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }





    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("producer")){
            if(sender instanceof Player) {
                //player
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "The Plugin FWSkillableWeapon has been produced by Diego30090");
            }
        }
        return false;
    }













    /*
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if (label.equalsIgnoreCase(("randomblock"))){
            if (!sender.hasPermission("randomblock.reload")){
                sender.sendMessage(ChatColor.RED + "You cannot run this command");
            }
        if (args.length==0){
            sender.sendMessage(ChatColor.RED + "Usage: /randomblock reload");
            if(args.length>0)[
                    if(args[0].equalsIgnoreCase(("reload"))){
                        this.reloadConfig();
                        this.saveConfig();
                        this.getConfig().;
                        this.saveDefaultConfig();

                    }
            }
        }


    return false;

    }

*/






}
