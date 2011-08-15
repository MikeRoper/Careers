package org.blockface.Careers.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class Inmate 
{
	private Player player;
	private Location home;
	private long release;
	private Crime crime;
	
	public Inmate(Player p, Crime c, int sentence)
	{
		this.player = p;
		this.crime = c;
		Location l = p.getLocation();
		this.home = new Location(l.getWorld(), l.getBlockX(), l.getBlockY(), l.getBlockZ());
		this.release = Calendar.getInstance().getTimeInMillis() + sentence * 60 * 1000;
	}

	public Crime getCrime() {
		return crime;
	}
	
	public long getTimeLeft()
	{
		return (this.release - Calendar.getInstance().getTimeInMillis())/1000;
	}
	
	public Location getHome() {
		return home;
	}
	
	public long getRelease() {
		return release;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Boolean SentenceOver()
	{
		if(Calendar.getInstance().getTimeInMillis()>this.release) return true;
		return false;
	}
}
