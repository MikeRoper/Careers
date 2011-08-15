package org.blockface.Careers.Util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CareerConfig
{
    private static File directory;
    private static File configfile;
    private static Configuration config;
    private static Plugin plugin;

    public static void Initialize(Plugin p) throws Exception
    {
        plugin = p;
        directory = plugin.getDataFolder();
        configfile = new File(directory,"config.yml");
        if(!directory.exists()) directory.mkdir();
        if(!configfile.exists()) configfile.createNewFile();
        LoadConfig();
    }

    public static void LoadConfig() throws Exception
    {
        config = new Configuration(configfile);
        config.load();
        config.setProperty("author", "SwearWord");
        config.setProperty("version",plugin.getDescription().getVersion());
        GetArrestWage();
        GetLockPickChance();
        GetHellTime();
        GetTheftSentence();
        GetMurderSentence();
        GetWeaponsSentence();
        GetDisabledWorlds();
        GetTrainCost();
        GetPickables();
        GetHeartCost();
        GetWeapons();
        config.save();
        Chatty.Info("Loaded Config");
    }

    public static Double GetHeartCost()
    {
        return config.getDouble("doctor.costperheart",5);
    }

    public static Double GetTrainCost()
    {
        return config.getDouble("training.cost",50);
    }

    public static Double GetCureCost()
    {
        return config.getDouble("doctor.curecost",25);
    }

    public static int GetHellTime() {
        return config.getInt("hell.time",2);
    }

    public static int GetArrestWage()
    {
        return config.getInt("wages.officer.arrest",25);
    }

    public static int GetTheftSentence()
    {
        return config.getInt("crime.sentence.theft",3);
    }

    public static int GetMurderSentence()
    {
        return config.getInt("crime.sentence.murder",5);
    }

    public static int GetWeaponsSentence()
    {
        return config.getInt("crime.sentence.weapons",2);
    }

    public static Location GetJail(World w)
	{
		String world = w.getName();
        Location spawn = w.getSpawnLocation();
		int x = config.getInt(world + ".x",spawn.getBlockX());
		int y = config.getInt(world + ".y",spawn.getBlockY());
		int z = config.getInt(world + ".z",spawn.getBlockZ());
		Location result = new Location(w, x, y, z);
		return result;
	}

    private static List<String> GetDisabledWorlds()
    {
        ArrayList<String> def = new ArrayList<String>();
        def.add("none");
        return config.getStringList("disabled-worlds",def);
    }

    public static Boolean isWorldDisabled(World w)
    {
        return GetDisabledWorlds().contains(w.getName());
    }

    public static String GetVersion()
    {
        return config.getString("version","0");
    }


    public static int GetLockPickChance()
    {
        return config.getInt("crime.theft.chance",2);
    }

    public static Boolean IsPickable(Integer id)
    {
        String item = id.toString();
        for(String s: GetPickables().split(","))
        {
            if(s.equalsIgnoreCase(item)) return true;
        }
        return false;
    }

    private static String GetPickables()
    {
        return config.getString("crime.theft.pickables","64,54");
    }

    private static String GetWeapons()
    {
		return config.getString("crime.weapons", "261,252,267,268,270,271,272,274,275,276,278,279");
    }

    public static boolean IsWeapon(Integer typeId)
    {
        String[] valid = GetWeapons().split(",");
		return ArrayContains(typeId.toString(), valid);
	}

	private static Boolean ArrayContains(String i, String[] array)
	{
		for(String s : array)
		{
			if(s.equalsIgnoreCase(i)) return true;
		}
		return false;
	}
}
