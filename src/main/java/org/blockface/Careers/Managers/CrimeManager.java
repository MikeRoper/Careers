package org.blockface.Careers.Managers;

import org.blockface.Careers.Careers;
import org.blockface.Careers.Employment.Agency;
import org.blockface.Careers.Employment.Career;
import org.blockface.Careers.Employment.Murderer;
import org.blockface.Careers.Objects.Crime;
import org.blockface.Careers.Objects.Inmate;
import org.blockface.Careers.Objects.Witness;
import org.blockface.Careers.Tasks.FreeJailed;
import org.blockface.Careers.Tasks.FreeWanted;
import org.blockface.Careers.Util.CareerConfig;
import org.blockface.Careers.Util.Chatty;
import org.blockface.Careers.Util.Visibility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Random;

public class CrimeManager
{
    private static HashMap<Player,Witness> witnesses = new HashMap<Player, Witness>();
    private static HashMap<Player,Crime> wanted = new HashMap<Player,Crime>();
    private static Random random = new Random();
	private static HashMap<Player,Inmate> jailed = new HashMap<Player,Inmate>();;
    private static Plugin plugin;

    public static void Initialize(Plugin p)
    {
        plugin = p;

    }
    public static void AddWanted(Crime c)
    {
        Player criminal = c.getCriminal();
		wanted.put(criminal, c);
		for(Witness w : witnesses.values())
		{
			w.RemoveCriminal(criminal);
		}
		Chatty.Broadcast("Alert: " + criminal.getDisplayName() + " is wanted for " + c.getType().toString().toLowerCase());
		Chatty.SendImportant(criminal, "You are wanted. Police are looking for you.");
        Location l = criminal.getLocation();
        Chatty.NotifyPolice(criminal.getDisplayName() + " was last seen at X:" + l.getBlockX() + " Z:" + l.getBlockZ());


        FreeWanted u = new FreeWanted(criminal);
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin,u,20L*60L*5L);
    }

    public static void RemoveWanted(Player p)
    {
        if(!wanted.containsKey(p)) return;;
        wanted.remove(p);
        Chatty.SendImportant(p, "You managed to get away with your crimes.");
        Chatty.NotifyPolice(p.getName() + " has escaped with his crimes.");

    }

    public static void ArrestPlayer(Player criminal, Player officer)
	{
        Crime crime = wanted.get(criminal);
        wanted.remove(criminal);
        DropInventory(criminal);
        int time = 3;
        if(crime.getType() == Crime.CrimeType.MURDER) time = CareerConfig.GetMurderSentence();
        if(crime.getType() == Crime.CrimeType.THEFT) time = CareerConfig.GetTheftSentence();
        if(crime.getType() == Crime.CrimeType.WEAPONS) time = CareerConfig.GetWeaponsSentence();
        criminal.teleport(JailManager.GetJail(criminal.getWorld()));
		jailed.put(criminal, new Inmate(criminal,crime,time));
		Chatty.Broadcast(criminal.getDisplayName() + " has been arrested for " + crime.getType().toString().toLowerCase());
        Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin,new FreeJailed(criminal),20L*time*60);
        EconomyManager.PayWage(officer,50);
    }

    private static void DropInventory(Player player)
    {
        ItemStack[] contents = player.getInventory().getContents().clone();
        Location l = player.getLocation();
        player.getInventory().clear();
        for(ItemStack item : contents)
        {
            if(item == null) continue;
            player.getWorld().dropItem(l,item);
        }
    }

    public static void AlertWitnesses(Player criminal, Player victim, Crime.CrimeType type)
	{
        if(TownyManager.IsOutsideHome(criminal)) return;
		for(Entity e : criminal.getNearbyEntities(25, 25, 25))
		{
            Crime c = new Crime(criminal, type);
			if(!(e instanceof Player)) continue;
            Player p = (Player)e;
            if(!TownyManager.AreAllies(criminal,p)) continue;
			if(Visibility.CanSee(e.getLocation(),criminal.getLocation()))
			{
				if(p.equals(victim)) continue;
				if(Agency.getCareer(p).CanArrest())
				{
					AddWanted(c);
					return;
				}
				else
				{
					Witness w = GetWitness(p);
					w.AddCrime(c);
				}
			}
		}
	}

    public static Witness GetWitness(Player p)
	{
		if(witnesses.keySet().contains(p)) return witnesses.get(p);
		Witness w = new Witness(p);
		witnesses.put(p, w);
		return w;
	}

    public static Inmate GetInmate(Player p)
	{
		return jailed.get(p);
	}

    public static void RemoveJailed(Player p)
    {
        Inmate i = jailed.get(p);
        jailed.remove(p);
        p.teleport(i.getHome());
        Chatty.SendImportant(p, "You have been released. Be good.");
    }

    public static Boolean IsWanted(Player c)
	{
		if(wanted.containsKey(c)) return true;
		return false;
	}

	public static Boolean IsJailed(Player p)
	{
		if(jailed.containsKey(p)) return true;
		return false;
	}

    public static void AttemptLockPick(PlayerInteractEvent event)
    {

        Career c = Agency.getCareer(event.getPlayer());
        if(c.CanLockPick() && CareerConfig.IsPickable(event.getClickedBlock().getTypeId()))
        {
            if(random.nextInt(100) < CareerConfig.GetLockPickChance())
            {
                event.setCancelled(false);
                Chatty.SendImportant(event.getPlayer(), "You've managed to pick the lock!");
            }
            else
            {
                Chatty.SendImportant(event.getPlayer(), "Your fingers get jammed in the lock.");
                event.getPlayer().damage(1);
            }
            CrimeManager.AlertWitnesses(event.getPlayer(), null, Crime.CrimeType.THEFT);
        }

    }

    public static void WeaponsArrest(PlayerInteractEntityEvent event)
	{
		Player suspect = (Player)event.getRightClicked();
		Player officer = event.getPlayer();
        if(!TownyManager.AreAllies(suspect, officer))
        {
            Chatty.SendImportant(officer, "You do not have jurisdiction over " + suspect.getDisplayName());
            return;
        }
		if(!Careers.isNight(suspect.getWorld())) return;
		if(!Agency.getCareer(officer).CanArrest()) return;
		if(Agency.getCareer(suspect).CanKill())
		{
			if(CareerConfig.IsWeapon(suspect.getItemInHand().getTypeId()))
			{
                CrimeManager.AddWanted(new Crime(suspect, Crime.CrimeType.WEAPONS));
				CrimeManager.ArrestPlayer(suspect,officer);
				return;
			}
			Chatty.SendImportant(officer, "Suspect is not holding a weapons.");
			return;
		}
		Chatty.SendImportant(officer, "Suspect is not a " + Chatty.FormatCareer(new Murderer()));
	}


}
