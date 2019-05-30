package me.mrCookieSlime.CSCoreLibPlugin.general.String;

import java.lang.reflect.Method;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.PackageName;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.inventory.ItemStack;

public class StringUtils {
	
	private static Method copy, getName, toString;
	
	static {
		try {
			copy = ReflectionUtils.getClass(PackageName.OBC, "inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack.class);
			getName = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.NMS, "ItemStack"), "getName");
			
			if (ReflectionUtils.isVersion("v1_13_", "v1_14_")) {
				toString = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.NMS, "IChatBaseComponent"), "getString");
			}
		}
		catch(Exception x) {
			x.printStackTrace();
		}
	}
	
	public static String formatItemName(ItemStack item, boolean includePlural) {
		String name = item.getType().toString();
		try {
			Object instance = copy.invoke(null, item);
			
			if (toString == null) {
				name = (String) getName.invoke(instance);
			}
			else {
				name = (String) toString.invoke(getName.invoke(instance));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) name = item.getItemMeta().getDisplayName();
		if (includePlural) name = item.getAmount() + " " + name + "/s";
		return name;
	}
	
	public static String format(String string) {
		string = string.toLowerCase();
		StringBuilder builder = new StringBuilder();
		int i = 0;
		for (String s: string.split("_")) {
			if (i == 0) builder.append(Character.toUpperCase(s.charAt(0)) + s.substring(1));
			else builder.append(" " + Character.toUpperCase(s.charAt(0)) + s.substring(1));
			i++;
		}
		return builder.toString();
	}
	
	public static boolean contains(String string, String... contain) {
		for (String s: contain) {
			if (string.contains(s)) return true;
		}
		return false;
	}
	
	public static boolean equals(String string, String... equal) {
		for (String s: equal) {
			if (string.equals(s)) return true;
		}
		return false;
	}
	
	public static boolean endsWith(String string, String... end) {
		for (String s: end) {
			if (string.endsWith(s)) return true;
		}
		return false;
	}

}
