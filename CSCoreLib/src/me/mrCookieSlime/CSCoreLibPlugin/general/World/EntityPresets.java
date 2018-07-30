package me.mrCookieSlime.CSCoreLibPlugin.general.World;

import java.lang.reflect.Method;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class EntityPresets {

	public static ArmorStand createHiddenArmorstand(Location l) {
		ArmorStand armorstand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		armorstand.setVisible(false);
		armorstand.setGravity(false);
		armorstand.setBasePlate(false);
		try {
			Object nmsEntity = armorstand.getClass().getMethod("getHandle").invoke(armorstand);
            Method method = nmsEntity.getClass().getMethod("getNBTTag");
            Object tag = method.invoke(nmsEntity);
            if(tag == null) {
                tag = ReflectionUtils.getClass("NBTTagCompound").newInstance();
            }
            method = nmsEntity.getClass().getMethod("c", ReflectionUtils.getClass("NBTTagCompound"));
            method.invoke(nmsEntity, tag);
			
            tag.getClass().getMethod("setBoolean", String.class, boolean.class).invoke(tag, "Invulnerable", true);
            nmsEntity.getClass().getMethod("f", ReflectionUtils.getClass("NBTTagCompound")).invoke(nmsEntity, tag);
            
            tag.getClass().getMethod("setInt", String.class, int.class).invoke(tag, "DisabledSlots", 2039583);
            nmsEntity.getClass().getMethod("a", ReflectionUtils.getClass("NBTTagCompound")).invoke(nmsEntity, tag);
        } catch (Exception x) {
            x.printStackTrace();
        }
		return armorstand;
	}
}
