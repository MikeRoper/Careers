package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Doctor extends Bum
{
	
	@Override
	public String getName() 
	{
		return "Doctor";
	}
	
	@Override
	public String getPluralName() 
	{
		return "Doctors";
	}
	
	@Override
	public Boolean CanHeal() 
	{
		return true;
	}
	
	@Override
	public ChatColor GetColor() 
	{
		return ChatColor.DARK_PURPLE;
	}
	
	@Override
	public Boolean isVisible() 
	{
		return true;
	}

}
