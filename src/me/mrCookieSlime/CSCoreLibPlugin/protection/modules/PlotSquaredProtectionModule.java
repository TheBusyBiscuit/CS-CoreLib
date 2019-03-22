package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.github.intellectualsites.plotsquared.plot.config.Captions;
import com.github.intellectualsites.plotsquared.plot.object.Location;
import com.github.intellectualsites.plotsquared.plot.object.Plot;
import com.github.intellectualsites.plotsquared.plot.object.PlotPlayer;
import com.github.intellectualsites.plotsquared.plot.util.Permissions;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;


public class PlotSquaredProtectionModule implements ProtectionModule {
	private boolean hasAccess(Player player, Block b) {
		Plot plot = new Location(b.getWorld().getName(), b.getX(), b.getY(), b.getZ()).getOwnedPlot();
		return plot == null || plot.isAdded(player.getUniqueId());
	}
	@Override
	public boolean canBuild(Player player, Block b) {
		return hasAccess(player, b) || Permissions.hasPermission(PlotPlayer.wrap(player), Captions.PERMISSION_ADMIN_BUILD_UNOWNED);
	}
	
	@Override
	public boolean canAccessChest(Player player, Block b) {
		return hasAccess(player,b) || Permissions.hasPermission(PlotPlayer.wrap(player), Captions.PERMISSION_ADMIN_INTERACT_UNOWNED);
	}

	@Override
	public String getName() {
		return "PlotSquared";
	}
}
	
