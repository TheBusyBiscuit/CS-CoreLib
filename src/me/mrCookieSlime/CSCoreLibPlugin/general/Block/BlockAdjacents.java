package me.mrCookieSlime.CSCoreLibPlugin.general.Block;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

@Deprecated
public class BlockAdjacents {
	
	public static Block[] getAdjacentBlocks(Block b) {
		return new Block[] {b.getRelative(BlockFace.UP), b.getRelative(BlockFace.DOWN), b.getRelative(BlockFace.NORTH), b.getRelative(BlockFace.EAST), b.getRelative(BlockFace.SOUTH), b.getRelative(BlockFace.WEST)};
	}
	
	public static boolean hasAdjacentMaterial(Block b, Material m) {
		for (Block block: getAdjacentBlocks(b)) {
			if (block.getType() == m) return true;
		}
		return false;
	}
	
	public static boolean hasMaterialOnBothSides(Block b, Material m) {
		if (b.getRelative(BlockFace.NORTH).getType() == m && b.getRelative(BlockFace.SOUTH).getType() == m) return true;
		else if (b.getRelative(BlockFace.EAST).getType() == m && b.getRelative(BlockFace.WEST).getType() == m) return true;
		else return false;
	}
	
	public static boolean hasMaterialOnAllSides(Block b, Material m) {
		Block[] adjacents = getAdjacentBlocks(b);
		if (adjacents[2].getType() == m && adjacents[3].getType() == m && adjacents[4].getType() == m && adjacents[5].getType() == m) return true;
		else return false;
	}
	
	public static boolean isMaterial(Block b, Material m) {
		return m == null ? true: m == b.getType();
	}
	
	public static boolean hasMaterialOnSide(Block b, Material m) {
		if (m == null) return true;
		else {
			Block[] adjacents = getAdjacentBlocks(b);
			
			if (adjacents[2].getType() == m || adjacents[3].getType() == m || adjacents[4].getType() == m || adjacents[5].getType() == m) return true;
			else return false;
		}
	}
	
	public static boolean hasMaterialOnTop(Block b, Material m) {
		return b.getRelative(BlockFace.UP).getType() == m;
	}

}
