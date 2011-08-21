package org.blockface.Careers.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Crime
{
	private CrimeType type;
	private Player criminal;
	private Location location;

	public static enum CrimeType
	{
		MURDER,
		THEFT,
		WEAPONS,
        ASSAULT,
        POISONING
	}

	public Crime(Player suspect, CrimeType type)
	{
		this.criminal = suspect;
		Location l = suspect.getLocation();
		this.location = new Location(l.getWorld(), l.getBlockX(), l.getBlockY(), l.getBlockZ());
		this.type = type;
	}

	public CrimeType getType() {
		return type;
	}

	public Player getCriminal() {
		return criminal;
	}

}
