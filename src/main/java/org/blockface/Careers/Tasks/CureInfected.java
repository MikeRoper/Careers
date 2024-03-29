package org.blockface.Careers.Tasks;

import org.blockface.Careers.Managers.HealthManager;
import org.bukkit.entity.Player;

public class CureInfected implements Runnable
{
    private Player player;

    public CureInfected(Player p)
    {
        this.player = p;
    }
    public void run()
    {
        if(!HealthManager.IsSick(player)) return;
        HealthManager.CurePlayer(this.player);
    }
}