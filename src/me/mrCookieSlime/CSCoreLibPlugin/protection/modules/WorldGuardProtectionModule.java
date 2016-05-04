package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

public class WorldGuardProtectionModule implements ProtectionModule {

	public boolean canBuild(Player player, Block b) {
		return WGBukkit.getPlugin().canBuild(player, b);
	}
	
	public boolean canAccessChest(Player player, Block b) {
		RegionQuery query = WGBukkit.getPlugin().getRegionContainer().createQuery();
		return query.testBuild(b.getLocation(), player, DefaultFlag.INTERACT, DefaultFlag.CHEST_ACCESS);
	}

	@Override
	public String getName() {
		return "WorldGuard";
	}

}
