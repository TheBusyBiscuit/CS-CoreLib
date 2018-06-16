package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;

public class FactionsUUIDProtectionModule implements ProtectionModule {

	@Override
	public boolean canBuild(Player player, Block b) {
		return !isProtected(player, b);
	}

	@Override
	public boolean canAccessChest(Player player, Block b) {
		return !isProtected(player, b);
	}
	
	private boolean isProtected(Player player, Block b) {
		FLocation fLoc = new FLocation(b.getLocation());
		Faction faction = Board.getInstance().getFactionAt(fLoc);
		FPlayer fPlayer = FPlayers.getInstance().getByPlayer(player);
		
		if (faction != null && !faction.getId().equals("none")) {
			if (!faction.getId().equals(fPlayer.getFaction().getId())) return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "Factions";
	}

}
