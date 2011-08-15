package org.blockface.Careers.Managers;


import com.palmergames.bukkit.towny.NotRegisteredException;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.*;
import org.blockface.Careers.Employment.Career;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TownyManager
{
    private static Towny towny = null;
    private static TownyUniverse universe;

    public static void LoadTowny(Plugin p)
    {
        towny = (Towny)p;
        universe = towny.getTownyUniverse();
        Chatty.Info("Towny detected and loaded.");
    }

    public static Boolean AreAllies(Player a, Player b)
    {
        try
        {
            Resident ra = universe.getResident(a.getName());
            Resident rb = universe.getResident(b.getName());
            //Friends Check
            if(ra.hasFriend(rb)) return true;
            //Town Check
            if(ra.getTown().equals(rb.getTown())) return true;
            //Nation Check
            if(ra.getTown().getNation().equals(rb.getTown().getNation())) return true;
        }
        catch(Exception ex) {}
        return false;
    }

    public static void SetTitle(Player a, Career c)
    {
        try
        {
            universe.getResident(a.getName()).setTitle(c.GetColor() + c.getName() + ChatColor.WHITE + " ");
        }
        catch (Exception ex) {ex.printStackTrace();}
    }

    public static Town GetTown(Player p)
    {
        try {
            return universe.getResident(p.getName()).getTown();
        } catch (NotRegisteredException e) {
            return null;
        }
    }

    public static Boolean IsOutsideHome(Player p)
    {
        try
        {
            Town home = GetTown(p);
            if(home==null) return true;
            TownyWorld tw = universe.getWorld(p.getWorld().getName());
            TownBlock tb = tw.getTownBlock(Coord.parseCoord(p));
            if(tb.getTown() == home) return false;
        }
        catch (Exception ex) { return true;}
        return true;

    }
}

