package me.mrCookieSlime.CSCoreLibPlugin.compatibility;

import org.bukkit.Material;

import java.util.Arrays;

@Deprecated
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
        if (!isLog(log))
            return Material.AIR;

        String type = log.name().substring(0, log.name().lastIndexOf('_'));
        type = type.replace("STRIPPED_", "");
        try {
            return Material.valueOf(type + "_SAPLING");
        }catch (IllegalArgumentException ignored) {
            return Material.AIR;
        }
    }

    public static Material getWoodFromLog(Material log) {
        if (!isLog(log))
            return Material.AIR;

        String type = log.name().substring(0, log.name().lastIndexOf('_'));
      	type = type.replace("STRIPPED_", "");
        try {
            return Material.valueOf(type + "_PLANKS");
        } catch (IllegalArgumentException ignored) {
            return Material.AIR;
        }
    }

    public static boolean isLog(Material log) {
        return log.name().endsWith("_LOG") || log.name().endsWith("_WOOD");
    }

    public static boolean isLeavesBlock(Material leaves) {
        return leaves.name().endsWith("_LEAVES");
    }

    public static boolean isWool(Material material) {
        return material.name().endsWith("_WOOL");
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

    public static boolean isDiggable(Material material) {
        return material.name().endsWith("_DIRT") || material.name().equals("DIRT")
                || material.name().endsWith("_SAND") || material.name().equals("SAND")
                || material.name().startsWith("SNOW_") || material.name().equals("SNOW")
                || material.name().equals("PODZOL") || material.name().equals("GRAVEL")
                || material.name().equals("MYCELIUM") || material.name().startsWith("GRASS_")
                || material.name().equals("FARMLAND") || material.name().equals("CLAY");
    }
}
