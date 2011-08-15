package org.blockface.Careers.Tasks;

import org.blockface.Careers.Managers.HellManager;
import org.bukkit.entity.Player;

public class FreeHell implements Runnable
{
    Player player;
    public FreeHell(Player p)
    {
        this.player = p;
    }

    public void run()
    {
        HellManager.SecondChance(player);
    }
}
