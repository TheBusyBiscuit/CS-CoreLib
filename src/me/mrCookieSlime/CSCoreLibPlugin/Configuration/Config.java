package me.mrCookieSlime.CSCoreLibPlugin.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class Config {
	
	File file;
	FileConfiguration config;
	
	/**
	 * Creates a new Config Object for the config.yml File of
	 * the specified Plugin
	 *
	 * @param  plugin The Instance of the Plugin, the config.yml is referring to
	 */
	public Config(Plugin plugin) {
		this.file = new File("plugins/" + plugin.getDescription().getName().replace(" ", "_") + "/config.yml");
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	/**
	 * Creates a new Config Object for the specified File
	 *
	 * @param  file The File for which the Config object is created for
	 */
	public Config(File file) {
		this.file = file;
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	/**
	 * Creates a new Config Object for the File with in
	 * the specified Location
	 *
	 * @param  path The Path of the File which the Config object is created for
	 */
	public Config(String path) {
		this.file = new File(path);
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
	
	/**
	 * Returns the File the Config is handling
	 *
	 * @return      The File this Config is handling
	 */ 
	public File getFile() {
		return this.file;
	}
	
	/**
	 * Converts this Config Object into a plain FileConfiguration Object
	 *
	 * @return      The converted FileConfiguration Object
	 */ 
	public FileConfiguration getConfiguration() {
		return this.config;
	}
	
	/**
	 * Sets the Value for the specified Path
	 *
	 * @param  path The path in the Config File
	 * @param  value The Value for that Path
	 */
	public void setValue(String path, Object value) {
		if (value == null) {
			config.set(path, value);
			config.set(path + "_extra", null);
		}
		else if (value instanceof Inventory) {
			for (int i = 0; i < ((Inventory) value).getSize(); i++) {
				setValue(path + "." + i, ((Inventory) value).getItem(i));
			}
		}
		else if (value instanceof Date) {
			config.set(path, String.valueOf(((Date) value).getTime()));
		}
		else if (value instanceof Long) {
			config.set(path, String.valueOf(value));
		}
		else if (value instanceof UUID) {
			config.set(path, value.toString());
		}
		else if (value instanceof Sound) {
			config.set(path, String.valueOf(value));
		}
		else if (value instanceof ItemStack) {
			config.set(path, new ItemStack((ItemStack) value));
			try {
				if (((ItemStack) value).hasItemMeta() && ((ItemStack) value).getItemMeta() instanceof SkullMeta) {
					config.set(path + "_extra.custom-skull", CustomSkull.getTexture((ItemStack) value));
					config.set(path + "_extra.custom-skullOwner", CustomSkull.getName((ItemStack) value));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (value instanceof Location) {
			setValue(path + ".x", ((Location) value).getX());
			setValue(path + ".y", ((Location) value).getY());
			setValue(path + ".z", ((Location) value).getZ());
			setValue(path + ".pitch", ((Location) value).getPitch());
			setValue(path + ".yaw", ((Location) value).getYaw());
			setValue(path + ".world", ((Location) value).getWorld().getName());
		}
		else if (value instanceof Chunk) {
			setValue(path + ".x", ((Chunk) value).getX());
			setValue(path + ".z", ((Chunk) value).getZ());
			setValue(path + ".world", ((Chunk) value).getWorld().getName());
		}
		else if (value instanceof World) {
			config.set(path, ((World) value).getName());
		}
		else config.set(path, value);
	}
	
	/**
	 * Saves the Config Object to its File
	 */ 
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
		}
	}
	
	/**
	 * Saves the Config Object to a File
	 * 
	 * @param  file The File you are saving this Config to
	 */ 
	public void save(File file) {
		try {
			config.save(file);
		} catch (IOException e) {
		}
	}
	

	/**
	 * Sets the Value for the specified Path 
	 * (IF the Path does not yet exist)
	 *
	 * @param  path The path in the Config File
	 * @param  value The Value for that Path
	 */
	public void setDefaultValue(String path, Object value) {
		if (!contains(path)) setValue(path, value);
	}
	
	/**
	 * Checks whether the Config contains the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      True/false
	 */ 
	public boolean contains(String path) {
		return config.contains(path);
	}
	
	/**
	 * Returns the Object at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Value at that Path
	 */ 
	public Object getValue(String path) {
		return config.get(path);
	}
	
	/**
	 * Returns the ItemStack at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The ItemStack at that Path
	 */ 
	public ItemStack getItem(String path) {
		ItemStack item = config.getItemStack(path);
		if (item == null) return null;
		try {
			if (item.hasItemMeta() && item.getItemMeta() instanceof SkullMeta) {
				if (config.contains(path + "_extra.custom-skull")) item = CustomSkull.getItem((ItemStack) item, config.getString(path + "_extra.custom-skull"));
				if (config.contains(path + "_extra.custom-skullOwner") && !((ItemStack) item).getItemMeta().hasDisplayName()) {
					ItemMeta im = ((ItemStack) item).getItemMeta();
					im.setDisplayName("§r" + config.getString(path + "_extra.custom-skullOwner") + "'s Head");
					((ItemStack) item).setItemMeta(im);
				}
			}
			else {
				config.set(path + "_extra.custom-skull", null);
				config.set(path + "_extra.custom-skullOwner", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	/**
	 * Returns a randomly chosen String from an
	 * ArrayList at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      A randomly chosen String from the ArrayList at that Path
	 */ 
	public String getRandomStringfromList(String path) {
		return getStringList(path).get(CSCoreLib.randomizer().nextInt(getStringList(path).size()));
	}
	
	/**
	 * Returns a randomly chosen Integer from an
	 * ArrayList at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      A randomly chosen Integer from the ArrayList at that Path
	 */ 
	public int getRandomIntfromList(String path) {
		return getIntList(path).get(CSCoreLib.randomizer().nextInt(getIntList(path).size()));
	}
	
	/**
	 * Returns the String at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The String at that Path
	 */ 
	public String getString(String path) {
		return config.getString(path);
	}
	
	/**
	 * Returns the Integer at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Integer at that Path
	 */ 
	public int getInt(String path) {
		return config.getInt(path);
	}
	
	/**
	 * Returns the Boolean at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Boolean at that Path
	 */ 
	public boolean getBoolean(String path) {
		return config.getBoolean(path);
	}
	
	/**
	 * Returns the StringList at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The StringList at that Path
	 */ 
	public List<String> getStringList(String path) {
		return config.getStringList(path);
	}
	
	/**
	 * Returns the ItemList at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The ItemList at that Path
	 */ 
	public List<ItemStack> getItemList(String path) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		for (String key: getKeys(path)) {
			if (!key.endsWith("_extra")) list.add(getItem(path + "." + key));
		}
		return list;
	}
	
	/**
	 * Returns the IntegerList at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The IntegerList at that Path
	 */ 
	public List<Integer> getIntList(String path) {
		return config.getIntegerList(path);
	}
	
	/**
	 * Recreates the File of this Config
	 */ 
	public void createFile() {
		try {
			this.file.createNewFile();
		} catch (IOException e) {
		}
	}
	
	/**
	 * Returns the Float at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Float at that Path
	 */ 
	public Float getFloat(String path) {
		return Float.valueOf(String.valueOf(getValue(path)));
	}
	
	/**
	 * Returns the Long at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Long at that Path
	 */ 
	public Long getLong(String path) {
		return Long.valueOf(String.valueOf(getValue(path)));
	}

	/**
	 * Returns the Sound at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Sound at that Path
	 */ 
	public Sound getSound(String path) {
		return Sound.valueOf(getString(path));
	}
	
	/**
	 * Returns the Date at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Date at that Path
	 */ 
	public Date getDate(String path) {
		return new Date(getLong(path));
	}
	
	/**
	 * Returns the Chunk at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Chunk at that Path
	 */ 
	public Chunk getChunk(String path) {
		return Bukkit.getWorld(getString(path + ".world")).getChunkAt(getInt(path + ".x"), getInt(path + ".z"));
	}
	
	/**
	 * Returns the UUID at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The UUID at that Path
	 */ 
	public UUID getUUID(String path) {
		return UUID.fromString(getString(path));
	}
	
	/**
	 * Returns the World at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The World at that Path
	 */ 
	public World getWorld(String path) {
		return Bukkit.getWorld(getString(path));
	}
	
	/**
	 * Returns the Double at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Double at that Path
	 */ 
	public Double getDouble(String path) {
		return config.getDouble(path);
	}
	
	/**
	 * Returns the Location at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @return      The Location at that Path
	 */ 
	public Location getLocation(String path) {
		if (config.contains(path + ".pitch")) {
			return new Location(
				Bukkit.getWorld(
				getString(path + ".world")),
				getDouble(path + ".x"),
				getDouble(path + ".y"),
				getDouble(path + ".z"),
				getFloat(path + ".yaw"),
				getFloat(path + ".pitch")
			);
		}
		else {
			return new Location(
				Bukkit.getWorld(
				config.getString(path + ".world")),
				config.getDouble(path + ".x"),
				config.getDouble(path + ".y"),
				config.getDouble(path + ".z")
			);
		}
	}
	
	@Deprecated
	public void setLocation(String path, Location location) {
		setValue(path + ".x", location.getX());
		setValue(path + ".y", location.getY());
		setValue(path + ".z", location.getZ());
		setValue(path + ".world", location.getWorld().getName());
	}
	
	@Deprecated
	public void setInventory(String path, Inventory inventory) {
		for (int i = 0; i < inventory.getSize(); i++) {
			setValue(path + "." + i, inventory.getItem(i));
		}
	}
	
	/**
	 * Gets the Contents of an Inventory at the specified Path
	 *
	 * @param  path The path in the Config File
	 * @param  size The Size of the Inventory
	 * @param  title The Title of the Inventory
	 * @return      The generated Inventory
	 */ 
	public Inventory getInventory(String path, int size, String title) {
		Inventory inventory = Bukkit.createInventory(null, size, title);
		for (int i = 0; i < size; i++) {
			inventory.setItem(i, getItem(path + "." + i));
		}
		return inventory;
	}
	
	/**
	 * Returns all Paths in this Config
	 *
	 * @return      All Paths in this Config
	 */ 
	public Set<String> getKeys() {
		return config.getKeys(false);
	}
	
	/**
	 * Returns all Sub-Paths in this Config
	 *
	 * @param  path The path in the Config File
	 * @return      All Sub-Paths of the specified Path
	 */ 
	public Set<String> getKeys(String path) {
		return config.getConfigurationSection(path).getKeys(false);
	}
	
	/**
	 * Reloads the Configuration File
	 */ 
	public void reload() {
		this.config = YamlConfiguration.loadConfiguration(this.file);
	}
}
