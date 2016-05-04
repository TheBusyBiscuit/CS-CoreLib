package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownyUniverse;
import com.palmergames.bukkit.towny.object.WorldCoord;

public class TownyProtectionModule implements ProtectionModule {

	@Override
	public boolean canBuild(Player player, Block b) {
		return !isProtected(player, b);
	}

	@Override
	public boolean canAccessChest(Player player, Block b) {
		return !isProtected(player, b);
	}

	private boolean isProtected(Player player, Block b) {
		try {
			WorldCoord coord = WorldCoord.parseWorldCoord(b);
			if (coord.getTownyWorld().hasTownBlock(coord) && coord.getTownBlock().hasTown()) {
				Town town = coord.getTownBlock().getTown();
				Resident resident = TownyUniverse.getDataSource().getResident(player.getName());
				if (resident.hasTown()) {
					if (town.getUID() != resident.getTown().getUID()) return true;
				}
				else return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String getName() {
		return "Towny";
	}

}
