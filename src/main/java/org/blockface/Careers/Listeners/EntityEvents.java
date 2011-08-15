package org.blockface.Careers.Listeners;

import org.blockface.Careers.Managers.ArrowManager;
import org.blockface.Careers.Managers.DamageManager;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.*;

public class EntityEvents extends EntityListener
{
    @Override
    public void onEntityDamage(EntityDamageEvent event)
    {
        if(!(event instanceof EntityDamageByEntityEvent)) return;
		EntityDamageByEntityEvent eve = (EntityDamageByEntityEvent)event;
        //Fire PVP Event
        if(eve.getEntity() instanceof Player && eve.getDamager() instanceof Player) DamageManager.onPVP((Player) eve.getDamager(), (Player) eve.getEntity(), event);
        else if(eve.getDamager() instanceof Player) DamageManager.onMobDamage(eve);
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event)
    {
        if(!(event.getEntity()instanceof Player)) return;
        Player def = (Player)event.getEntity();


        DamageManager.RemoveVictim(def);


        if(!(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent)) return;
		EntityDamageByEntityEvent eve = (EntityDamageByEntityEvent)event.getEntity().getLastDamageCause();
		if(!(eve.getDamager() instanceof Player)) return;
		Player att = (Player)eve.getDamager();
        DamageManager.onKill(att, def);


    }

    @Override
    public void onProjectileHit(ProjectileHitEvent event)
    {
        if(!ArrowManager.IsExplosive(event.getEntity())) return;
        Block b = event.getEntity().getLocation().getBlock();
        for(BlockFace bf : BlockFace.values())
        {
            ArrowManager.SwitchLever(b.getRelative(bf));
        }


    }
}
