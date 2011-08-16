package org.blockface.Careers.Managers;

import org.blockface.Careers.Careers;
import org.blockface.Careers.Employment.Agency;
import org.blockface.Careers.Employment.Career;
import org.blockface.Careers.Objects.Crime;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.HashSet;

public class DamageManager
{

    private static HashMap<Player, HashSet<Player>> provokes = new HashMap<Player, HashSet<Player>>();

    private static void addProvoker(Player provoker, Player victim)
	{
		HashSet<Player> provokers = new HashSet<Player>();
		if(provokes.keySet().contains(victim)) provokers = provokes.get(victim);
		provokers.add(provoker);
		provokes.put(victim, provokers);
	}

	private static Boolean IsProvoker(Player victim, Player provoker)
	{
		if(!provokes.keySet().contains(victim)) return false;
        if (provokes.get(victim).contains(provoker))return true;
        return false;
    }

	public static void RemoveVictim(Player victim)
	{
		provokes.remove(victim);
	}

    public static void onPVP(Player att, Player def, EntityDamageEvent event)
    {

        Career ca = Agency.getCareer(att);
        Career cd = Agency.getCareer(def);
        Boolean night = Careers.isNight(att.getWorld());
        Boolean allies = TownyManager.AreAllies(att,def);
        addProvoker(att, def);
        if((ca.CanArrest() && CrimeManager.IsWanted(def) || CrimeManager.IsWanted(att) && cd.CanArrest()) && allies) return;
        if(night)
        {
            if(!allies) return;
            if(IsProvoker(att,def)) return;
            if(ca.CanKill())
            {
                if(cd.CanArrest()) CrimeManager.AddWanted(new Crime(att, Crime.CrimeType.ASSAULT));
                return;
            }
        }
        if(!ca.CanKill() && allies)
        {
            Chatty.SendMessage(att, Chatty.FormatPluralCareer(ca) + " cannot kill friendly citizens.");
            event.setCancelled(true);
            return;
        }
        if(!night)
        {
            Chatty.SendMessage(att, "You may only kill at night.");
            event.setCancelled(true);
            return;
        }
    }

    public static void onKill(Player att, Player def)
    {
        EconomyManager.PlayerPay(def,att,EconomyManager.GetAccount(def).balance(),"dying.");
        Career ca = Agency.getCareer(att);
        Career cd = Agency.getCareer(def);
        if(!TownyManager.AreAllies(att,def)) return;
        if(ca.CanArrest() && CrimeManager.IsWanted(def))
		{

			CrimeManager.ArrestPlayer(def,att);
			return;
		}
        if(!ca.CanKill()) return;
        CrimeManager.AlertWitnesses(att,def, Crime.CrimeType.MURDER);

    }
    private static HashSet<Entity> locked = new HashSet<Entity>();
    public static void onMobDamage(EntityDamageByEntityEvent event)
    {
		Player player = (Player)event.getDamager();
		locked.add(player);
		if(Agency.getCareer(player).CanSlay())
		{
			Entity e = event.getEntity();
			if(!(e instanceof Skeleton) &&!(e instanceof Zombie) &&!(e instanceof Creeper) &&!(e instanceof Spider)) return;
			if(locked.contains(e)) return;
			locked.add(e);
			if(e.isDead()) return;
			event.setDamage(100);
			Chatty.SendMessage(player, "Your powerful strike obliterates your foe!");
			EconomyManager.PayWage(player, 5);
		}
    }


}
