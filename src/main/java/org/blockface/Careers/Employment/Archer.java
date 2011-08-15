package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Archer extends Bum
{
	
	@Override
	public String getName() 
	{
		return "Archer";
	}
	
	@Override
	public String getPluralName() 
	{
		return "Archers";
	}
	
	@Override
	public Boolean CanArrow()
	{
		return true;
	}
	
	@Override
	public ChatColor GetColor() 
	{
		return ChatColor.GREEN;
	}
	
	@Override
	public Boolean isVisible() 
	{
		return true;
	}

}
