package org.blockface.Careers.Listeners;

import com.palmergames.bukkit.towny.Towny;
import org.blockface.Careers.Managers.EconomyManager;
import org.blockface.Careers.Managers.TownyManager;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

public class ServerEvents extends ServerListener
{
    @Override
    public void onPluginEnable(PluginEnableEvent event)
    {
        if(event.getPlugin() instanceof Towny) TownyManager.LoadTowny(event.getPlugin());
        else EconomyManager.LoadMethods(event.getPlugin());
    }
}
