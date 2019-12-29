package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

@Deprecated
public class CustomPotion extends CustomItem {

	@Deprecated
	public CustomPotion(String name, int durability, String[] lore, PotionEffect effect) {
		super(Material.POTION, name, (ReflectionUtils.getVersion().startsWith("v1_8_")) ? durability: 0, lore);
		PotionMeta meta = (PotionMeta) getItemMeta();
		if (ReflectionUtils.getVersion().startsWith("v1_8_")) meta.setMainEffect(PotionEffectType.SATURATION);
		else meta.setMainEffect(effect.getType());
		meta.addCustomEffect(effect, true);
		setItemMeta(meta);
	}
	
	public CustomPotion(String name, PotionType type, PotionEffect effect, String... lore) {
		super(Material.POTION, name, lore);
		PotionMeta meta = (PotionMeta) getItemMeta();
		meta.setBasePotionData(new PotionData(type));
		meta.addCustomEffect(effect, true);
		setItemMeta(meta);
	}
	
	public CustomPotion(String name, Color color, PotionEffect effect, String... lore) {
		super(Material.POTION, name, lore);
		PotionMeta meta = (PotionMeta) getItemMeta();
		meta.setColor(color);
		meta.addCustomEffect(effect, true);
		setItemMeta(meta);
	}

}
