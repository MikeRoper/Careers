package org.blockface.Careers.Employment;

import org.blockface.Careers.Managers.EconomyManager;
import org.blockface.Careers.Managers.TownyManager;
import org.blockface.Careers.Util.CareerConfig;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.config.Configuration;

import java.io.File;

public class Agency
{
    private static File directory;
	private static File datafile;
	private static Configuration data;
    private static Plugin plugin;

	public static void Initialize(Plugin p) throws Exception
	{
        plugin = p;
        directory = plugin.getDataFolder();
        datafile = new File(directory,"data.yml");
		if(!directory.exists()) directory.mkdir();
		if(!datafile.exists()) datafile.createNewFile();
		data = new Configuration(datafile);
		data.load();
	}
    public static void setCareer(Player player, String c)
	{
		setCareer(player, ParseCareer(c));
	}

	public static void setCareer(Player player, Career c)
	{
		data.setProperty(player.getName()+".career", c.getName());
        data.save();
        TownyManager.SetTitle(player,c);
		Chatty.SwitchedJob(player, c);
	}

    public static Career getCareer(Player player)
	{
		String car = data.getString(player.getName() +".career","Bum");
		return ParseCareer(car);
	}

    public static Career ParseCareer(String c)
	{
		if(c.equalsIgnoreCase("Thief")) return new Thief();
		if(c.equalsIgnoreCase("Murderer")) return new Murderer();
		if(c.equalsIgnoreCase("Officer")) return new Officer();
		if(c.equalsIgnoreCase("Doctor")) return new Doctor();
		if(c.equalsIgnoreCase("Blacksmith")) return new Blacksmith();
		if(c.equalsIgnoreCase("Boss")) return new Boss();
		if(c.equalsIgnoreCase("Knight")) return new Knight();
		if(c.equalsIgnoreCase("Tamer")) return new Tamer();
		if(c.equalsIgnoreCase("Archer")) return new Archer();
		if(c.equalsIgnoreCase("Assassin")) return new Assassin();
		return new Bum();
	}

    public static void TrainCareer(Player teacher, Player learner)
    {
        Career a = getCareer(teacher);
        Career b = getCareer(learner);
        if(a.getName().equalsIgnoreCase(b.getName())) return;
        if(EconomyManager.PlayerPay(learner,teacher, CareerConfig.GetTrainCost(),"training")) setCareer(learner,getCareer(teacher));
    }
}
