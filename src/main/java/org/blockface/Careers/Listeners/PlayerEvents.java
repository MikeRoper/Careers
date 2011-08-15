package org.blockface.Careers.Listeners;

import org.blockface.Careers.Employment.Agency;
import org.blockface.Careers.Employment.Career;
import org.blockface.Careers.Managers.*;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;

public class PlayerEvents extends PlayerListener
{
    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        Player player = event.getPlayer();
        if(CrimeManager.IsJailed(event.getPlayer()))
        {
            event.setRespawnLocation(JailManager.GetJail(player.getWorld()));
            Chatty.SendMessage(player, "Jail time left: " + CrimeManager.GetInmate(event.getPlayer()).getTimeLeft() + " seconds");
            return;
        }

        if(HellManager.IsDead(player))
        {
            event.setRespawnLocation(HellManager.GetSpawn());
            return;
        }

        HellManager.AddDead(player);
        event.setRespawnLocation(HellManager.GetSpawn());
    }



    @Override
    public void onPlayerTeleport(PlayerTeleportEvent event)
    {
        Player player = event.getPlayer();
        if(CrimeManager.IsJailed(event.getPlayer()))
        {
            event.setTo(JailManager.GetJail(player.getWorld()));
            Chatty.SendMessage(player,"Jail time left: " + CrimeManager.GetInmate(event.getPlayer()).getTimeLeft() + " seconds");
            return;
        }
        if(HellManager.IsDead(event.getPlayer()))
        {
            event.setTo(HellManager.GetSpawn());
            return;
        }
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if(CrimeManager.IsJailed(event.getPlayer()))
        {
            event.setCancelled(true);
            player.teleport(player);
            Chatty.SendMessage(player,"Jail time left: " + CrimeManager.GetInmate(event.getPlayer()).getTimeLeft() + " seconds");
            return;
        }

        if(event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            if(event.isCancelled())
            {
                CrimeManager.AttemptLockPick(event);
            }
        }

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) && player.getItemInHand().getType() == Material.BOW && Agency.getCareer(player).CanArrow()) ArrowManager.FireArrow(event);

		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.FURNACE) && Agency.getCareer(player).CanSuperSmelt())
		{
			FurnaceManager.addFurnace(event.getClickedBlock());
			Chatty.SendMessage(player, "This furnace will supersmelt.");
		}
    }

    @Override
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event)
    {
        if(!(event.getRightClicked() instanceof Player)) return;
        Player target = (Player)event.getRightClicked();
        Player player = event.getPlayer();
        Career cp = Agency.getCareer(player);
        Career ct = Agency.getCareer(target);
        if(player.getItemInHand().getType() == Material.BOOK)
        {
            Agency.TrainCareer(target,player);
            return;
        }
        if(ct.CanHeal())
        {
            HealthManager.HealPlayer(target,player);
            return;
        }
        if(CrimeManager.IsWanted(target) && cp.CanArrest())
        {
            CrimeManager.ArrestPlayer(target,player);
            return;
        }
        if(cp.CanArrest()) CrimeManager.WeaponsArrest(event);

    }
}
