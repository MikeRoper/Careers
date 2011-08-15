package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Thief extends Bum
{
	
	public String getName() 
	{
		return "Thief";
	}
	
	@Override
	public String getPluralName() 
	{
		return "Thieves";
	}
	
	@Override
    public Boolean CanLockPick()
	{
		return true;
	}

    @Override
    public Boolean CanArrow() {
        return true;
    }

    public ChatColor GetColor()
	{
		return ChatColor.GRAY;
	}
	
	
	
	

}
