package me.mrCookieSlime.CSCoreLibPlugin.general.Player;

import org.bukkit.Bukkit;

@Deprecated
public class Players {
	
	public static boolean isOnline(String name) {
		return Bukkit.getPlayer(name) != null;
	}
}
