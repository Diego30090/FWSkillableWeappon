package it.com;
import org.bukkit.plugin.java.JavaPlugin;

public final class FWSkillableWeapon extends JavaPlugin {




    @Override
    public void onEnable() {
        // Plugin startup login
        getServer().getConsoleSender().sendMessage(org.bukkit.ChatColor.GREEN + "Inizio Test per i drop a scelta\n\n");
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Events(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(org.bukkit.ChatColor.RED + "Fine Test per i drop a scelta\n\n");
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



/*


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


*/
}
