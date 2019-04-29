package me.mrCookieSlime.CSCoreLibPlugin.general.World;


import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

public class ArmorStandFactory {

	public static ArmorStand createHidden(Location l) {
		ArmorStand armorStand = (ArmorStand) l.getWorld().spawnEntity(l, EntityType.ARMOR_STAND);
		armorStand.setVisible(false);
		armorStand.setGravity(false);
		armorStand.setBasePlate(false);
		armorStand.setCustomNameVisible(true);
		armorStand.setRemoveWhenFarAway(false);
		
		applyNMSTraits(armorStand);
		
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
		
		applyNMSTraits(armorStand);
		
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
		
		applyNMSTraits(armorStand);
		
		return armorStand;
	}
	
	private static void applyNMSTraits(ArmorStand armorStand) {
		try {
			Object nmsEntity = armorStand.getClass().getMethod("getHandle").invoke(armorStand);
			
			try {
				ReflectionUtils.setFieldValue(nmsEntity, "bH", 2039583);
			} catch (IllegalArgumentException x) {
				ReflectionUtils.setFieldValue(nmsEntity, "bI", true);
			}
			
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

}
