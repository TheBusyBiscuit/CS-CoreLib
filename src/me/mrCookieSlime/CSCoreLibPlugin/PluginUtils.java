package me.mrCookieSlime.CSCoreLibPlugin;

import java.io.File;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Localization;
import me.mrCookieSlime.CSCoreLibPlugin.updater.Updater;

import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import io.github.thebusybiscuit.cscorelib2.updater.BukkitUpdater;

@Deprecated
public class PluginUtils {

	private Plugin plugin;
	private int id;
	private Config cfg;
	private Localization local;
	private Metrics metrics;
	
	/**
	 * Creates a new PluginUtils Instance for
	 * the specified Plugin
	 *
	 * @param  plugin The Plugin for which this PluginUtils Instance is made for
	 */ 
	public PluginUtils(Plugin plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * Creates a new PluginUtils Instance for
	 * the specified Plugin
	 *
	 * @param  plugin The Plugin for which this PluginUtils Instance is made for
	 */ 
	public PluginUtils(Plugin plugin, int id) {
		this(plugin);
		this.id = id;
	}
	
	/**
	 * Returns the specified ID from Curse
	 *
	 * @return      Plugin ID
	 */ 
	public int getPluginID() {
		return this.id;
	}
	
	/**
	 * Automatically sets up an Updater Service for you
	 *
	 * @param  id The ID of your Project
	 * @param  file The file of your Plugin (obtained by plugin.getFile() )
	 */ 
	public void setupUpdater(int id, File file) {
		this.id = id;
		if (plugin.getConfig().getBoolean("options.auto-update")) {
			new BukkitUpdater(plugin, file, id).start();
		}
	}
	
	/**
	 * Automatically sets up an Updater Service for you
	 *
	 * @param  file The file of your Plugin (obtained by plugin.getFile() )
	 */ 
	public void setupUpdater(File file) {
		if (plugin.getConfig().getBoolean("options.auto-update")) {
			new BukkitUpdater(plugin, file, id).start();
		}
	}
	
	/**
	 * Automatically sets up MC-Stats Metrics for you
	 */ 
	public void setupMetrics() {
	    metrics = new Metrics(plugin);
	}

	/**
	 * Returns the previously setup Metrics
	 * which can be used to setup custom charts
	 *
	 * @return      Metrics of this Plugin
	 */
	public Metrics getMetrics() {
		return metrics;
	}

	/**
	 * Automatically sets up the messages.yml for you
	 */ 
	public void setupLocalization() {
		local = new Localization(plugin);
	}
	
	/**
	 * Automatically sets up the config.yml for you
	 */ 
	public void setupConfig() {
		FileConfiguration config = plugin.getConfig();
		config.options().copyDefaults(true);
		config.options().header("\nName: " + plugin.getName() + "\nAuthor: " + plugin.getDescription().getAuthors().get(0) + "\n\nDo not modify the Config while the Server is running\notherwise bad things might happen!\n\nThis Plugin also requires CS-CoreLib to run!\nIf you don't have it installed already, its going to be\nautomatically installed for you\n\nThis Plugin utilises an Auto-Updater. If you want to turn that off,\nsimply set options -> auto-update to false");
		plugin.saveConfig();
		
		cfg = new Config(plugin);
	}
	
	/**
	 * Returns the previously setup Config
	 *
	 * @return      Config of this Plugin
	 */ 
	public Config getConfig() {
		return cfg;
	}
	
	/**
	 * Returns the previously setup Localization
	 *
	 * @return      Localization for this Plugin
	 */ 
	public Localization getLocalization() {
		return local;
	}
}
