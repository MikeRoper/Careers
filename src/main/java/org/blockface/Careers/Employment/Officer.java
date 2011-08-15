package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Officer extends Bum
{
	@Override
	public String getName() 
	{
		return "Officer";
	}
	
	@Override
	public String getPluralName() 
	{
		return "Officers";
	}
	
	@Override
	public Boolean CanArrest() 
	{
		return true;
	}
	
	@Override
	public ChatColor GetColor() 
	{
		return ChatColor.BLUE;
	}
	
	@Override
	public Boolean isVisible() 
	{
		return true;
	}
	

}
