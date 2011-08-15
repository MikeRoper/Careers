package org.blockface.Careers.Listeners;

import org.blockface.Careers.Util.Chatty;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockListener;

import java.util.HashMap;

public class BlockEvents extends BlockListener
{

    HashMap<Block, Integer> BlockTracker = new HashMap<Block, Integer>();
    @Override
    public void onBlockDamage(BlockDamageEvent event) {
        if(!(event.getPlayer().getName().equalsIgnoreCase("SwearWord"))) return;
        Chatty.Info("Block hit.");
        int i =0;
        if(BlockTracker.containsKey(event.getBlock())) i = BlockTracker.get(event.getBlock());
        if(i+1 == 3)
        {
            event.setInstaBreak(true);
            return;
        }
        BlockTracker.put(event.getBlock(),i+1);
    }
}
