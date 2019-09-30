package me.mrCookieSlime.CSCoreLibPlugin.general.Block;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

@Deprecated
public class Vein {
	
	private static final BlockFace[] faces = new BlockFace[] {BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH_EAST, BlockFace.NORTH_WEST, BlockFace.SOUTH_EAST, BlockFace.SOUTH_WEST};
	
	public static void calculate(Location origin, Location anchor, List<Location> list, int max) {
		if (list.size() > max) return;
		
		for (BlockFace face: faces) {
			Block next = anchor.getBlock().getRelative(face);
			if ((next.getType() == anchor.getBlock().getType() || (next.getType().toString().endsWith("REDSTONE_ORE") && anchor.getBlock().getType().toString().endsWith("REDSTONE_ORE"))) && !list.contains(next.getLocation())) {
				list.add(next.getLocation());
				calculate(origin, next.getLocation(), list, max);
			}
		}
	}

}
