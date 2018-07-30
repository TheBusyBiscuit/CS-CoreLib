package me.mrCookieSlime.CSCoreLibPlugin.general.audio;

import java.util.HashMap;
import java.util.Map;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

@SuppressWarnings("deprecation")
public class SoundConfig {
	
	public static Map<String, MaterialData> items;
	
	static {
		items = new HashMap<String, MaterialData>();
		
		// Ambience
		items.put("AMBIENCE_CAVE", new MaterialData(Material.STONE));
		items.put("AMBIENCE_RAIN", new MaterialData(Material.WATER_BUCKET));
		items.put("AMBIENCE_THUNDER", new MaterialData(Material.LEGACY_SULPHUR));
		
		// Anvil
		items.put("ANVIL_BREAK", new MaterialData(Material.ANVIL));
		items.put("ANVIL_LAND", new MaterialData(Material.ANVIL));
		items.put("ANVIL_USE", new MaterialData(Material.ANVIL));
		items.put("ANVIL_HIT", new MaterialData(Material.ANVIL));
		
		// Bat
		items.put("BAT_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 65));
		items.put("BAT_HURT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 65));
		items.put("BAT_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 65));
		items.put("BAT_LOOP", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 65));
		items.put("BAT_TAKEOFF", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 65));
		
		// Blaze
		items.put("BLAZE_BREATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 61));
		items.put("BLAZE_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 61));
		items.put("BLAZE_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 61));
		
		// Cat
		items.put("CAT_HISS", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 98));
		items.put("CAT_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 98));
		items.put("CAT_MEOW", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 98));
		items.put("CAT_PURR", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 98));
		items.put("CAT_PURREOW", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 98));
		
		// Chest
		items.put("CHEST_CLOSE", new MaterialData(Material.CHEST));
		items.put("CHEST_OPEN", new MaterialData(Material.CHEST));
		
		// Chicken
		items.put("CHICKEN_EGG_POP", new MaterialData(Material.EGG));
		items.put("CHICKEN_HURT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 93));
		items.put("CHICKEN_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 93));
		items.put("CHICKEN_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 93));
		
		// Cow
		items.put("COW_HURT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 92));
		items.put("COW_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 92));
		items.put("COW_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 92));
		
		// Creeper
		items.put("CREEPER_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 50));
		items.put("CREEPER_HISS", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 50));
		
		// Dig
		items.put("DIG_GRASS", new MaterialData(Material.GRASS));
		items.put("DIG_GRAVEL", new MaterialData(Material.GRAVEL));
		items.put("DIG_SAND", new MaterialData(Material.SAND));
		items.put("DIG_SNOW", new MaterialData(Material.SNOW));
		items.put("DIG_WOOD", new MaterialData(Material.OAK_WOOD));
		items.put("DIG_WOOL", new MaterialData(Material.WHITE_WOOL));
		
		// Step
		items.put("STEP_GRASS", new MaterialData(Material.GRASS));
		items.put("STEP_GRAVEL", new MaterialData(Material.GRAVEL));
		items.put("STEP_SAND", new MaterialData(Material.SAND));
		items.put("STEP_SNOW", new MaterialData(Material.SNOW));
		items.put("STEP_WOOD", new MaterialData(Material.OAK_WOOD));
		items.put("STEP_WOOL", new MaterialData(Material.WHITE_WOOL));
		
		// Donkey
		items.put("DONKEY_ANGRY", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("DONKEY_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("DONKEY_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("DONKEY_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		
		// Door
		items.put("DOOR_CLOSE", new MaterialData(Material.OAK_DOOR));
		items.put("DOOR_OPEN", new MaterialData(Material.OAK_DOOR));
		
		// Misc
		items.put("DRINK", new MaterialData(Material.POTION));
		items.put("EAT", new MaterialData(Material.APPLE));
		items.put("BURP", new MaterialData(Material.CAKE));
		items.put("CLICK", new MaterialData(Material.STONE_BUTTON));
		items.put("EXPLODE", new MaterialData(Material.TNT));
		items.put("FALL_BIG", new MaterialData(Material.FEATHER));
		items.put("FALL_SMALL", new MaterialData(Material.FEATHER));
		items.put("GLASS", new MaterialData(Material.GLASS));
		items.put("HURT_FLESH", new MaterialData(Material.ROTTEN_FLESH));
		items.put("LEVEL_UP", new MaterialData(Material.EXPERIENCE_BOTTLE));
		items.put("ORB_PICKUP", new MaterialData(Material.EXPERIENCE_BOTTLE));
		items.put("SHOOT_ARROW", new MaterialData(Material.ARROW));
		items.put("SPLASH", new MaterialData(Material.POTION));
		items.put("SPLASH2", new MaterialData(Material.POTION));
		items.put("SUCCESSFUL_HIT", new MaterialData(Material.ARROW));
		items.put("SWIM", new MaterialData(Material.WATER_BUCKET));
		items.put("WATER", new MaterialData(Material.WATER_BUCKET));
		items.put("WOOD_CLICK", new MaterialData(Material.OAK_BUTTON));
		
		// Ender Dragon
		items.put("ENDERDRAGON_DEATH", new MaterialData(Material.DRAGON_EGG));
		items.put("ENDERDRAGON_GROWL", new MaterialData(Material.DRAGON_EGG));
		items.put("ENDERDRAGON_HIT", new MaterialData(Material.DRAGON_EGG));
		items.put("ENDERDRAGON_WINGS", new MaterialData(Material.DRAGON_EGG));
		
		// Enderman
		items.put("ENDERMAN_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 58));
		items.put("ENDERMAN_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 58));
		items.put("ENDERMAN_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 58));
		items.put("ENDERMAN_SCREAM", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 58));
		items.put("ENDERMAN_STARE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 58));
		items.put("ENDERMAN_TELEPORT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 58));
		
		// Fire
		items.put("FIRE", new MaterialData(Material.FLINT_AND_STEEL));
		items.put("FIRE_IGNITE", new MaterialData(Material.FLINT_AND_STEEL));
		
		//TODO
		// Firework
		items.put("FIREWORK_BLAST", new MaterialData(Material.FIREWORK_STAR));
		items.put("FIREWORK_BLAST2", new MaterialData(Material.FIREWORK_STAR));
		items.put("FIREWORK_LARGE_BLAST", new MaterialData(Material.FIREWORK_STAR));
		items.put("FIREWORK_LARGE_BLAST2", new MaterialData(Material.FIREWORK_STAR));
		items.put("FIREWORK_LAUNCH", new MaterialData(Material.FIREWORK_STAR));
		items.put("FIREWORK_TWINKLE", new MaterialData(Material.FIREWORK_STAR));
		items.put("FIREWORK_TWINKLE2", new MaterialData(Material.FIREWORK_STAR));
		
		// TNT
		items.put("FIZZ", new MaterialData(Material.TNT));
		items.put("FUSE", new MaterialData(Material.TNT));
		
		// Ghast
		items.put("GHAST_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 56));
		items.put("GHAST_FIREBALL", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 56));
		items.put("GHAST_MOAN", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 56));
		items.put("GHAST_SCREAM", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 56));
		items.put("GHAST_SCREAM2", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 56));
		
		// Horse
		items.put("HORSE_ANGRY", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_ARMOR", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_BREATHE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_GALLOP", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_JUMP", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_LAND", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_SADDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_SKELETON_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 51));
		items.put("HORSE_SKELETON_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 51));
		items.put("HORSE_SKELETON_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 51));
		items.put("HORSE_SOFT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_WOOD", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 100));
		items.put("HORSE_ZOMBIE_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("HORSE_ZOMBIE_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("HORSE_ZOMBIE_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		
		// Iron Golem
		items.put("IRONGOLEM_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 99));
		items.put("IRONGOLEM_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 99));
		items.put("IRONGOLEM_THROW", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 99));
		items.put("IRONGOLEM_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 99));
		
		// Item
		items.put("ITEM_BREAK", new MaterialData(Material.IRON_SWORD));
		items.put("ITEM_PICKUP", new MaterialData(Material.HOPPER));
		
		// Lava
		items.put("LAVA", new MaterialData(Material.LAVA_BUCKET));
		items.put("LAVA_POP", new MaterialData(Material.LAVA_BUCKET));
		
		// Magma Cube
		items.put("MAGMACUBE_JUMP", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 62));
		items.put("MAGMACUBE_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 62));
		items.put("MAGMACUBE_WALK2", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 62));
		
		// Minecart
		items.put("MINECART_BASE", new MaterialData(Material.MINECART));
		items.put("MINECART_INSIDE", new MaterialData(Material.MINECART));
		
		// Pig
		items.put("PIG_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 90));
		items.put("PIG_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 90));
		items.put("PIG_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 90));
		
		// Piston
		items.put("PISTON_EXTEND", new MaterialData(Material.MINECART));
		items.put("PISTON_RETRACT", new MaterialData(Material.MINECART));
		
		// Sheep
		items.put("SHEEP_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 91));
		items.put("SHEEP_SHEAR", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 91));
		items.put("SHEEP_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 91));
		
		// Silverfish
		items.put("SILVERFISH_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 60));
		items.put("SILVERFISH_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 60));
		items.put("SILVERFISH_KILL", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 60));
		items.put("SILVERFISH_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 60));
		
		// Skeleton
		items.put("SKELETON_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 51));
		items.put("SKELETON_HURT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 51));
		items.put("SKELETON_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 51));
		items.put("SKELETON_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 51));
		
		// Slime
		items.put("SLIME_ATTACK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 55));
		items.put("SLIME_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 55));
		items.put("SLIME_WALK2", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 55));
		
		// Spider
		items.put("SPIDER_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 52));
		items.put("SPIDER_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 52));
		items.put("SPIDER_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 52));
		
		// Villager
		items.put("VILLAGER_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 120));
		items.put("VILLAGER_HAGGLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 120));
		items.put("VILLAGER_HIT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 120));
		items.put("VILLAGER_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 120));
		items.put("VILLAGER_YES", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 120));
		items.put("VILLAGER_NO", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 120));
		
		// Wither
		items.put("WITHER_DEATH", new MaterialData(Material.NETHER_STAR));
		items.put("WITHER_HURT", new MaterialData(Material.NETHER_STAR));
		items.put("WITHER_IDLE", new MaterialData(Material.NETHER_STAR));
		items.put("WITHER_SHOOT", new MaterialData(Material.NETHER_STAR));
		items.put("WITHER_SPAWN", new MaterialData(Material.NETHER_STAR));
		
		// Wolf
		items.put("WOLF_BARK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		items.put("WOLF_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		items.put("WOLF_GROWL", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		items.put("WOLF_HOWL", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		items.put("WOLF_HURT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		items.put("WOLF_PANT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		items.put("WOLF_SHAKE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		items.put("WOLF_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		items.put("WOLF_WHINE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 95));
		
		// Zombie
		items.put("ZOMBIE_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("ZOMBIE_HURT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("ZOMBIE_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("ZOMBIE_INFECT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("ZOMBIE_METAL", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("ZOMBIE_REMEDY", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("ZOMBIE_UNFECT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));
		items.put("ZOMBIE_WALK", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 54));

		items.put("ZOMBIE_WOOD", new MaterialData(Material.OAK_DOOR));
		items.put("ZOMBIE_WOODBREAK", new MaterialData(Material.OAK_DOOR));
		
		// Zombie Pigman
		items.put("ZOMBIE_PIG_ANGRY", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 57));
		items.put("ZOMBIE_PIG_DEATH", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 57));
		items.put("ZOMBIE_PIG_HURT", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 57));
		items.put("ZOMBIE_PIG_IDLE", new MaterialData(Material.BAT_SPAWN_EGG, (byte) 57));
	}
	
	private static final Config cfg = new Config("data-storage/SoundConfig/sounds.cfg");
	
	public static void playSound(Player p, String sound) {
		p.playSound(p.getLocation(), cfg.getSound(sound + ".enum"), cfg.getFloat(sound + ".volume"), cfg.getFloat(sound + ".pitch"));
	}
	
	public static void playSound(Location l, String sound) {
		l.getWorld().playSound(l, cfg.getSound(sound + ".enum"), cfg.getFloat(sound + ".volume"), cfg.getFloat(sound + ".pitch"));
	}
	
	public static void registerSound(String path, Sound sound, float volume, float pitch) {
		cfg.setDefaultValue(path + ".enum", sound.toString());
		cfg.setDefaultValue(path + ".volume", volume);
		cfg.setDefaultValue(path + ".pitch", pitch);
		cfg.save();
	}
	
	public static void copySounds(Config preset, String... sounds) {
		for (String sound: sounds) {
			registerSound(sound, preset.getSound(sound + ".enum"), preset.getFloat(sound + ".volume"), preset.getFloat(sound + ".pitch"));
		}
	}
	
	public static void openConfig(final Player p, final String sound) {
		ChestMenu menu = new ChestMenu("�cSound Manager");
		
		menu.addItem(1, new CustomItem(new MaterialData(Material.REDSTONE), "�eEdit Volume", "", "�7\u21E8 Click to open"));
		menu.addMenuClickHandler(1, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				openVolumeManager(p, sound);
				return false;
			}
		});
		
		menu.addItem(4, new CustomItem(new MaterialData(Material.NOTE_BLOCK), "�ePreview", "�rSelected Sound: �b" + cfg.getString(sound + ".enum"), "�rVolume: �b" + cfg.getFloat(sound + ".volume"), "�rPitch: �b" + cfg.getFloat(sound + ".pitch"), "", "�7\u21E8 Click to hear a Preview"));
		menu.addMenuClickHandler(4, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				playSound(p, sound);
				return false;
			}
		});
		
		menu.addItem(7, new CustomItem(new MaterialData(Material.GLOWSTONE_DUST), "�eEdit Pitch", "", "�7\u21E8 Click to open"));
		menu.addMenuClickHandler(7, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				openPitchManager(p, sound);
				return false;
			}
		});
		
		menu.open(p);
	}

	private static void openVolumeManager(Player p, String sound) {
		// TODO Auto-generated method stub
	}

	private static void openPitchManager(Player p, String sound) {
		// TODO Auto-generated method stub
	}

}
