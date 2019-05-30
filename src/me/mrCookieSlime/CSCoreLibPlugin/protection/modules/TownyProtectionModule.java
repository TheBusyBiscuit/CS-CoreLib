package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.object.TownyPermission.ActionType;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;

public class TownyProtectionModule implements ProtectionModule {

    @Override
    public boolean canBuild(Player player, Block b) {
    	//ActionType is DESTROY because other plugins mostly use canBuild function to check if the player has permission to destroy block.
    	//TODO: Split canBuild() into 2 functions (canBuild and canDestroy).
        return PlayerCacheUtil.getCachePermission(player, b.getLocation(), b.getType(), ActionType.DESTROY);
    }

    @Override
    public boolean canAccessChest(Player player, Block b) {
        return PlayerCacheUtil.getCachePermission(player, b.getLocation(), b.getType(), ActionType.SWITCH);
    }

    @Override
    public String getName() {
        return "Towny";
    }

}
