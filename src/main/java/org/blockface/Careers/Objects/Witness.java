package org.blockface.Careers.Objects;

import org.blockface.Careers.Managers.CrimeManager;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Witness 
{
	private HashMap<Player,Crime> crimes = new HashMap<Player,Crime>();
	private Player player;
	
	public Witness(Player p)
	{
		this.player = p;
	}
	
	public void AddCrime(Crime c)
	{
		crimes.put(c.getCriminal(), c);
        Chatty.WitnessCrime(c.getType(),this.player,c.getCriminal());
	}
	
	public void ClearCrimes()
	{
		for(Player p : this.crimes.keySet())
		{
			Chatty.DeadWitness(p, this.player);
		}
		crimes = new HashMap<Player, Crime>();
	}
	
	public void RemoveCriminal(Player p)
	{
		crimes.remove(p);
	}
	
	public HashMap<Player, Crime> getCrimes() {
		return crimes;
	}
	
	private Crime GetCrime(String player)
	{
		player = player.toLowerCase();
		for(Player p : crimes.keySet())
		{
			if(p.getName().toLowerCase().contains(player))
			{
				return crimes.get(p);
			}
		}
		return null;
	}
	
	public Boolean ReportCrime(String player)
	{
		Crime c = GetCrime(player);
		if(c==null) return false;
		CrimeManager.AddWanted(c);
		crimes.remove(c.getCriminal());
		return true;
	}

}
