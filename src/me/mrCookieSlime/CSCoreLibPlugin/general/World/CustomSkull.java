package me.mrCookieSlime.CSCoreLibPlugin.general.World;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.CraftObject;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.PackageName;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

@Deprecated
public class CustomSkull {
	
	private static Method tileentity, gameprofile, getgameprofile, property, insert_property, map_list, get_name, get_value, getOwner;
	private static Constructor<?> position, profile_constructor, property_constructor;
	private static Class<?> profile_class, property_class, map_class;
	
	static {
		try {
			profile_class = Class.forName("com.mojang.authlib.GameProfile");
			property_class = Class.forName("com.mojang.authlib.properties.Property");
			map_class = Class.forName("com.mojang.authlib.properties.PropertyMap");
			
			profile_constructor = ReflectionUtils.getConstructor(profile_class, UUID.class, String.class);
			property = ReflectionUtils.getMethod(profile_class, "getProperties");
			property_constructor = ReflectionUtils.getConstructor(property_class, String.class, String.class);
			insert_property = ReflectionUtils.getMethod(map_class, "put", String.class, property_class);
			map_list = ReflectionUtils.getMethod(map_class, "get", String.class);
			gameprofile = ReflectionUtils.getClass(PackageName.NMS, "TileEntitySkull").getMethod("setGameProfile", profile_class);
			
			// Removed from 1.14			
			if (!ReflectionUtils.isVersion("v1_14_"))
				getgameprofile = ReflectionUtils.getClass(PackageName.NMS, "TileEntitySkull").getMethod("getGameProfile");
			
			getOwner = ReflectionUtils.getMethod(profile_class, "getName");
			get_name = ReflectionUtils.getMethod(property_class, "getName");
			get_value = ReflectionUtils.getMethod(property_class, "getValue");
			
			position = ReflectionUtils.getConstructor(ReflectionUtils.getClass(PackageName.NMS, "BlockPosition"), int.class, int.class, int.class);
			tileentity = ReflectionUtils.getClass(PackageName.NMS, "WorldServer").getMethod("getTileEntity", ReflectionUtils.getClass(PackageName.NMS, "BlockPosition"));
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Object createProfile(String texture) throws Exception {
		if (!CSCoreLib.getLib().getCfg().contains("skulls.uuids." + texture)) {
			CSCoreLib.getLib().getCfg().setValue("skulls.uuids." + texture, UUID.randomUUID().toString());
			CSCoreLib.getLib().getCfg().save();
		}
		Object profile = profile_constructor.newInstance(UUID.fromString(CSCoreLib.getLib().getCfg().getString("skulls.uuids." + texture)), "CSCoreLib");
		Object properties = property.invoke(profile);
		insert_property.invoke(properties, "textures", property_constructor.newInstance("textures", texture));
		return profile;
	}
	
	/**
	 * Sets the Skull at that Location to be the Skin of the specified Base64 String
	 *
	 * @param  block The Block being set to that Skin
	 * @param  texture A Base64 String representing the Texture
	 */ 
	public static void setSkull(Block block, String texture) throws Exception {
		if (getTexture(block).equals(texture)) return;

		if (ReflectionUtils.isVersion("v1_12_", "v1_11_", "v1_10_", "v1_9_")) {
			if (!block.getType().equals(Material.valueOf("SKULL"))) {
				throw new IllegalArgumentException("Block is not a Skull");
			}
		} 
		else {
			if (!(block.getType().equals(Material.PLAYER_HEAD) || block.getType().equals(Material.PLAYER_WALL_HEAD))) {
				throw new IllegalArgumentException("Block is not a Skull");
			}
		}
		
		Object profile = createProfile(texture);
		Object world = ReflectionUtils.getHandle(CraftObject.WORLD, block.getWorld());
		
		Object tile = tileentity.invoke(world, position.newInstance(block.getX(), block.getY(), block.getZ()));
		
		try {
			if (tile != null){
				gameprofile.invoke(tile, profile);
				block.getState().update(true);
			}
		} catch(NullPointerException x) {
			System.err.println("Method: " + gameprofile);
			System.err.println("World: " + world);
			System.err.println("Tile Retriever: " + tileentity);
			System.err.println("Tile: " + tile);
			System.err.println("Profile Retriever: " + gameprofile);
			System.err.println("Profile: " + profile);
			x.printStackTrace();
		}
	}

	/**
	 * Returns a Skull Item with the specified Texture
	 *
	 * @param  texture A Base64 String representing the Texture
	 * @return      The modified ItemStack
	 */ 
	public static ItemStack getItem(String texture) throws Exception {
		Object profile = createProfile(texture);
		ItemStack item = new ItemStack(Material.valueOf("PLAYER_HEAD"));
		
		ItemMeta im = item.getItemMeta();
		ReflectionUtils.setFieldValue(im, "profile", profile);
		item.setItemMeta(im);
		return item;
	}
	
	/**
	 * Sets the specified Skull to have the specified Texture
	 *
	 * @param  item The ItemStack you want to modify
	 * @param  texture A Base64 String representing the Texture
	 * @return      The modified ItemStack
	 */
	public static ItemStack getItem(ItemStack item, String texture) throws Exception {
		if (texture == null) return item;
		Object profile = createProfile(texture);
		ItemMeta im = item.getItemMeta();
		ReflectionUtils.setFieldValue(im, "profile", profile);
		item.setItemMeta(im);
		return item;
	}
	
	/**
	 * Returns the Base64 String representing the Texture of the specified item
	 *
	 * @param  item The ItemStack you want to retrieve the Data from
	 * @return      The found Base64 String representing the Texture
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */ 
	public static String getTexture(ItemStack item) {
		if (!(item.getItemMeta() instanceof SkullMeta)) return null;
		Object profile = null;
		try {
			profile = ReflectionUtils.getFieldValue(item.getItemMeta(), "profile");
			Collection<?> collection = (Collection<?>) map_list.invoke(property.invoke(profile), "textures");
			for (Object p: collection) {
				if (get_name.invoke(p).equals("textures")) return (String) get_value.invoke(p);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	public static String getTexture(Block block) throws Exception {
		Object world = ReflectionUtils.getHandle(CraftObject.WORLD, block.getWorld());
		
		Object tile = tileentity.invoke(world, position.newInstance(block.getX(), block.getY(), block.getZ()));		
		if (tile == null) return "";

		Object profile;
		
		if (ReflectionUtils.isVersion("v1_14_")) profile = ReflectionUtils.getFieldValue(tile, "gameProfile");
		else profile = getgameprofile.invoke(tile);
		
		if (profile != null) {
			Collection<?> collection = (Collection<?>) map_list.invoke(property.invoke(profile), "textures");
			for (Object p: collection) {
				if (get_name.invoke(p).equals("textures")) return (String) get_value.invoke(p);
			}
		}
		
		return "";
	}

	/**
	 * Returns the Skull Owner of that Skull
	 *
	 * @param  item The ItemStack you want to retrieve the Data from
	 * @return      The found Skull Owner
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */ 
	public static Object getName(ItemStack item) {
		if (!(item.getItemMeta() instanceof SkullMeta)) return "CSCoreLib";
		try {
			return getOwner.invoke(ReflectionUtils.getFieldValue(item.getItemMeta(), "profile"));
		} catch (Exception e) {
			return "CSCoreLib";
		}
	}

}
