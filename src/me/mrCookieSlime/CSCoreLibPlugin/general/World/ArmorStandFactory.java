package me.mrCookieSlime.CSCoreLibPlugin.general.World;


import java.lang.reflect.Method;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class ArmorStandFactory {

	public static ArmorStand createHidden(Location l) {
		ArmorStand armorStand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		armorStand.setVisible(false);
		armorStand.setGravity(false);
		armorStand.setBasePlate(false);
		armorStand.setCustomNameVisible(true);
		armorStand.setRemoveWhenFarAway(false);
		try {
			Object nmsEntity = armorStand.getClass().getMethod("getHandle").invoke(armorStand);
			if (ReflectionUtils.getVersion().startsWith("v1_9_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bz", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", 2039583);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_10_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", true);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_11_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", true);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_12_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bC", true);
				}
			}
			else {
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
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
		return armorStand;
	}

	public static ArmorStand createSmall(Location l, ItemStack item, EulerAngle arm, float yaw) {
		l.setYaw(yaw);
		ArmorStand armorStand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		armorStand.getEquipment().setItemInMainHand(item);
		armorStand.setVisible(false);
		armorStand.setGravity(false);
		armorStand.setSmall(true);
		armorStand.setArms(true);
		armorStand.setRightArmPose(arm);
		armorStand.setBasePlate(false);
		armorStand.setRemoveWhenFarAway(false);
		try {
			Object nmsEntity = armorStand.getClass().getMethod("getHandle").invoke(armorStand);
			if (ReflectionUtils.getVersion().startsWith("v1_9_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bz", 2039583);
				} catch(IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", 2039583);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_10_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", true);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_11_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", true);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_12_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bC", true);
				}
			}
			else {
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
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
		return armorStand;
	}

	public static ArmorStand createSmall(Location l, ItemStack head, float yaw) {
		l.setYaw(yaw);
		ArmorStand armorStand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		armorStand.getEquipment().setHelmet(head);
		armorStand.setVisible(false);
		armorStand.setGravity(false);
		armorStand.setSmall(true);
		armorStand.setBasePlate(false);
		armorStand.setRemoveWhenFarAway(false);
		try {
			Object nmsEntity = armorStand.getClass().getMethod("getHandle").invoke(armorStand);
			if (ReflectionUtils.getVersion().startsWith("v1_9_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bz", 2039583);
				} catch(IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", 2039583);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_10_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", true);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_11_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bA", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", true);
				}
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_12_")) {
				try {
					ReflectionUtils.setFieldValue(nmsEntity, "bB", 2039583);
				} catch (IllegalArgumentException x) {
					ReflectionUtils.setFieldValue(nmsEntity, "bC", true);
				}
			}
			else {
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
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
		return armorStand;
	}

}
