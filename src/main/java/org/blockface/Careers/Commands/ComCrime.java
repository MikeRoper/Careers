package org.blockface.Careers.Commands;

import org.blockface.Careers.Managers.CrimeManager;
import org.blockface.Careers.Managers.JailManager;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComCrime
{
    private static Server server = Bukkit.getServer();
    public static void Execute(CommandSender sender, String[] args)
    {
        if(args.length < 1) return;
        String base = args[0];
        if(base.equalsIgnoreCase("setjail")) SetJail(sender);
        else if(base.equalsIgnoreCase("sentence"));
        else Report(sender,base);


    }

    private static void Report(CommandSender sender, String target)
    {
        if(!(sender instanceof Player))
        {
            Chatty.DenyConsole(sender);
            return;
        }
        Player player = (Player)sender;
		if(CrimeManager.GetWitness(player).ReportCrime(target))
		{
			Chatty.SendImportant(player,"Police are now looking for the criminal.");
			return;
		}
        Chatty.SendImportant(player,target + " did not commit a crime.");
    }

    private static void SetJail(CommandSender sender)
    {
        if(!(sender instanceof Player))
        {
            Chatty.DenyConsole(sender);
            return;
        }
        Player player = (Player)sender;
        JailManager.AddJail(((Player) sender).getLocation());
        Chatty.SendMessage(player,"Jail set for world: " + player.getWorld().getName());
    }

    private static void CheckSentence(CommandSender sender)
    {
        return;
    }
}
