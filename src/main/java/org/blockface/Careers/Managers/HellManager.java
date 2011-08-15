package org.blockface.Careers.Managers;

import com.palmergames.bukkit.towny.TownyException;
import org.blockface.Careers.Tasks.FreeHell;
import org.blockface.Careers.Util.CareerConfig;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;

public class HellManager
{
    private static HashSet<Player> dead = new HashSet<Player>();
    private static Plugin plugin;
    private static Location hellspawn;

    public static void Initialize(Plugin p)
    {
        plugin = p;
        hellspawn = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
        for(World w: Bukkit.getServer().getWorlds())
        {
            if(w.getEnvironment() != World.Environment.NETHER) continue;
            hellspawn = w.getSpawnLocation();
            hellspawn.setY(128);
        }
    }

    public static void AddDead(Player p)
    {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin,new FreeHell(p), 20 * 30 * CareerConfig.GetHellTime());
        dead.add(p);
        Chatty.SendImportant(p,"You have died. You will be given a second chance in " + CareerConfig.GetHellTime() + " minutes.");

    }

    public static Location GetSpawn()
    {
        return hellspawn;
    }

    public static void SecondChance(Player p)
    {
        dead.remove(p);
        try {
            p.teleport(TownyManager.GetTown(p).getSpawn());
        } catch (TownyException e) {
            p.teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
        }
        Chatty.SendMessage(p,"You have been brought back to life.");
    }

    public static Boolean IsDead(Player p)
    {
        return dead.contains(p);
    }
}
