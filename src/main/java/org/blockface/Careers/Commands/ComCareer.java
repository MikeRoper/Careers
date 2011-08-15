package org.blockface.Careers.Commands;

import org.blockface.Careers.Employment.Agency;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ComCareer
{

    public static void Execute(CommandSender sender, String[] args)
    {
        if(args.length==0) return;
        String base = args[0];
        if(!sender.hasPermission("Careers.employ"))
        {
           return;
        }
        if(args.length == 2 && base.equalsIgnoreCase("set")) SetSelf(sender, args);
        if(args.length == 3 && base.equals("set")) SetOther(sender,args);

    }

    private static Player MatchPlayer(String player)
    {
        player = player.toLowerCase();
        for(Player p : Bukkit.getServer().getOnlinePlayers())
        {
            if(p.getName().toLowerCase().contains(player)) return p;
        }
        return null;
    }

    private static void SetSelf(CommandSender sender, String[] args)
    {
        if(!(sender instanceof Player))
        {
            Chatty.DenyConsole(sender);
            return;
        }
        Agency.setCareer((Player)sender,args[1]);
    }

    private static void SetOther(CommandSender sender, String[] args)
    {
        Player player = MatchPlayer(args[1]);
        if(player==null) Chatty.SendMessage(sender,"No player found.");
        Agency.setCareer(player,args[2]);
        Chatty.SendMessage(sender,"Career set for " + player.getDisplayName());
    }

}
