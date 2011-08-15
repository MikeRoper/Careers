package org.blockface.Careers.Managers;

import org.blockface.Careers.Util.Chatty;
import org.blockface.Careers.Util.InventoryManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Lever;

import java.util.HashSet;

public class ArrowManager
{
    private static HashSet<Entity> arrows = new HashSet<Entity>();

    public static void AddArrow(Entity arrow)
    {
        arrows.add(arrow);
    }

    public static Boolean IsExplosive(Entity arrow)
    {
        if(arrows.contains(arrow))
        {
            arrows.remove(arrow);
            arrow.remove();
            return true;
        }
        return false;
    }

    public static void SwitchLever(Block block)
    {
        if(block.getType() == Material.LEVER || block.getType() == Material.STONE_PLATE)
        {
            Lever lever = new Lever();
            lever.setData(block.getData());
            lever.setPowered(!lever.isPowered());
            Block b = block.getRelative(lever.getAttachedFace());
            b.setData(lever.getData());
            block.setData(lever.getData());
            Chatty.Info("Hit lever.");
        }
    }

    public static void FireArrow(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        InventoryManager im = new InventoryManager(player);
        if(im.quantify(new ItemStack(Material.ARROW)).getAmount() < 1) return;
        im.remove(new ItemStack(Material.ARROW,1));
        ArrowManager.AddArrow(player.shootArrow());
        event.setCancelled(true);
    }
}
