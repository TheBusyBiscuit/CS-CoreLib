package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldGuardProtectionModule implements ProtectionModule {

	private static WorldGuardPlugin worldguard = WorldGuardPlugin.inst();
	private static RegionContainer manager = WorldGuard.getInstance().getPlatform().getRegionContainer();
	private static RegionQuery query = manager.createQuery();
	
	public boolean canBuild(Player player, Block b) {
		Location loc = BukkitAdapter.adapt(b.getLocation());
	    LocalPlayer lp = worldguard.wrapPlayer(player);
		return query.testState(loc, lp, Flags.BUILD);
	}
	
	public boolean canAccessChest(Player player, Block b) {
		Location loc = BukkitAdapter.adapt(b.getLocation());
		LocalPlayer lp = worldguard.wrapPlayer(player);
		return query.testBuild(loc, lp, Flags.INTERACT, Flags.CHEST_ACCESS);
	}
	
	@Override
	public String getName() {
		return "WorldGuard";
	}
}