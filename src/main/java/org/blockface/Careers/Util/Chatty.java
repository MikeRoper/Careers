package org.blockface.Careers.Util;

import org.blockface.Careers.Employment.Agency;
import org.blockface.Careers.Employment.Career;
import org.blockface.Careers.Managers.EconomyManager;
import org.blockface.Careers.Objects.Crime;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class Chatty
{
    private static Logger logger = Logger.getLogger("minecraft");
    private static String prefix = ChatColor.DARK_GREEN + "[Careers] " + ChatColor.WHITE;
    private static String dullprefix = "[Careers] ";

    public static void Error(String message)
    {
        logger.severe(dullprefix + message);
    }
    public static void Info(String message)
    {
        logger.info(dullprefix + message);
    }

    public static void SendMessage(CommandSender sender, String message)
	{
		sender.sendMessage(prefix + message);
	}

    public static void SendMessage(Player p, String message)
	{
		p.sendMessage(prefix + message);
	}

    public static void EarnedWage(Player source, double amount)
    {
        SendMessage(source, "Your town paid you " + FormatMoney(amount));
    }

    public static void Bankrupt(Player source)
    {
        SendImportant(source, "Your town is bankrupt and cannot afford to pay you.");
    }

    public static void SendImportant(Player p, String message)
	{
		p.sendMessage(prefix + ChatColor.RED + message);
	}

    public static void Broadcast(String message)
    {
        Bukkit.getServer().broadcastMessage(prefix + ChatColor.RED + message);
    }

    public static void DeadWitness(Player p, Player w)
	{
		SendImportant(p, w.getDisplayName() + " is no longer a witness.");
	}

	public static void WitnessCrime(Crime.CrimeType type, Player p, Player s)
	{
		SendMessage(p,"You just witnessed " + s.getDisplayName() + " commit " + type.toString().toLowerCase());
		SendMessage(s,p.getDisplayName() + " just witnessed you commit " + type.toString().toLowerCase());
	}

    public static void Paid(Player source, Player target, double amount, String reason)
    {
        if(!(reason.contains("dying"))) SendMessage(source,"You just paid " + target.getName() + " " + FormatMoney(amount) + " for " + reason);
        SendMessage(target,source.getName() + " just paid you " + FormatMoney(amount) + " for " + reason);
    }

    public static void DenyConsole(CommandSender sender)
	{
		SendMessage(sender, "Console can't do this.");
	}

    public static void SwitchedJob(Player p, Career c)
	{
		SendMessage(p, "You are now a " + FormatCareer(c));

	}

	public static String FormatCareer(Career c)
	{
		return c.GetColor() + c.getName() + ChatColor.WHITE;
	}

	public static String FormatPluralCareer(Career c)
	{
		return c.GetColor() + c.getPluralName() + ChatColor.WHITE;
	}

	public static String FormatMoney(double amount)
	{
		return ChatColor.YELLOW + EconomyManager.Format(amount) + ChatColor.WHITE;
	}

    public static void NotifyPolice(String message)
	{
		for(Player p : Bukkit.getServer().getOnlinePlayers())
		{
			if(!Agency.getCareer(p).CanArrest()) continue;
            SendImportant(p, message);
		}
	}

}
