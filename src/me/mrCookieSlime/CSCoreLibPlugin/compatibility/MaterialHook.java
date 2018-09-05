package me.mrCookieSlime.CSCoreLibPlugin.compatibility;

import org.bukkit.Material;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

public class MaterialHook {
	
	public static Material parse(String name, String legacy) {
		if (ReflectionUtils.isVersion("v1_12_", "v1_11_", "v1_10_", "v1_9_")) {
			return Material.valueOf(legacy);
		}
		else {
			return Material.valueOf(name);
		}
	}


    public static Material getSaplingFromLog(Material log) {
        try {
            return MaterialHook13.getSaplingFromLog(log);
        } catch (Throwable ignored) {
            return parse("OAK_SAPLING", "SAPLING");
        }
    }


    public static Material getWoodFromLog(Material log) {
        try {
            return MaterialHook13.getWoodFromLog(log);
        } catch (Throwable ignored) {
            return parse("OAK_WOOD", "WOOD");
        }
    }

    public static boolean isLog(Material log) {
        try {
            return MaterialHook13.isLog(log);
        } catch (Throwable ignored) {
            return log == parse("OAK_WOOD", "WOOD");
        }
    }


    public static final Material[] WoolColours;
    public static final Material[] StainedGlassColours;
    public static final Material[] StainedGlassPaneColours;
    public static final Material[] TerracottaColours;
    public static final Material[] GlazedTerracottaColours;

    static {
        Material[] wools, stainedGlass, stainedGlassPane, terraCotta, glazedTerraCotta;
        try {
            //Try to load with new 1.13 Materials
            wools = MaterialHook13.initWoolColours();
            stainedGlass = MaterialHook13.initStainedGlassColours();
            stainedGlassPane = MaterialHook13.initStainedGlassPaneColours();
            terraCotta = MaterialHook13.initTerracottaColours();
            glazedTerraCotta = MaterialHook13.initGlazesTerracottaColours();
        } catch (Throwable ignored) {
            // The materials most likely could not be found - so we put some default values into them
            wools = new Material[]{parse("WHITE_WOOL", "WOOL")};
            stainedGlass = new Material[]{parse("WHITE_STAINED_GLASS", "STAINED_GLASS")};
            stainedGlassPane = new Material[]{parse("WHITE_STAINED_GLASS_PANE", "STAINED_GLASS_PANE")};
            terraCotta = new Material[]{parse("WHITE_TERRACOTTA", "WHITE_TERRACOTTA")};
            glazedTerraCotta = new Material[]{parse("WHITE_GLAZED_TERRACOTTA", "STONE")};
        }
        WoolColours = wools;
        StainedGlassColours = stainedGlass;
        StainedGlassPaneColours = stainedGlassPane;
        TerracottaColours = terraCotta;
        GlazedTerracottaColours = glazedTerraCotta;
    }

    public static boolean isWool(Material material) {
        for (Material mat : WoolColours)
            if (mat == material) return true;
        return false;
    }

    public static boolean isStainedGlass(Material material) {
        for (Material mat : StainedGlassColours)
            if (mat == material) return true;
        return false;
    }

    public static boolean isStainedGlassPane(Material material) {
        for (Material mat : StainedGlassPaneColours)
            if (mat == material) return true;
        return false;
    }

    public static boolean isTerracotta(Material material) {
        for (Material mat : TerracottaColours)
            if (mat == material) return true;
        return false;
    }

    public static boolean isGlazedTerracotta(Material material) {
        for (Material mat : GlazedTerracottaColours)
            if (mat == material) return true;
        return false;
    }


}
