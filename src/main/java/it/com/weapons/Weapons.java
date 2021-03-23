package it.com.weapons;

import org.bukkit.event.Listener;



public class Weapons implements Listener {

    //classe delle armi, probabilmente verrà ignorata
    private int id;
    private String Description;
    private int experience;
    private int WeaponLevel;

    public Weapons(int id, String Description, int experience, int WeaponLevel){
        this.id=id;
        this.Description=Description;
        this.experience=experience;
        this.WeaponLevel=WeaponLevel;

    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getDescription(){
        return Description;
    }
    public void setDescription(String Description){
        this.Description=Description;
    }
    public int getExperience(){
        return experience;
    }
    public void setExperience(int experience){
        this.experience=experience;
    }
    public int getWeaponLevel(){
        return WeaponLevel;
    }
    public void setWeaponLevel(int WeaponLevel){
        this.WeaponLevel=WeaponLevel;
    }

}

