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
		items.put("AMBIENT_CAVE", new MaterialData(Material.STONE));
		items.put("AMBIENT_UNDERWATER_ENTER", new MaterialData(Material.WATER_BUCKET));
		items.put("AMBIENT_UNDERWATER_EXIT", new MaterialData(Material.WATER_BUCKET));
		items.put("AMBIENT_UNDERWATER_LOOP", new MaterialData(Material.WATER_BUCKET));
		items.put("AMBIENT_UNDERWATER_LOOP_ADDITIONS", new MaterialData(Material.WATER_BUCKET));
		items.put("AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE", new MaterialData(Material.WATER_BUCKET));
		items.put("AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE", new MaterialData(Material.WATER_BUCKET));
		items.put("WEATHER_RAIN", new MaterialData(Material.WATER_BUCKET));
		items.put("ENTITY_LIGHTNING_BOLT_THUNDER", new MaterialData(Material.GUNPOWDER));
		
		// Anvil
		items.put("BLOCK_ANVIL_BREAK", new MaterialData(Material.ANVIL));
		items.put("BLOCK_ANVIL_DESTROY", new MaterialData(Material.ANVIL));
		items.put("BLOCK_ANVIL_FALL", new MaterialData(Material.ANVIL));
		items.put("BLOCK_ANVIL_LAND", new MaterialData(Material.ANVIL));
		items.put("BLOCK_ANVIL_USE", new MaterialData(Material.ANVIL));
		items.put("BLOCK_ANVIL_HIT", new MaterialData(Material.ANVIL));
		items.put("BLOCK_ANVIL_PLACE", new MaterialData(Material.ANVIL));
		items.put("BLOCK_ANVIL_STEP", new MaterialData(Material.ANVIL));
		
		// Bat
		items.put("ENTITY_BAT_DEATH", new MaterialData(Material.BAT_SPAWN_EGG));
		items.put("ENTITY_BAT_HURT", new MaterialData(Material.BAT_SPAWN_EGG));
		items.put("ENTITY_BAT_AMBIENT", new MaterialData(Material.BAT_SPAWN_EGG));
		items.put("ENTITY_BAT_LOOP", new MaterialData(Material.BAT_SPAWN_EGG));
		items.put("ENTITY_BAT_TAKEOFF", new MaterialData(Material.BAT_SPAWN_EGG));
		
		// Blaze
		items.put("ENTITY_BLAZE_AMBIENT", new MaterialData(Material.BLAZE_SPAWN_EGG));
		items.put("ENTITY_BLAZE_DEATH", new MaterialData(Material.BLAZE_SPAWN_EGG));
		items.put("ENTITY_BLAZE_HURTT", new MaterialData(Material.BLAZE_SPAWN_EGG));
		items.put("ENTITY_BLAZE_BURN", new MaterialData(Material.BLAZE_SPAWN_EGG));
		items.put("ENTITY_BLAZE_SHOOT", new MaterialData(Material.BLAZE_SPAWN_EGG));
		
		// Cat
		items.put("ENTITY_CAT_HISS", new MaterialData(Material.OCELOT_SPAWN_EGG));
		items.put("ENTITY_CAT_HURT", new MaterialData(Material.OCELOT_SPAWN_EGG));
		items.put("ENTITY_CAT_MEOW", new MaterialData(Material.OCELOT_SPAWN_EGG));
		items.put("ENTITY_CAT_PURR", new MaterialData(Material.OCELOT_SPAWN_EGG));
		items.put("ENTITY_CAT_PURREOW", new MaterialData(Material.OCELOT_SPAWN_EGG));
		items.put("ENTITY_CAT_DEATH", new MaterialData(Material.OCELOT_SPAWN_EGG));
		items.put("ENTITY_CAT_AMBIENT", new MaterialData(Material.OCELOT_SPAWN_EGG));
		
		// Chest
		items.put("BLOCK_CHEST_CLOSE", new MaterialData(Material.CHEST));
		items.put("BLOCK_CHEST_OPEN", new MaterialData(Material.CHEST));
		items.put("BLOCK_CHEST_LOCKED", new MaterialData(Material.CHEST));
		
		// Chicken
		items.put("ENTITY_CHICKEN_EGG", new MaterialData(Material.EGG));
		items.put("ENTITY_CHICKEN_HURT", new MaterialData(Material.CHICKEN_SPAWN_EGG));
		items.put("ENTITY_CHICKEN_AMBIENT", new MaterialData(Material.CHICKEN_SPAWN_EGG));
		items.put("ENTITY_CHICKEN_STEP", new MaterialData(Material.CHICKEN_SPAWN_EGG));
		items.put("ENTITY_CHICKEN_DEATH", new MaterialData(Material.CHICKEN_SPAWN_EGG));
		
		// Cow
		items.put("ENTITY_COW_HURT", new MaterialData(Material.COW_SPAWN_EGG));
		items.put("ENTITY_COW_AMBIENT", new MaterialData(Material.COW_SPAWN_EGG));
		items.put("ENTITY_COW_STEP", new MaterialData(Material.COW_SPAWN_EGG));
		items.put("ENTITY_COW_MILK", new MaterialData(Material.COW_SPAWN_EGG));
		items.put("ENTITY_COW_DEATH", new MaterialData(Material.COW_SPAWN_EGG));
		
		// Creeper
		items.put("CREEPER_HURT", new MaterialData(Material.CREEPER_SPAWN_EGG));
		items.put("CREEPER_DEATH", new MaterialData(Material.CREEPER_SPAWN_EGG));
		items.put("CREEPER_PRIMED", new MaterialData(Material.CREEPER_SPAWN_EGG));
		
		// Dig
		items.put("BLOCK_GRASS_BREAK", new MaterialData(Material.GRASS));
		items.put("BLOCK_GRAVEL_BREAK", new MaterialData(Material.GRAVEL));
		items.put("BLOCK_SAND_BREAK", new MaterialData(Material.SAND));
		items.put("BLOCK_SNOW_BREAK", new MaterialData(Material.SNOW));
		items.put("BLOCK_WOOD_BREAK", new MaterialData(Material.OAK_WOOD));
		items.put("BLOCK_WOOL_BREAK", new MaterialData(Material.WHITE_WOOL));
		
		// Step
		items.put("BLOCK_GRASS_STEP", new MaterialData(Material.GRASS));
		items.put("BLOCK_GRAVEL_STEP", new MaterialData(Material.GRAVEL));
		items.put("BLOCK_SAND_STEP", new MaterialData(Material.SAND));
		items.put("BLOCK_SNOW_STEP", new MaterialData(Material.SNOW));
		items.put("BLOCK_WOOD_STEP", new MaterialData(Material.OAK_WOOD));
		items.put("BLOCK_WOOL_STEP", new MaterialData(Material.WHITE_WOOL));
		
		// Donkey
		items.put("ENTITY_DONKEY_ANGRY", new MaterialData(Material.DONKEY_SPAWN_EGG));
		items.put("ENTITY_DONKEY_DEATH", new MaterialData(Material.DONKEY_SPAWN_EGG));
		items.put("ENTITY_DONKEY_HURT", new MaterialData(Material.DONKEY_SPAWN_EGG));
		items.put("ENTITY_DONKEY_AMBIENT", new MaterialData(Material.DONKEY_SPAWN_EGG));
		items.put("ENTITY_DONKEY_CHEST", new MaterialData(Material.DONKEY_SPAWN_EGG));
		
		// Door
		items.put("BLOCK_WOODEN_DOOR_CLOSE", new MaterialData(Material.OAK_DOOR));
		items.put("BLOCK_WOODEN_DOOR_OPEN", new MaterialData(Material.OAK_DOOR));
		
		// Misc
		items.put("ENTITY_GENERIC_DRINK", new MaterialData(Material.POTION));
		items.put("ENTITY_GENERIC_EAT", new MaterialData(Material.APPLE));
		items.put("ENTITY_PLAYER_BURP", new MaterialData(Material.CAKE));
		items.put("UI_BUTTON_CLICK", new MaterialData(Material.STONE_BUTTON));
		items.put("ENTITY_GENERIC_EXPLODE", new MaterialData(Material.TNT));
		items.put("ENTITY_GENERIC_BIG_FALL", new MaterialData(Material.FEATHER));
		items.put("ENTITY_GENERIC_SMALL_FALL", new MaterialData(Material.FEATHER));
		items.put("BLOCK_GLASS_PLACE", new MaterialData(Material.GLASS));
		items.put("ENTITY_GENERIC_HURT", new MaterialData(Material.ROTTEN_FLESH));
		items.put("ENTITY_PLAYER_LEVELUP", new MaterialData(Material.EXPERIENCE_BOTTLE));
		items.put("ENTITY_EXPERIENCE_ORB_PICKUP", new MaterialData(Material.EXPERIENCE_BOTTLE));
		items.put("ENTITY_ARROW_SHOOT", new MaterialData(Material.ARROW));
		items.put("ENTITY_GENERIC_SPLASH", new MaterialData(Material.POTION));
		items.put("ENTITY_PLAYER_SPLASH", new MaterialData(Material.POTION));
		items.put("ENTITY_PLAYER_ATTACK_CRIT", new MaterialData(Material.ARROW));
		items.put("ENTITY_GENERIC_SWIM", new MaterialData(Material.WATER_BUCKET));
		items.put("ITEM_BUCKET_EMPTY", new MaterialData(Material.WATER_BUCKET));
		items.put("BLOCK_WOODEN_BUTTON_CLICK_ON", new MaterialData(Material.OAK_BUTTON));
		
		// Ender Dragon
		items.put("ENDERDRAGON_DEATH", new MaterialData(Material.DRAGON_EGG));
		items.put("ENDERDRAGON_GROWL", new MaterialData(Material.DRAGON_EGG));
		items.put("ENDERDRAGON_HURT", new MaterialData(Material.DRAGON_EGG));
		items.put("ENTITY_ENDER_DRAGON_FLAP", new MaterialData(Material.DRAGON_EGG));
		items.put("ENTITY_ENDER_DRAGON_SHOOT", new MaterialData(Material.DRAGON_EGG));
		items.put("ENTITY_ENDER_DRAGON_AMBIENT", new MaterialData(Material.DRAGON_EGG));
		
		// Enderman
		items.put("ENTITY_ENDERMAN_AMBIENT", new MaterialData(Material.ENDERMAN_SPAWN_EGG));
		items.put("ENTITY_ENDERMAN_DEATH", new MaterialData(Material.ENDERMAN_SPAWN_EGG));
		items.put("ENTITY_ENDERMAN_HURT", new MaterialData(Material.ENDERMAN_SPAWN_EGG));
		items.put("ENTITY_ENDERMAN_SCREAM", new MaterialData(Material.ENDERMAN_SPAWN_EGG));
		items.put("ENTITY_ENDERMAN_STARE", new MaterialData(Material.ENDERMAN_SPAWN_EGG));
		items.put("ENTITY_ENDERMAN_TELEPORT", new MaterialData(Material.ENDERMAN_SPAWN_EGG));
		
		// Fire
		items.put("BLOCK_FIRE_AMBIENT", new MaterialData(Material.FLINT_AND_STEEL));
		items.put("ITEM_FLINTANDSTEEL_USE", new MaterialData(Material.FLINT_AND_STEEL));
		
		// Firework
		items.put("ENTITY_FIREWORK_ROCKET_BLAST", new MaterialData(Material.FIREWORK_ROCKET));
		items.put("ENTITY_FIREWORK_ROCKET_BLAST_FAR", new MaterialData(Material.FIREWORK_ROCKET));
		items.put("ENTITY_FIREWORK_ROCKET_LARGE_BLAST", new MaterialData(Material.FIREWORK_ROCKET));
		items.put("ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR", new MaterialData(Material.FIREWORK_ROCKET));
		items.put("ENTITY_FIREWORK_ROCKET_LAUNCH", new MaterialData(Material.FIREWORK_ROCKET));
		items.put("ENTITY_FIREWORK_ROCKET_SHOOT", new MaterialData(Material.FIREWORK_ROCKET));
		items.put("ENTITY_FIREWORK_ROCKET_TWINKLE", new MaterialData(Material.FIREWORK_ROCKET));
		items.put("ENTITY_FIREWORK_ROCKET_TWINKLE_FAR", new MaterialData(Material.FIREWORK_ROCKET));
		
		// TNT
		items.put("ENTITY_TNT_PRIMED", new MaterialData(Material.TNT));
		
		// Ghast
		items.put("ENTITY_GHAST_AMBIENT", new MaterialData(Material.GHAST_SPAWN_EGG));
		items.put("ENTITY_GHAST_DEATH", new MaterialData(Material.GHAST_SPAWN_EGG));
		items.put("ENTITY_GHAST_HURT", new MaterialData(Material.GHAST_SPAWN_EGG));
		items.put("ENTITY_GHAST_SCREAM", new MaterialData(Material.GHAST_SPAWN_EGG));
		items.put("ENTITY_GHAST_SHOOT", new MaterialData(Material.GHAST_SPAWN_EGG));
		items.put("ENTITY_GHAST_WARN", new MaterialData(Material.GHAST_SPAWN_EGG));
		
		// Horse
		items.put("ENTITY_HORSE_AMBIENT", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_ANGRY", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_ARMOR", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_BREATHE", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_DEATH", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_EAT", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_GALLOP", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_HURT", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_JUMP", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_LAND", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_SADDLE", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_STEP", new MaterialData(Material.HORSE_SPAWN_EGG));
		items.put("ENTITY_HORSE_STEP_WOOD", new MaterialData(Material.HORSE_SPAWN_EGG));
		
		// Skeleton Horse
		items.put("ENTITY_SKELETON_HORSE_AMBIENT", new MaterialData(Material.SKELETON_HORSE_SPAWN_EGG));
		items.put("ENTITY_SKELETON_HORSE_AMBIENT_WATER", new MaterialData(Material.SKELETON_HORSE_SPAWN_EGG));
		items.put("ENTITY_SKELETON_HORSE_DEATH", new MaterialData(Material.SKELETON_HORSE_SPAWN_EGG));
		items.put("ENTITY_SKELETON_HORSE_GALLOP_WATER", new MaterialData(Material.SKELETON_HORSE_SPAWN_EGG));
		items.put("ENTITY_SKELETON_HORSE_HURT", new MaterialData(Material.SKELETON_HORSE_SPAWN_EGG));
		items.put("ENTITY_SKELETON_HORSE_JUMP_WATER", new MaterialData(Material.SKELETON_HORSE_SPAWN_EGG));
		items.put("ENTITY_SKELETON_HORSE_STEP_WATER", new MaterialData(Material.SKELETON_HORSE_SPAWN_EGG));
		items.put("ENTITY_SKELETON_HORSE_SWIM", new MaterialData(Material.SKELETON_HORSE_SPAWN_EGG));
		
		// Zombie Horse
		items.put("ENTITY_ZOMBIE_HORSE_AMBIENT", new MaterialData(Material.ZOMBIE_HORSE_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_HORSE_DEATH", new MaterialData(Material.ZOMBIE_HORSE_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_HORSE_HURT", new MaterialData(Material.ZOMBIE_HORSE_SPAWN_EGG));
		
		// Iron Golem
		items.put("ENTITY_IRON_GOLEM_ATTACK", new MaterialData(Material.CARVED_PUMPKIN));
		items.put("ENTITY_IRON_GOLEM_DEATH", new MaterialData(Material.CARVED_PUMPKIN));
		items.put("ENTITY_IRON_GOLEM_HURT", new MaterialData(Material.CARVED_PUMPKIN));
		items.put("ENTITY_IRON_GOLEM_STEP", new MaterialData(Material.CARVED_PUMPKIN));
		
		// Item
		items.put("ENTITY_ITEM_BREAK", new MaterialData(Material.IRON_SWORD));
		items.put("ENTITY_ITEM_PICKUP", new MaterialData(Material.HOPPER));
		
		// Lava
		items.put("BLOCK_LAVA_AMBIENT", new MaterialData(Material.LAVA_BUCKET));
		items.put("BLOCK_LAVA_EXTINGUISH", new MaterialData(Material.LAVA_BUCKET));
		items.put("BLOCK_LAVA_POP", new MaterialData(Material.LAVA_BUCKET));
		
		// Magma Cube
		items.put("ENTITY_MAGMA_CUBE_DEATH", new MaterialData(Material.MAGMA_CUBE_SPAWN_EGG));
		items.put("ENTITY_MAGMA_CUBE_DEATH_SMALL", new MaterialData(Material.MAGMA_CUBE_SPAWN_EGG));
		items.put("ENTITY_MAGMA_CUBE_HURT", new MaterialData(Material.MAGMA_CUBE_SPAWN_EGG));
		items.put("ENTITY_MAGMA_CUBE_HURT_SMALL", new MaterialData(Material.MAGMA_CUBE_SPAWN_EGG));
		items.put("ENTITY_MAGMA_CUBE_JUMP", new MaterialData(Material.MAGMA_CUBE_SPAWN_EGG));
		items.put("ENTITY_MAGMA_CUBE_SQUISH", new MaterialData(Material.MAGMA_CUBE_SPAWN_EGG));
		items.put("ENTITY_MAGMA_CUBE_SQUISH_SMALL", new MaterialData(Material.MAGMA_CUBE_SPAWN_EGG));
		
		// Minecart
		items.put("ENTITY_MINECART_INSIDE", new MaterialData(Material.MINECART));
		items.put("ENTITY_MINECART_RIDING", new MaterialData(Material.MINECART));
		
		// Pig
		items.put("ENTITY_PIG_AMBIENT", new MaterialData(Material.PIG_SPAWN_EGG));
		items.put("ENTITY_PIG_DEATH", new MaterialData(Material.PIG_SPAWN_EGG));
		items.put("ENTITY_PIG_HURT", new MaterialData(Material.PIG_SPAWN_EGG));
		items.put("ENTITY_PIG_SADDLE", new MaterialData(Material.PIG_SPAWN_EGG));
		items.put("ENTITY_PIG_STEP", new MaterialData(Material.PIG_SPAWN_EGG));
		
		// Piston
		items.put("BLOCK_PISTON_CONTRACT", new MaterialData(Material.MINECART));
		items.put("BLOCK_PISTON_EXTEND", new MaterialData(Material.MINECART));
		
		// Sheep
		items.put("ENTITY_SHEEP_AMBIENT", new MaterialData(Material.SHEEP_SPAWN_EGG));
		items.put("ENTITY_SHEEP_DEATH", new MaterialData(Material.SHEEP_SPAWN_EGG));
		items.put("ENTITY_SHEEP_HURT", new MaterialData(Material.SHEEP_SPAWN_EGG));
		items.put("ENTITY_SHEEP_SHEAR", new MaterialData(Material.SHEEP_SPAWN_EGG));
		items.put("ENTITY_SHEEP_STEP", new MaterialData(Material.SHEEP_SPAWN_EGG));
		
		// Silverfish
		items.put("ENTITY_SILVERFISH_AMBIENT", new MaterialData(Material.SILVERFISH_SPAWN_EGG));
		items.put("ENTITY_SILVERFISH_DEATH", new MaterialData(Material.SILVERFISH_SPAWN_EGG));
		items.put("ENTITY_SILVERFISH_HURT", new MaterialData(Material.SILVERFISH_SPAWN_EGG));
		items.put("ENTITY_SILVERFISH_STEP", new MaterialData(Material.SILVERFISH_SPAWN_EGG));
		
		// Skeleton
		items.put("ENTITY_SKELETON_AMBIENT", new MaterialData(Material.SKELETON_SPAWN_EGG));
		items.put("ENTITY_SKELETON_DEATH", new MaterialData(Material.SKELETON_SPAWN_EGG));
		items.put("ENTITY_SKELETON_HURT", new MaterialData(Material.SKELETON_SPAWN_EGG));
		items.put("ENTITY_SKELETON_SHOOT", new MaterialData(Material.SKELETON_SPAWN_EGG));
		items.put("ENTITY_SKELETON_STEP", new MaterialData(Material.SKELETON_SPAWN_EGG));
		
		// Slime
		items.put("ENTITY_SLIME_ATTACK", new MaterialData(Material.SLIME_SPAWN_EGG));
		items.put("ENTITY_SLIME_DEATH", new MaterialData(Material.SLIME_SPAWN_EGG));
		items.put("ENTITY_SLIME_DEATH_SMALL", new MaterialData(Material.SLIME_SPAWN_EGG));
		items.put("ENTITY_SLIME_HURT", new MaterialData(Material.SLIME_SPAWN_EGG));
		items.put("ENTITY_SLIME_HURT_SMALL", new MaterialData(Material.SLIME_SPAWN_EGG));
		items.put("ENTITY_SLIME_JUMP", new MaterialData(Material.SLIME_SPAWN_EGG));
		items.put("ENTITY_SLIME_JUMP_SMALL", new MaterialData(Material.SLIME_SPAWN_EGG));
		items.put("ENTITY_SLIME_SQUISH", new MaterialData(Material.SLIME_SPAWN_EGG));
		items.put("ENTITY_SLIME_SQUISH_SMALL", new MaterialData(Material.SLIME_SPAWN_EGG));
		
		// Spider
		items.put("ENTITY_SPIDER_AMBIENT", new MaterialData(Material.SPIDER_SPAWN_EGG));
		items.put("ENTITY_SPIDER_DEATH", new MaterialData(Material.SPIDER_SPAWN_EGG));
		items.put("ENTITY_SPIDER_HURT", new MaterialData(Material.SPIDER_SPAWN_EGG));
		items.put("ENTITY_SPIDER_STEP", new MaterialData(Material.SPIDER_SPAWN_EGG));
		
		// Villager
		items.put("ENTITY_VILLAGER_AMBIENT", new MaterialData(Material.VILLAGER_SPAWN_EGG));
		items.put("ENTITY_VILLAGER_DEATH", new MaterialData(Material.VILLAGER_SPAWN_EGG));
		items.put("ENTITY_VILLAGER_HURT", new MaterialData(Material.VILLAGER_SPAWN_EGG));
		items.put("ENTITY_VILLAGER_NO", new MaterialData(Material.VILLAGER_SPAWN_EGG));
		items.put("ENTITY_VILLAGER_TRADE", new MaterialData(Material.VILLAGER_SPAWN_EGG));
		items.put("ENTITY_VILLAGER_YES", new MaterialData(Material.VILLAGER_SPAWN_EGG));
		
		// Wither
		items.put("ENTITY_WITHER_AMBIENT", new MaterialData(Material.NETHER_STAR));
		items.put("ENTITY_WITHER_BREAK_BLOCK", new MaterialData(Material.NETHER_STAR));
		items.put("ENTITY_WITHER_DEATH", new MaterialData(Material.NETHER_STAR));
		items.put("ENTITY_WITHER_HURT", new MaterialData(Material.NETHER_STAR));
		items.put("ENTITY_WITHER_SHOOT", new MaterialData(Material.NETHER_STAR));
		items.put("ENTITY_WITHER_SPAWN", new MaterialData(Material.NETHER_STAR));
		
		// Wolf
		items.put("ENTITY_WOLF_AMBIENT", new MaterialData(Material.WOLF_SPAWN_EGG));
		items.put("ENTITY_WOLF_DEATH", new MaterialData(Material.WOLF_SPAWN_EGG));
		items.put("ENTITY_WOLF_GROWL", new MaterialData(Material.WOLF_SPAWN_EGG));
		items.put("ENTITY_WOLF_HOWL", new MaterialData(Material.WOLF_SPAWN_EGG));
		items.put("ENTITY_WOLF_HURT", new MaterialData(Material.WOLF_SPAWN_EGG));
		items.put("ENTITY_WOLF_PANT", new MaterialData(Material.WOLF_SPAWN_EGG));
		items.put("ENTITY_WOLF_SHAKE", new MaterialData(Material.WOLF_SPAWN_EGG));
		items.put("ENTITY_WOLF_STEP", new MaterialData(Material.WOLF_SPAWN_EGG));
		items.put("ENTITY_WOLF_WHINE", new MaterialData(Material.WOLF_SPAWN_EGG));
		
		// Zombie
		items.put("ENTITY_ZOMBIE_AMBIENT", new MaterialData(Material.ZOMBIE_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_DEATH", new MaterialData(Material.ZOMBIE_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_HURT", new MaterialData(Material.ZOMBIE_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_INFECT", new MaterialData(Material.ZOMBIE_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_CONVERTED_TO_DROWNED", new MaterialData(Material.ZOMBIE_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_ATTACK_IRON_DOOR", new MaterialData(Material.ZOMBIE_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_STEP", new MaterialData(Material.ZOMBIE_SPAWN_EGG));

		items.put("ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR", new MaterialData(Material.OAK_DOOR));
		items.put("ENTITY_ZOMBIE_BREAK_WOODEN_DOOR", new MaterialData(Material.OAK_DOOR));
		
		// Zombie Villager
		items.put("ENTITY_ZOMBIE_AMBIENT", new MaterialData(Material.ZOMBIE_VILLAGER_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_VILLAGER_CONVERTED", new MaterialData(Material.ZOMBIE_VILLAGER_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_VILLAGER_CURE", new MaterialData(Material.ZOMBIE_VILLAGER_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_VILLAGER_DEATH", new MaterialData(Material.ZOMBIE_VILLAGER_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_VILLAGER_HURT", new MaterialData(Material.ZOMBIE_VILLAGER_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_VILLAGER_STEP", new MaterialData(Material.ZOMBIE_VILLAGER_SPAWN_EGG));
				
		// Zombie Pigman
		items.put("ENTITY_ZOMBIE_PIGMAN_AMBIENT", new MaterialData(Material.ZOMBIE_PIGMAN_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_PIGMAN_ANGRY", new MaterialData(Material.ZOMBIE_PIGMAN_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_PIGMAN_DEATH", new MaterialData(Material.ZOMBIE_PIGMAN_SPAWN_EGG));
		items.put("ENTITY_ZOMBIE_PIGMAN_HURT", new MaterialData(Material.ZOMBIE_PIGMAN_SPAWN_EGG));
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
		ChestMenu menu = new ChestMenu("&cSound Manager");
		
		menu.addItem(1, new CustomItem(new MaterialData(Material.REDSTONE), "&eEdit Volume", "", "&7\u21E8 Click to open"));
		menu.addMenuClickHandler(1, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				openVolumeManager(p, sound);
				return false;
			}
		});
		
		menu.addItem(4, new CustomItem(new MaterialData(Material.NOTE_BLOCK), "&ePreview", "&rSelected Sound: &b" + cfg.getString(sound + ".enum"), "&rVolume: &b" + cfg.getFloat(sound + ".volume"), "&rPitch: &b" + cfg.getFloat(sound + ".pitch"), "", "&7\u21E8 Click to hear a Preview"));
		menu.addMenuClickHandler(4, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				playSound(p, sound);
				return false;
			}
		});
		
		menu.addItem(7, new CustomItem(new MaterialData(Material.GLOWSTONE_DUST), "&eEdit Pitch", "", "&7\u21E8 Click to open"));
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
