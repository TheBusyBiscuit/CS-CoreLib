package me.mrCookieSlime.CSCoreLibPlugin.general.World;

import java.lang.reflect.Field;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.CraftObject;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.PackageName;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

public class Animals {
	
	private static Field field;
	
	static {
		try {
			field = ReflectionUtils.tryField(ReflectionUtils.getClass(PackageName.NMS, "EntityAnimal"), "bC", "bv", "bw","bx", "loveTicks");
			field.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean feed(Entity n) throws Exception {
		if (!isFeedable(n)) return false;
		Object handle = ReflectionUtils.getHandle(CraftObject.ANIMALS, n);
		field.set(handle, 600);
		return true;
	}
	
	public static boolean isFeedable(Entity n) throws Exception {
		if (!(n instanceof org.bukkit.entity.Animals && ((Ageable) n).isAdult() && ((Ageable) n).canBreed())) return false;
		
		Object handle = ReflectionUtils.getHandle(CraftObject.ANIMALS, n);
		return field.getInt(handle) < 1;
	}

}
