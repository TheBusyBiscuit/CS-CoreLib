package me.mrCookieSlime.CSCoreLibPlugin.general.World;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

@Deprecated
public class FakeItem {
	
	public static void spawn(Location location, ItemStack itemstack, long ticks) {
		final Item item = location.getWorld().dropItem(location, itemstack);
		item.setPickupDelay(Integer.MAX_VALUE);
		Bukkit.getScheduler().scheduleSyncDelayedTask(CSCoreLib.getLib(), new Runnable() {
			
			@Override
			public void run() {
				if (item != null) item.remove();
			}
		}, ticks);
	}
	
	@Deprecated
	public static void spawn(Location location, MaterialData type, long ticks) {
		final Item item = location.getWorld().dropItem(location, type.toItemStack(1));
		item.setPickupDelay(Integer.MAX_VALUE);
		Bukkit.getScheduler().scheduleSyncDelayedTask(CSCoreLib.getLib(), new Runnable() {
			
			@Override
			public void run() {
				if (item != null) item.remove();
			}
		}, ticks);
	}

}
