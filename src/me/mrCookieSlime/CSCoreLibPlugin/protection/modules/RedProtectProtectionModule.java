package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import br.net.fabiozumbi12.RedProtect.Bukkit.API.RedProtectAPI;
import br.net.fabiozumbi12.RedProtect.Bukkit.RedProtect;
import br.net.fabiozumbi12.RedProtect.Bukkit.Region;
import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class RedProtectProtectionModule implements ProtectionModule {

    // Last edited by DrOreo002, 4/10/2018
    @Override
    public boolean canBuild(Player player, Block b) {
        return isProtected(player, b);
    }

    @Override
    public boolean canAccessChest(Player player, Block b) {
        RedProtectAPI api = RedProtect.get().getAPI();
        Region region = api.getRegion(b.getLocation());
        // Can build if there's no region
        if (region == null) return true;
        return region.canChest(player);
    }

    private boolean isProtected(Player player, Block b) {
        RedProtectAPI api = RedProtect.get().getAPI();
        Region region = api.getRegion(b.getLocation());
        // Can build if there's no region
        if (region == null) return true;
        return region.canBuild(player);
    }

    @Override
    public String getName() {
        return "RedProtect";
    }
}
