package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import net.sacredlabyrinth.Phaed.PreciousStones.field.FieldFlag;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * Created by John on 19.10.2016.
 */
public class PreciousStonesProtectionModule implements ProtectionModule {

    @Override
    public boolean canBuild(Player player, Block b) {
        return PreciousStones.API().canPlace(player, b.getLocation());
    }

    @Override
    public boolean canAccessChest(Player player, Block b) {
        return PreciousStones.API().isFieldProtectingArea(FieldFlag.PREVENT_USE, b.getLocation());
    }

    @Override
    public String getName() {
        return "PreciousStones";
    }
}
