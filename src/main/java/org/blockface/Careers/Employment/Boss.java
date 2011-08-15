package org.blockface.Careers.Employment;

import org.bukkit.ChatColor;

public class Boss extends Career
{
	@Override
	public String getName() 
	{
		return "Boss";
	}
	
	public String getPluralName() 
	{
		return "Bosses";
	}

    @Override
    public Boolean CanArrow() {
        return true;
    }

    @Override
	public Boolean CanLockPick() 
	{
		return true;
	}

	@Override
	public Boolean CanKill() 
	{
		return true;
	}

	@Override
	public Boolean CanHeal() 
	{
		return true;
	}

	@Override
	public Boolean CanArrest() 
	{
		return true;
	}

	@Override
	public Boolean CanTame() 
	{
		return true;
	}

	@Override
	public ChatColor GetColor() 
	{
		return ChatColor.GOLD;
	}
	
	@Override
	public Boolean isVisible() 
	{
		return true;
	}

	@Override
	public Boolean CanSuperSmelt() 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Boolean CanSlay() 
	{
		return true;
	}
	
	

}
