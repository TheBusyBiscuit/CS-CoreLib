package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.yi.acru.bukkit.Lockette.Lockette;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;

public class LocketteProtectionModule implements ProtectionModule{

	@Override
	public boolean canBuild(Player player, Block b) {
		return !isProtected(player, b);
	}

	@Override
	public boolean canAccessChest(Player player, Block b) {
		return !isProtected(player, b);
	}
	
	public boolean isProtected(Player player, Block b){
		if(Lockette.isProtected(b)) {
			// Signs need to be treated individually
			if (b.getState() instanceof Sign) {
				return !Lockette.isOwner((Sign) b.getState(), player);
			}
			else {
				return !Lockette.isOwner(b, player);
			}
		}
		else return false;
	}

	@Override
	public String getName() {
		return "Lockette";
	}

}
