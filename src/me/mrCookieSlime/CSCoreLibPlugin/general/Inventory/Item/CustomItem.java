package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class CustomItem extends ItemStack {
	
	public CustomItem(ItemStack item) {
		super(item);
	}
	
	public CustomItem(Material type, String name, int durability, List<String> lore) {
		super(new ItemStack(type));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		im.setLore(lore);
		setItemMeta(im);
		setDurability((short) durability);
	}
	
	public CustomItem(Material type, String name, int durability, String[] lore) {
		super(new ItemStack(type));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lines = new ArrayList<String>();
		for (String line: lore) {
			lines.add(ChatColor.translateAlternateColorCodes('&', line));
		}
		im.setLore(lines);
		setItemMeta(im);
		setDurability((short) durability);
	}
	
	public CustomItem(Material type, String name, int durability, String[] lore, String[] enchantments) {
		super(new ItemStack(type));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lines = new ArrayList<String>();
		for (String line: lore) {
			lines.add(ChatColor.translateAlternateColorCodes('&', line));
		}
		im.setLore(lines);
		setItemMeta(im);
		setDurability((short) durability);
		for (String ench: enchantments) {
			addUnsafeEnchantment(Enchantment.getByName(ench.split("-")[0]), Integer.parseInt(ench.split("-")[1]));
		}
	}
	
	public CustomItem(ItemStack item, String[] enchantments) {
		super(item);
		for (String ench: enchantments) {
			addUnsafeEnchantment(Enchantment.getByName(ench.split("-")[0]), Integer.parseInt(ench.split("-")[1]));
		}
	}
	
	public CustomItem(Material type, String name, String[] enchantments, int durability) {
		super(new ItemStack(type));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		setItemMeta(im);
		setDurability((short) durability);
		for (String ench: enchantments) {
			addUnsafeEnchantment(Enchantment.getByName(ench.split("-")[0]), Integer.parseInt(ench.split("-")[1]));
		}
	}
	
	public CustomItem(Material type, String name, int durability) {
		super(new ItemStack(type));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		setItemMeta(im);
		setDurability((short) durability);
	}
	
	public CustomItem(ItemStack item, String name) {
		super(item);
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		setItemMeta(im);
	}
	
	public CustomItem(ItemStack item, String name, String... lore) {
		super(item);
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lines = new ArrayList<String>();
		for (String line: lore) {
			lines.add(ChatColor.translateAlternateColorCodes('&', line));
		}
		im.setLore(lines);
		setItemMeta(im);
	}
	
	public CustomItem(Material type, int durability) {
		super(new ItemStack(type));
		setDurability((short) durability);
	}
	
	public CustomItem(Material type, int durability, int amount) {
		super(new ItemStack(type, amount));
		setDurability((short) durability);
	}
	
	public CustomItem(ItemStack item, int amount) {
		super(item.clone());
		setAmount(amount);
	}
	
	public CustomItem(Material type, String name, int durability, int amount, List<String> lore) {
		super(new ItemStack(type, amount));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		im.setLore(lore);
		setItemMeta(im);
		setDurability((short) durability);
	}
	
	public CustomItem(Material type, String name, String... lore) {
		this(new ItemStack(type), name, lore);
	}
	
	public CustomItem(MaterialData data, String name, String... lore) {
		super(data.toItemStack(1));
		ItemMeta im = getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lines = new ArrayList<String>();
		for (String line: lore) {
			lines.add(ChatColor.translateAlternateColorCodes('&', line));
		}
		im.setLore(lines);
		setItemMeta(im);
	}
	
	public CustomItem(MaterialData data, int amount) {
		super(data.toItemStack(amount));
	}
	
	public List<String> getLore() {
		if (!hasItemMeta()) return new ArrayList<String>();
		else if (!getItemMeta().hasLore()) return new ArrayList<String>();
		else return getItemMeta().getLore();
	}
	
	public String getDisplayName() {
		if (!hasItemMeta()) return "";
		else if (!getItemMeta().hasDisplayName()) return "";
		else return getItemMeta().getDisplayName();
	}
	
	public boolean hasEnchantment(Enchantment enchantment) {
		return getEnchantments().containsKey(enchantment);
	}
	
	public int getEnchantmentLevel(Enchantment enchantment){
		return hasEnchantment(enchantment) ? getEnchantmentLevel(enchantment):0;
	}
	
	public static void addEnchantmentGlow(ItemStack item) {
		
	}

}
