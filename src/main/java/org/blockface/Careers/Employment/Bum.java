package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Bum extends Career
{
	@Override
	public String getName() 
	{
		return "Bum";
	}
	
	public String getPluralName() 
	{
		return "Bums";
	}

    @Override
    public Boolean CanArrow() {
        return false;
    }

    @Override
    public Boolean CanPoison() {
        return false;
    }

    @Override
	public Boolean CanLockPick() 
	{
		return false;
	}

	@Override
	public Boolean CanKill() 
	{
		return false;
	}

	@Override
	public Boolean CanHeal() 
	{
		return false;
	}

	@Override
	public Boolean CanArrest() 
	{
		return false;
	}

	@Override
	public ChatColor GetColor() 
	{
		return ChatColor.WHITE;
	}

	@Override
	public Boolean CanTame() 
	{
		return false;
	}
	
	@Override
	public Boolean isVisible() 
	{
		return false;
	}

	@Override
	public Boolean CanSuperSmelt() 
	{
		return false;
	}

	@Override
	public Boolean CanSlay() 
	{
		return false;
	}
	
	

}
