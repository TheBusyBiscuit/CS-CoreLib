package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item;

import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import me.mrCookieSlime.CSCoreLibPlugin.compatibility.MaterialHook;

@Deprecated
public class CustomBanner extends ItemStack {
	
	public CustomBanner(DyeColor color, Pattern... patterns) {
		super(MaterialHook.parse("WHITE_BANNER", "BANNER"));
		
		BannerMeta meta = (BannerMeta) getItemMeta();
		
		meta.setBaseColor(color);
		
		for (Pattern pattern: patterns) {
			meta.addPattern(pattern);
		}
		
		setItemMeta(meta);
	}

}
