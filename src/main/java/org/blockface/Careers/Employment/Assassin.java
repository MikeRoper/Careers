package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Assassin extends Bum
{
    @Override
	public String getName()
	{
		return "Assassin";
	}

	@Override
	public String getPluralName()
	{
		return "Assassins";
	}

	@Override
	public Boolean CanPoison()
	{
		return true;
	}

	@Override
	public ChatColor GetColor()
	{
		return ChatColor.RED;
	}

	@Override
	public Boolean isVisible()
	{
		return true;
	}
}
