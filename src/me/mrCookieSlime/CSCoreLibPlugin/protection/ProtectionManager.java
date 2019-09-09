package me.mrCookieSlime.CSCoreLibPlugin.protection;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;

import io.github.thebusybiscuit.cscorelib2.protection.ProtectionModule.Action;
import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

@Deprecated
public class ProtectionManager {
	
	private io.github.thebusybiscuit.cscorelib2.protection.ProtectionManager manager;

	public ProtectionManager() {}
	
	public ProtectionManager(CSCoreLib plugin) {
		manager = new io.github.thebusybiscuit.cscorelib2.protection.ProtectionManager(plugin.getServer());
	}
	
	public boolean canBuild(UUID uuid, Block b) {
		return this.canBuild(uuid, b, false);
	}

	public boolean canAccessChest(UUID uuid, Block b) {
		return this.canAccessChest(uuid, b, false);
	}

	public boolean canBuild(UUID uuid, Block b, boolean message) {
		if (manager == null) return false;
		
		return manager.hasPermission(Bukkit.getOfflinePlayer(uuid), b.getLocation(), Action.PLACE_BLOCK);
	}

	public boolean canAccessChest(UUID uuid, Block b, boolean message) {
		if (manager == null) return false;
		
		return manager.hasPermission(Bukkit.getOfflinePlayer(uuid), b.getLocation(), Action.ACCESS_INVENTORIES);
	}

}
