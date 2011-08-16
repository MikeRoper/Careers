package org.blockface.Careers.Listeners;

import org.blockface.Careers.Managers.CrimeManager;
import org.blockface.Careers.Managers.HealthManager;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class PlayerChatEvents extends PlayerListener
{
    @Override
    public void onPlayerChat(PlayerChatEvent event)
    {
        if(HealthManager.HasDisease(event.getPlayer(), HealthManager.Disease.RABIES))
        {
            event.setMessage(ChatColor.RED + "*Foams at Mouth*");
        }
    }
}
