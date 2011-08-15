package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Knight extends Bum
{
	
	@Override
	public String getName() 
	{
		return "Knight";
	}
	
	@Override
	public String getPluralName() 
	{
		return "Knights";
	}
	
	@Override
	public Boolean CanSlay() 
	{
		return true;
	}
	
	@Override
	public ChatColor GetColor() 
	{
		return ChatColor.YELLOW;
	}
	
	@Override
	public Boolean isVisible() 
	{
		return true;
	}

	

}
