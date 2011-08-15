package org.blockface.Careers.Listeners;

import org.blockface.Careers.Managers.FurnaceManager;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryListener;

public class InventoryEvents extends InventoryListener
{
	@Override
	public void onFurnaceSmelt(FurnaceSmeltEvent event)
	{
		if(event.isCancelled()) return;
		if(!FurnaceManager.isRegistered(event.getFurnace())) return;
		event.getResult().setAmount(2);
		//furnace.getInventory().setItem(2, event.getResult());
	}

}
