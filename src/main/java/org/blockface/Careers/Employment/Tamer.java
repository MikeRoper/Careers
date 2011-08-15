package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Tamer extends Bum
{
	
	@Override
	public String getName() 
	{
		return "Tamer";
	}
	
	@Override
	public String getPluralName() 
	{
		return "Tamers";
	}
	
	@Override
	public Boolean CanTame() 
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
