package org.blockface.Careers.Managers;

import org.bukkit.block.Block;

import java.util.HashSet;

public class FurnaceManager
{

    private static HashSet<Block> furnaces = new HashSet<Block>();

	public static void addFurnace(Block b)
	{
		furnaces.add(b);
	}

	public static Boolean isRegistered(Block b)
	{
		return furnaces.contains(b);
	}
}
