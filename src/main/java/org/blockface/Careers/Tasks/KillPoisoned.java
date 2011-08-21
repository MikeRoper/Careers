package org.blockface.Careers.Tasks;

import org.blockface.Careers.Managers.DamageManager;
import org.bukkit.entity.Player;

public class KillPoisoned implements Runnable
{
    private Player victim;
    public KillPoisoned(Player victim,Player assassin)
    {
        this.victim = victim;
    }
    public void run()
    {
        DamageManager.KillPoisoned(victim);
    }
}
