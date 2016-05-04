package me.mrCookieSlime.CSCoreLibPlugin.general.World;

import java.lang.reflect.Field;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.CraftObject;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.PackageName;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

public class Animals {
	
	public static boolean feed(Entity n) throws Exception {
		if (!isFeedable(n)) return false;
		Object handle = ReflectionUtils.getHandle(CraftObject.ANIMALS, n);
		Field f = ReflectionUtils.getField(ReflectionUtils.getClass(PackageName.NMS, "EntityAnimal"), "bv");
		f.setAccessible(true);
		f.set(handle, 600);
		return true;
	}
	
	public static boolean isFeedable(Entity n) throws Exception {
		if (!(n instanceof org.bukkit.entity.Animals && ((Ageable) n).isAdult() && ((Ageable) n).canBreed())) return false;
		
		Object handle = ReflectionUtils.getHandle(CraftObject.ANIMALS, n);
		Field f = ReflectionUtils.getField(ReflectionUtils.getClass(PackageName.NMS, "EntityAnimal"), "bv");
		f.setAccessible(true);
		return f.getInt(handle) < 1;
	}

}
