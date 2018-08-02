package me.mrCookieSlime.CSCoreLibPlugin.general.Chat;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

import org.bukkit.ChatColor;

public enum Colors {
	
	YELLOW(ChatColor.YELLOW),
	GREEN(ChatColor.GREEN),
	GOLD(ChatColor.GOLD),
	AQUA(ChatColor.AQUA),
	DARK_AQUA(ChatColor.DARK_AQUA),
	DARK_BLUE(ChatColor.DARK_BLUE);
	
	ChatColor color;
	
	Colors(ChatColor color) {
		this.color = color;
	}
	
	public static ChatColor getRandom() {
		return values()[CSCoreLib.randomizer().nextInt(values().length)].toChatColor();
	}
	
	public ChatColor toChatColor() {
		return color;
	}

}
