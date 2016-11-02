package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import com.griefcraft.lwc.LWC;
import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class LWCProtectionModule implements ProtectionModule {

    @Override
    public String getName() {
        return "LWC";
    }

    @Override
    public boolean canBuild(Player p, Block b) {
        return canAccess(p, b);
    }

    @Override
    public boolean canAccessChest(Player p, Block b) {
        return canAccess(p, b);
    }

    private boolean canAccess(Player p, Block b){
        if(!LWC.getInstance().isProtectable(b)) return true;

        return LWC.getInstance().getProtectionCache().getProtection(b) == null || LWC.getInstance().canAccessProtection(p, b);
    }
}
