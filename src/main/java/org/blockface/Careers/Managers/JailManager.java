package org.blockface.Careers.Managers;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

import java.io.File;

public class JailManager
{

    private static File directory;
    private static Configuration jails;
    private static Plugin plugin;

    public static void Initialize(Plugin p) throws Exception
    {
        plugin = p;
        directory = plugin.getDataFolder();
        File jailfile = new File(directory, "jails.yml");
        if(!directory.exists()) directory.mkdir();
        if(!jailfile.exists()) jailfile.createNewFile();
        jails = new Configuration(jailfile);
        jails.load();
    }

	public static void AddJail(Location l)
	{
		String world = l.getWorld().getName();
		jails.setProperty(world + ".x", l.getBlockX());
		jails.setProperty(world + ".y", l.getBlockY());
		jails.setProperty(world + ".z", l.getBlockZ());
		jails.save();
	}

	public static Location GetJail(World w)
	{
		String world = w.getName();
        Location spawn = w.getSpawnLocation();
		int x = jails.getInt(world + ".x",spawn.getBlockX());
		int y = jails.getInt(world + ".y",spawn.getBlockY());
		int z = jails.getInt(world + ".z",spawn.getBlockZ());
		Location result = new Location(w, x, y, z);
		return result;
	}


}
