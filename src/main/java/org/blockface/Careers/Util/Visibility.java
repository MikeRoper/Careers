package org.blockface.Careers.Util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Visibility
{
    private static Material[] transparent = new Material[] {Material.LADDER,Material.WATER,Material.AIR,Material.TORCH,Material.GLASS,Material.FENCE,Material.WOOD_DOOR,Material.WOODEN_DOOR};
    public static Boolean CanSee(Location from, Location to)
	{
		int x1 = from.getBlockX();
		int y1 = from.getBlockY();
		int z1 = from.getBlockZ();
		int x2 = to.getBlockX();
		int y2 = to.getBlockY();
		int z2 = to.getBlockZ();
		int max = Distance3D(from, to);
		if(max > 25) return false;
		//to.getWorld().getBlockAt(to).setType(Material.BEDROCK);
		//to.getWorld().getBlockAt(from).setType(Material.BEDROCK);
		//plugin.log.info(plugin.prefix + "Max: " + max);
		//plugin.log.info(plugin.prefix + x1 +"," + y1 + "," + z1);
		//plugin.log.info(plugin.prefix + x2 +"," + y2 + "," + z2);
		float inc = 1f/(float)max;
		for(float t=0;t<1;t+=0.1f)
		{
			int nx = (int)(x1 + (x2 -x1) * t);
			int ny = (int)(y1 + (y2 -y1) * t);
			int nz = (int)(z1 + (z2 -z1) * t);
			//plugin.log.info(plugin.prefix + nx +"," + ny + "," + nz);
			Block m = to.getWorld().getBlockAt(nx,ny,nz);

			//plugin.log.info(plugin.prefix + m.getType().name());
			if(isNotTransparent(m.getType())) return false;
		}
		return true;
	}

	private static int Distance3D(Location from, Location to)
    {
        //     __________________________________
        //d = &#8730; (x2-x1)^2 + (y2-y1)^2 + (z2-z1)^2
        //
		int x1 = from.getBlockX();
		int y1 = from.getBlockY();
		int z1 = from.getBlockZ();
		int x2 = to.getBlockX();
		int y2 = to.getBlockY();
		int z2 = to.getBlockZ();
        //Our end result
        int result = 0;
        //Take x2-x1, then square it
        double part1 = Math.pow((x2 - x1), 2);
        //Take y2-y1, then square it
        double part2 = Math.pow((y2 - y1), 2);
        //Take z2-z1, then square it
        double part3 = Math.pow((z2 - z1), 2);
        //Add both of the parts together
        double underRadical = part1 + part2 + part3;
        //Get the square root of the parts
        result = (int)Math.sqrt(underRadical);
        //Return our result
        return result;

    }

	private static Boolean isNotTransparent(Material mat)
	{

		for(Material m : transparent)
		{
			if(m.equals(mat)) return false;
		}
		return true;
	}
}
