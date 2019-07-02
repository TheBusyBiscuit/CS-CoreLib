package me.mrCookieSlime.CSCoreLibPlugin.compatibility;

import org.bukkit.Material;

import java.util.Arrays;

public class MaterialHelper {

    public static final Material[] WoolColours;
    public static final Material[] StainedGlassColours;
    public static final Material[] StainedGlassPaneColours;
    public static final Material[] TerracottaColours;
    public static final Material[] GlazedTerracottaColours;

    static {
        WoolColours = Arrays.stream(Material.values()).filter(MaterialHelper::isWool)
            .toArray(Material[]::new);

        StainedGlassColours = Arrays.stream(Material.values()).filter(MaterialHelper::isStainedGlass)
            .toArray(Material[]::new);

        StainedGlassPaneColours = Arrays.stream(Material.values()).filter(MaterialHelper::isStainedGlassPane)
            .toArray(Material[]::new);

        TerracottaColours = Arrays.stream(Material.values()).filter(MaterialHelper::isTerracotta)
            .toArray(Material[]::new);

        GlazedTerracottaColours = Arrays.stream(Material.values()).filter(MaterialHelper::isGlazedTerracotta)
            .toArray(Material[]::new);
    }

    public static Material getSaplingFromLog(Material log) {
        switch (log) {
            case OAK_LOG:
                return Material.OAK_SAPLING;
            case SPRUCE_LOG:
                return Material.SPRUCE_SAPLING;
            case BIRCH_LOG:
                return Material.BIRCH_SAPLING;
            case JUNGLE_LOG:
                return Material.JUNGLE_SAPLING;
            case ACACIA_LOG:
                return Material.ACACIA_SAPLING;
            case DARK_OAK_LOG:
                return Material.DARK_OAK_SAPLING;
            default:
                break;
        }

        return Material.OAK_SAPLING;
    }

    public static Material getWoodFromLog(Material log) {
        if (!isLog(log))
            return Material.AIR;

        String type = log.name().substring(0, log.name().lastIndexOf('_'));
        try {
            return Material.valueOf(type + "_PLANKS");
        }catch (IllegalArgumentException ignored) {
            return Material.AIR;
        }
    }

    public static boolean isLog(Material log) {
        return log.name().endsWith("_LOG");
    }

    public static boolean isLeavesBlock(Material leaves) {
        return leaves.name().endsWith("_LEAVES");
    }

    public static boolean isWool(Material material) {
        for (Material mat : WoolColours)
            if (mat == material) return true;
        return false;
    }

    public static boolean isStainedGlass(Material material) {
        return material.name().endsWith("_STAINED_GLASS");
    }

    public static boolean isStainedGlassPane(Material material) {
        return material.name().endsWith("_STAINED_GLASS_PANE");
    }

    public static boolean isTerracotta(Material material) {
        return material.name().endsWith("_TERRACOTTA");
    }

    public static boolean isGlazedTerracotta(Material material) {
        return material.name().endsWith("_GLAZED_TERRACOTTA");
    }
}
