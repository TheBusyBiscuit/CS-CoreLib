package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import java.util.UUID;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;

public class ASkyBlockProtectionModule implements ProtectionModule {
	
	@Override
	public boolean canBuild(Player player, Block b) {
		return !isProtected(player, b);
	}

	@Override
	public boolean canAccessChest(Player player, Block b) {
		return !isProtected(player, b);
	}
	
	private boolean isProtected(Player player, Block b) {
		Island island = ASkyBlockAPI.getInstance().getIslandAt(b.getLocation());
		
		if (island == null) return false;
		
		if (player.getUniqueId().equals(island.getOwner())) return false;
		
		for (UUID member: island.getMembers()) {
			if (player.getUniqueId().equals(member)) return false;
		}
		
		return true;
	}

	@Override
	public String getName() {
		return "ASkyBlock";
	}

}
