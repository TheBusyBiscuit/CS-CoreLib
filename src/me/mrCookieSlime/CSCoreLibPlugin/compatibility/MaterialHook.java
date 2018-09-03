package me.mrCookieSlime.CSCoreLibPlugin.compatibility;

import org.bukkit.Material;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

public class MaterialHook {
	
	public static Material parse(String name, String legacy) {
		if (ReflectionUtils.isVersion("v1_12_", "v1_11_", "v1_10_", "v1_9_")) {
			return Material.valueOf(legacy);
		}
		else {
			return Material.valueOf(name);
		}
	}

}
