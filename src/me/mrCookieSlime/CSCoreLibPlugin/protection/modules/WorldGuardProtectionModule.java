package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldGuardProtectionModule implements ProtectionModule {

	private static WorldGuardPlugin worldguard = WorldGuardPlugin.inst();
	
	public boolean canBuild(Player player, Block b) {
		return worldguard.createProtectionQuery().testBlockBreak(player, b);
	}
	
	public boolean canAccessChest(Player player, Block b) {
		return worldguard.createProtectionQuery().testBlockInteract(player, b);
	}
	
	@Override
	public String getName() {
		return "WorldGuard";
	}
}