package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Murderer extends Bum
{
	@Override
	public String getName() 
	{
		return "Murderer";
	}
	
	@Override
	public String getPluralName() 
	{
		return "Murderers";
	}
	
	@Override
	public Boolean CanKill() 
	{
		return true;
	}
	
	@Override
	public ChatColor GetColor() 
	{
		return ChatColor.DARK_RED;
	}

}
