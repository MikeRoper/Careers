package org.blockface.Careers.Managers;

import com.nijikokun.register.payment.Method;
import com.nijikokun.register.payment.Methods;
import com.palmergames.bukkit.towny.IConomyException;
import com.palmergames.bukkit.towny.object.Town;
import org.blockface.Careers.Util.Chatty;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class EconomyManager
{
    private static Methods methods = new Methods();

    public static void LoadMethods(Plugin p)
    {
        if(methods.hasMethod()) return;
        if(!methods.setMethod(p)) return;
        Chatty.Info("Detected and using " + methods.getMethod().getName() + " for economy.");
    }

    public static Boolean PlayerPay(Player source, Player target, double amount, String reason)
    {
        Method.MethodAccount srcacc = GetAccount(source);
        Method.MethodAccount trgacc = GetAccount(target);
        if(!srcacc.hasEnough(amount))
        {
            Chatty.SendMessage(source,"You do not have enough money.");
            return false;
        }
        srcacc.subtract(amount);
        trgacc.add(amount);
        Chatty.Paid(source,target,amount,reason);
        return true;
    }

    public static Boolean PayWage(Player player, double amount)
    {
        Town t = TownyManager.GetTown(player);
        try {
            if(t==null || t.getHoldingBalance() < amount)
            {
                Chatty.Bankrupt(player);
                return false;
            }
        } catch (IConomyException e) {
            Chatty.Bankrupt(player);
            return false;
        }
        Method.MethodAccount srcacc = GetAccount(t.getIConomyName());
        Method.MethodAccount trgacc = GetAccount(player);
        srcacc.subtract(amount);
        trgacc.add(amount);
        Chatty.EarnedWage(player,amount);
        return true;

    }

    public static Method.MethodAccount GetAccount(Player player)
    {
        return methods.getMethod().getAccount(player.getName());
    }

    public static Method.MethodAccount GetAccount(String account)
    {
        return methods.getMethod().getAccount(account);
    }

    public static String Format(double amount)
    {
        return methods.getMethod().format(amount);
    }
}
