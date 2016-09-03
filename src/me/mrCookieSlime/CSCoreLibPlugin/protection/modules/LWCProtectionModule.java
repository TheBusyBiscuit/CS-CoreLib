package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import com.griefcraft.lwc.LWC;
import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created by John on 03.09.2016.
 */
public class LWCProtectionModule implements ProtectionModule {
    @Override
    public String getName() {
        return "LWC";
    }

    @Override
    public boolean canBuild(Player p, Block b) {
        return isProtected(p, b);
    }

    @Override
    public boolean canAccessChest(Player p, Block b) {
        return isProtected(p, b);
    }

    private boolean isProtected(Player p, Block b){
        if(LWC.getInstance().isProtectable(b)){
            return LWC.getInstance().canAccessProtection(p, b);
        }
        return true;
    }
}
