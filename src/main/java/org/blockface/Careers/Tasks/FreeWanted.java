package org.blockface.Careers.Tasks;

import org.blockface.Careers.Managers.CrimeManager;
import org.bukkit.entity.Player;

public class FreeWanted implements Runnable
{
    private Player player;
    public FreeWanted(Player p)
    {
        this.player = p;
    }
    public void run()
    {
        CrimeManager.RemoveWanted(player);
    }

}
