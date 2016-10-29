package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import com.palmergames.bukkit.towny.object.PlayerCache;
import com.palmergames.bukkit.towny.object.WorldCoord;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

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
            PlayerCache.TownBlockStatus blockStatus = PlayerCacheUtil.getTownBlockStatus(player, coord);
            switch (blockStatus) {
                case PLOT_FRIEND:
                    return false;
                case PLOT_OWNER:
                    return false;
                case UNCLAIMED_ZONE:
                    return false;
                case TOWN_RESIDENT:
                    return false;
                case NOT_REGISTERED:
                    return false;
                default:
                    return true;
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
