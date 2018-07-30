package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GriefPreventionProtectionModule implements ProtectionModule {
	
	@Override
	public boolean canBuild(Player player, Block b) {
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(b.getLocation(), true, null);
		if (claim != null) {
			return claim.allowBreak(player, b.getType()) == null;
		}
		return true;
	}

	@Override
	public boolean canAccessChest(Player player, Block b) {
		Claim claim = GriefPrevention.instance.dataStore.getClaimAt(b.getLocation(), true, null);
		if (claim != null) {
			return claim.allowContainers(player) == null;
		}
		return true;
	}

	@Override
	public String getName() {
		return "GriefPrevention";
	}

}
