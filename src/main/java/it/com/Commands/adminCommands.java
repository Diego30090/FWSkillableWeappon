package it.com.Commands;

import it.com.config.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import org.jetbrains.annotations.NotNull;



public class adminCommands implements CommandExecutor {
/*
    Config config= new Config();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
              /*
                if(label.equalsIgnoreCase("mobexperience")){
                    if(sender instanceof Player) {
                        //player
                        Player player = (Player) sender;
                        player.sendMessage(ChatColor.GREEN + "The Plugin FWSkillableWeapon has been produced by Diego30090");
                        return true;
                    }
                }*/
               // return false;









@Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

/*
            if(sender instanceof Player){

                if(command.getName().equalsIgnoreCase("MobExperience")) {
                    String defaultSetting="leveling.mob_experiences.";

                    try{
                        double d = Double.parseDouble(args[0]);
                            try{
                                type=EntityType.valueOf(args[1]);
                                config.setDouble(defaultSetting+"custom."+args[1].toUpperCase(),Double.valueOf(args[0]));
                            } catch(IllegalArgumentException exp){
                                config.setDouble(defaultSetting+"default",Double.valueOf(args[0]));
                                //no valid mobType
                        }
                    }catch(NumberFormatException nfe){
                        //no valid Value
                        return true;
                    }
                }
                if(command.getName().equalsIgnoreCase("ExpCoefficient")){
                    try{
                        double d = Double.parseDouble(args[0]);
                        String defaultSetting="leveling.exp_coefficient";
                        config.setDouble(defaultSetting,Double.valueOf(args[0]));

                    }catch(NumberFormatException e){
                        return true;
                    }

                }

            }

 */
        return true;
    }




}







