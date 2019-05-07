package me.mrCookieSlime.CSCoreLibPlugin.protection.modules;

import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionModule;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.lists.Flags;

import java.util.Optional;

/**
 * Provides protection handling using the BentoBox API.
 * @author Poslovitch
 */
public class BentoBoxProtectionModule implements ProtectionModule {

    @Override
    public boolean canBuild(Player player, Block b) {
        return canInteract(player, b, Flags.PLACE_BLOCKS) || canInteract(player, b, Flags.BREAK_BLOCKS);
    }

    @Override
    public boolean canAccessChest(Player player, Block b) {
        return canInteract(player, b, Flags.CONTAINER);
    }

    /**
     * Returns whether this player can interact with this block in accordance with this flag.
     * @param player the Player who is trying to interact.
     * @param block the Block the player is trying to interact with.
     * @param flag the {@link Flag} to check.
     * @return {@code true} if this player can interact with this block, {@code false} otherwise.
     */
    private boolean canInteract(Player player, Block block, Flag flag) {
        Optional<Island> island = BentoBox.getInstance().getIslands().getIslandAt(block.getLocation());
        return island.map(value -> value.isAllowed(User.getInstance(player), flag)).orElse(false);
    }

    @Override
    public String getName() {
        return "BentoBox";
    }
}
