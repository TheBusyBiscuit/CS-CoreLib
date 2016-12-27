package me.mrCookieSlime.CSCoreLibPlugin.protection;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public interface ProtectionModule {
	
	public boolean canBuild(Player player, Block b);
	
	public boolean canAccessChest(Player player, Block b);
	public String getName();

}
