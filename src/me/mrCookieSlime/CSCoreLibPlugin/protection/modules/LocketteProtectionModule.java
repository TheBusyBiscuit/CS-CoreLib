package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.yi.acru.bukkit.Lockette.Lockette;

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
		if(Lockette.isProtected(b)){
			if(b.getType() == Material.WALL_SIGN){
				//The check is placed because Lockette did not check if the block itself is a sign.
				Lockette.isOwner((Sign) b, player);
			}else if(Lockette.isOwner(b, player)){
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "Lockette";
	}

}
