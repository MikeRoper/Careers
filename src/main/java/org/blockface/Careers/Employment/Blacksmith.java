package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Blacksmith extends Bum
{
	@Override
	public String getName() 
	{
		return "Blacksmith";
	}
	
	@Override
	public String getPluralName() 
	{
		return "Blacksmiths";
	}

	@Override
	public ChatColor GetColor() 
	{
		return ChatColor.DARK_AQUA;
	}
	
	@Override
	public Boolean isVisible() 
	{
		return true;
	}

	@Override
	public Boolean CanSuperSmelt() 
	{
		return true;
	}


}
