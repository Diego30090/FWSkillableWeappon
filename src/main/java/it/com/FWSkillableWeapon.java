package it.com;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public final class FWSkillableWeapon extends JavaPlugin {

    private YamlConfiguration yamlConfiguration;

    public static FWSkillableWeapon plugin;

    @Override
    public void onEnable() {
        plugin =this;
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new Events(),this);
    }

    @Override
    public void onDisable() {
    }





    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

       File file =new File(FWSkillableWeapon.plugin.getDataFolder(), "config.yml");

        if(label.equalsIgnoreCase("producer")){
            if(sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "The Plugin FWSkillableWeapon has been produced by Diego30090");
                return true;
            }
        }


        if(label.equalsIgnoreCase("setCoefficient")) {
            yamlConfiguration = yamlConfiguration.loadConfiguration(file);
            EntityType type = null;


            if (sender instanceof Player) {

                Player player = (Player) sender;
                if (player.isOp()) {
                    String defaultSetting = "leveling.exp_coefficient";
                    if (args.length >= 1) {

                        if (args[0].matches("^[0-9]*$")) {
                            try {
                                yamlConfiguration.set(defaultSetting, Double.valueOf(args[0]));
                                player.sendMessage(ChatColor.GREEN + "Command done!");
                            } catch (IllegalArgumentException e) {
                                player.sendMessage(ChatColor.RED + "The argument you inserted is not a number!");
                            }
                            try {
                                yamlConfiguration.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Wrong Argument Type!");
                            player.sendMessage(ChatColor.RED + "The correct Syntax is /setCoefficient [newCoefficient] ");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Missing Arguments!");
                        player.sendMessage(ChatColor.RED + "The correct Syntax is /setCoefficient [newCoefficient]");
                    }
                    return true;
                }
            }
        }



        if(label.equalsIgnoreCase("mobExperience")){
            yamlConfiguration = yamlConfiguration.loadConfiguration(file);
            EntityType type= null;


            if(sender instanceof Player) {

                Player player = (Player) sender;
                if (player.isOp()) {
                    String defaultSetting = "leveling.mob_experiences.";
                    if (args.length >= 2) {

                        if (args[1].matches("^[a-zA-Z]*$") && args[0].matches("^[0-9]*$")) {
                            try {
                                type = EntityType.valueOf(args[1].toUpperCase());
                                String config2 = defaultSetting + "custom." + args[1].toUpperCase();
                                yamlConfiguration.set(config2, Double.valueOf(args[0]));
                                player.sendMessage(ChatColor.GREEN + "Command done!");
                            } catch (IllegalArgumentException e) {
                                player.sendMessage(ChatColor.RED + "The argument you inserted is not a creature!");
                            }
                            try {
                                yamlConfiguration.save(file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Wrong Argument Type!");
                            player.sendMessage(ChatColor.RED + "The correct Syntax is /mobExperience [newExpValue] [Monster] ");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Missing Arguments!");
                        player.sendMessage(ChatColor.RED + "The correct Syntax is /mobExperience [newExpValue] [Monster]");
                    }
                    return true;
                }
            }
        }
        return false;



    }







}
