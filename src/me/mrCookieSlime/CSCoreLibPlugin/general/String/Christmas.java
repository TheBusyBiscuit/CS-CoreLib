package me.mrCookieSlime.CSCoreLibPlugin.general.String;

import org.bukkit.ChatColor;

public class Christmas {
	
	public static String color(String string) {
		StringBuilder xmas = new StringBuilder("");
		for (int i = 0; i < string.length(); i++) {
			xmas.append((i % 2 == 0 ? "&a": "&c"));
			xmas.append(string.charAt(i));
		}
		return ChatColor.translateAlternateColorCodes('&', xmas.toString());
	}

}
