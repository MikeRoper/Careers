package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public abstract class Career
{
	public abstract String getName();
	
	public abstract String getPluralName();
	
	public abstract Boolean CanLockPick();
	
	public abstract Boolean CanKill();
	
	public abstract Boolean CanHeal();

	public abstract Boolean CanArrest();
	
	public abstract Boolean CanSuperSmelt();
	
	public abstract Boolean CanSlay();
	
	public abstract Boolean CanTame();

	public abstract Boolean CanArrow();

	public abstract ChatColor GetColor();

	public abstract Boolean isVisible();
	
	
	
	

}
