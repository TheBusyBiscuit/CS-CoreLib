package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullItem extends ItemStack {
	
	String owner;
	
	public SkullItem(String name, String owner) {
		super(new ItemStack(Material.PLAYER_HEAD));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		((SkullMeta) im).setOwner(owner);
		setItemMeta(im);
		this.owner = owner;
	}
	
	public SkullItem(String owner) {
		super(new ItemStack(Material.PLAYER_HEAD));
		ItemMeta im = getItemMeta();
		((SkullMeta) im).setOwner(owner);
		setItemMeta(im);
		this.owner = owner;
	}
	
	public SkullItem(String name, String owner, String... lore) {
		super(new ItemStack(Material.PLAYER_HEAD));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lines = new ArrayList<String>();
		for (String line: lore) {
			lines.add(ChatColor.translateAlternateColorCodes('&', line));
		}
		im.setLore(lines);
		((SkullMeta) im).setOwner(owner);
		setItemMeta(im);
		this.owner = owner;
	}
	
	public String getOwner() {
		return this.owner;
	}

}
