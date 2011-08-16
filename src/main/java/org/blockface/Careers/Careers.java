package org.blockface.Careers;

import org.blockface.Careers.Commands.ComCareer;
import org.blockface.Careers.Commands.ComCrime;
import org.blockface.Careers.Employment.Agency;
import org.blockface.Careers.Listeners.*;
import org.blockface.Careers.Managers.CrimeManager;
import org.blockface.Careers.Managers.HealthManager;
import org.blockface.Careers.Managers.HellManager;
import org.blockface.Careers.Managers.JailManager;
import org.blockface.Careers.Util.CareerConfig;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Careers extends JavaPlugin
{
    public void onDisable()
    {

    }

    public void onEnable()
    {
        Chatty.Info("Loading.");
        //Load Config
        try
        {
            CareerConfig.Initialize(this);
            Agency.Initialize(this);
            JailManager.Initialize(this);
            CrimeManager.Initialize(this);
            HellManager.Initialize(this);
            HealthManager.Initialize(this);
            //Register Events
            RegisterEvents();
        }
        catch (Exception failed)
        {
            Chatty.Info("Failed to load.");
            failed.printStackTrace();
        }
    }

    private void RegisterEvents()
    {
        PluginManager pm = this.getServer().getPluginManager();

        //Server Events
        pm.registerEvent(Event.Type.PLUGIN_ENABLE,new ServerEvents(), Event.Priority.Normal,this);

        //Entity Events
        EntityEvents ee = new EntityEvents();
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, ee, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, ee, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.PROJECTILE_HIT, ee, Event.Priority.Highest, this);

        //Player Events
        PlayerEvents pe = new PlayerEvents();
        pm.registerEvent(Event.Type.PLAYER_RESPAWN, pe, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, pe, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT_ENTITY, pe, Event.Priority.Highest, this);
        pm.registerEvent(Event.Type.PLAYER_TELEPORT, pe, Event.Priority.Highest, this);

        //Player Chat Events
        PlayerChatEvents pc = new PlayerChatEvents();
        pm.registerEvent(Event.Type.PLAYER_CHAT, pc, Event.Priority.Lowest, this);

        //Inventory Events
        pm.registerEvent(Event.Type.FURNACE_SMELT,new InventoryEvents(), Event.Priority.Highest,this);

        //Block Events
        //pm.registerEvent(Event.Type.BLOCK_DAMAGE,new BlockEvents(), Event.Priority.Normal,this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(label.equalsIgnoreCase("c")) ComCareer.Execute(sender,args);
        if(label.equalsIgnoreCase("crime")) ComCrime.Execute(sender,args);
        return true;
    }

    public static Boolean isNight(World w)
	{
		long time = w.getTime();
		if(time < 14000)
		{
			return false;
		}
		return true;
	}

}

