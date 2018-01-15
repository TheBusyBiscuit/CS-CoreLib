package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.config.C;
import com.intellectualcrafters.plot.object.PlotPlayer;
import com.intellectualcrafters.plot.util.Permissions;
import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;


public class PlotSquaredProtectionModule implements ProtectionModule {
	
	@Override
	public boolean canBuild(Player player, Block b) {
		return Permissions.hasPermission(PlotPlayer.wrap(player), C.PERMISSION_ADMIN_BUILD_UNOWNED);
	}
	
	@Override
	public boolean canAccessChest(Player player, Block b) {
		PlotPlayer pp = PlotPlayer.wrap(player);
		PlotAPI api = new PlotAPI();
		if(api.getPlot(player).isAdded(pp.getUUID()) || Permissions.hasPermission(pp, C.PERMISSION_ADMIN_INTERACT_OTHER)) {
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "PlotSquared";
	}
}
	