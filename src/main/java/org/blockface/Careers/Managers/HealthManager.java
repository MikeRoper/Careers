package org.blockface.Careers.Managers;

import org.blockface.Careers.Careers;
import org.blockface.Careers.Tasks.CureInfected;
import org.blockface.Careers.Util.CareerConfig;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import sun.plugin2.main.server.Plugin;

import java.util.HashMap;
import java.util.Random;

public class HealthManager
{
    private static Careers plugin;
    private static HashMap<Player, Disease> infected = new HashMap<Player, Disease>();
    private static Random random = new Random();

    public static void Initialize(Careers p)
    {
        plugin = p;
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin,Infector,20L*60*20);
    }

    public static void CurePlayer(Player player)
    {
        if(!infected.containsKey(player)) return;
        Disease type = infected.get(player);
        infected.remove(player);
        player.sendMessage("You are not longer infected with " + Disease.RABIES.toString().toLowerCase());
    }

    public static enum Disease
    {
        RABIES
    }

    private static Runnable Infector = new Runnable()
    {
        public void run()
        {
            Player player = SelectRandomPlayer();
            if(IsSick(player)) return;
            InfectPlayer(player,Disease.RABIES," a tramp.");
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin,Infector,20L*60*20);
        }

    };

    private static Player SelectRandomPlayer()
    {
        int max = plugin.getServer().getOnlinePlayers().length;
        return plugin.getServer().getOnlinePlayers()[random.nextInt(max)];

    }

    public static boolean HasDisease(Player player,Disease type)
    {
        if(infected.containsKey(player) && infected.get(player) == type) return true;
        return false;
    }

    public static boolean IsSick(Player player)
    {
        return infected.containsKey(player);
    }

    private static void InfectPlayer(Player player, Disease type, String source)
    {
        infected.put(player,type);
        Chatty.SendImportant(player,"You have caught " + type.name().toLowerCase() + " from " + source);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin,new CureInfected(player),20L*60*8);
    }

    public static void HealPlayer(Player doctor, Player patient)
    {
        int hearts = 20 - patient.getHealth();
        double cost = hearts * CareerConfig.GetHeartCost();
        if(IsSick(patient)) cost += CareerConfig.GetCureCost();
        if(DamageManager.IsPoisoned(patient)) cost += 10;
        if(cost==0) return;
        if(EconomyManager.PlayerPay(patient,doctor,cost,"healing."))
        {
            patient.setHealth(20);
            CurePlayer(patient);
            DamageManager.CurePoisoned(patient);
        }
    }

}
