package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class CustomBanner extends ItemStack {
	
	@SuppressWarnings("deprecation")
	public CustomBanner(DyeColor color, Pattern... patterns) {
		super(Material.WHITE_BANNER);
		
		BannerMeta meta = (BannerMeta) getItemMeta();
		
		meta.setBaseColor(color);
		
		for (Pattern pattern: patterns) {
			meta.addPattern(pattern);
		}
		
		setItemMeta(meta);
	}

}
