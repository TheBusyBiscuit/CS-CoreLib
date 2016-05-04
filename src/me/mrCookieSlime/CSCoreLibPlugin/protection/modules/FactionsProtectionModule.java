package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;

public class FactionsProtectionModule implements ProtectionModule {

	@Override
	public boolean canBuild(Player player, Block b) {
		return !isProtected(player, b);
	}

	@Override
	public boolean canAccessChest(Player player, Block b) {
		return !isProtected(player, b);
	}
	
	private boolean isProtected(Player player, Block b) {
		Faction faction = BoardColl.get().getFactionAt(PS.valueOf(b));
		MPlayer mp = MPlayer.get(player);
		if (faction != null && !faction.getId().equals("none")) {
			if (!faction.getId().equals(mp.getFactionId())) return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "Factions";
	}

}
