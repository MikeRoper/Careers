package org.blockface.Careers.Tasks;

import org.blockface.Careers.Managers.CrimeManager;
import org.bukkit.entity.Player;

public class FreeJailed implements Runnable
{
    private Player player;

    public FreeJailed(Player p)
    {
        this.player = p;
    }
    public void run()
    {
        CrimeManager.RemoveJailed(this.player);
    }
}
