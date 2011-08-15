package org.blockface.Careers.Managers;

import org.blockface.Careers.Util.CareerConfig;
import org.bukkit.entity.Player;

public class HealthManager
{
    public static void HealPlayer(Player doctor, Player patient)
    {
        int hearts = 20 - patient.getHealth();
        if(hearts == 0) return;
        double cost = hearts * CareerConfig.GetHeartCost();
        if(EconomyManager.PlayerPay(patient,doctor,cost," healing.")) patient.setHealth(20);
    }
}
