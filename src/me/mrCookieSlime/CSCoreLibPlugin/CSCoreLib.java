package me.mrCookieSlime.CSCoreLibPlugin;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.Guide.Guides;
import me.mrCookieSlime.CSCoreLibPlugin.currency.Currency;
import me.mrCookieSlime.CSCoreLibPlugin.events.Listeners.GuideListener;
import me.mrCookieSlime.CSCoreLibPlugin.events.Listeners.ItemUseListener;
import me.mrCookieSlime.CSCoreLibPlugin.events.Listeners.MapListener;
import me.mrCookieSlime.CSCoreLibPlugin.events.Listeners.MenuClickListener;
import me.mrCookieSlime.CSCoreLibPlugin.events.Listeners.StatisticListener;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.CustomBookOverlay;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Maps;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.MenuHelper;
import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerStats;
import me.mrCookieSlime.CSCoreLibPlugin.general.audio.SoundConfig;
import me.mrCookieSlime.CSCoreLibPlugin.protection.ProtectionManager;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CSCoreLib extends JavaPlugin {
	
	private static Random random;
	private PluginUtils utils;
	private static CSCoreLib instance;
	private Config cfg;
	
	private ProtectionManager manager;
	
	private Set<Pattern> log_patterns = new HashSet<Pattern>();
	
	@Override
	public void onEnable() {
		
		if (!new File("data-storage/CS-CoreLib/PlayerStats").exists()) new File("data-storage/CS-CoreLib/PlayerStats").mkdirs();
		new GuideListener(this);
		new ItemUseListener(this);
		new MapListener(this);
		new MenuClickListener(this);
		new StatisticListener(this);
		getServer().getPluginManager().registerEvents(new MenuHelper(), this);
		
		random = new Random();
		new Maps();
		new Guides();
		
		this.manager = new ProtectionManager(this);
		
		utils = new PluginUtils(this);
		utils.setupConfig();
		
		cfg = utils.getConfig();
		cfg.setDefaultValue("skulls.uuid", UUID.randomUUID().toString());
		cfg.save();
		
		utils.setupUpdater(88802, getFile());
		utils.setupMetrics();
		instance = this;
		
		((Logger) LogManager.getRootLogger()).addFilter(new AbstractFilter() {
        	
        	public Filter.Result filter(final String logger, final String message, final Level level) {
                if (message == null) return Filter.Result.NEUTRAL;
        		for (Pattern pattern: log_patterns) {
        			final Matcher matcher = pattern.matcher(message);
                    if (matcher.matches()) {
                        return Filter.Result.DENY;
                    }
        		}
                return Filter.Result.NEUTRAL;
            }
            
            public Filter.Result filter(final LogEvent event) {
                return this.filter(event.getLoggerName(), event.getMessage().getFormattedMessage(), event.getLevel());
            }
            
            public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String message, final Object... parameters) {
                return this.filter(logger.getName(), message, level);
            }
            
            public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Object message, final Throwable t) {
                return this.filter(logger.getName(), message.toString(), level);
            }
            
            public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Message message, final Throwable t) {
                return this.filter(logger.getName(), message.getFormattedMessage(), level);
            }
        	
		});
		
		filterLog("([A-Za-z0-9_]{3,16}) issued server command: /cs_triggerinterface (.{0,})");
	}
	
	public void filterLog(String pattern) {
		this.log_patterns.add(Pattern.compile(pattern));
	}
	
	@Override
	public void onDisable() {
		for (Player p: Bukkit.getOnlinePlayers()) {
			PlayerStats.unregister(p);
		}
		
		random = null;
		Guides.instance = null;
		Maps.instance = null;
		Currency.currencies = null;
		instance = null;
		MenuHelper.map = null;
		SoundConfig.items = null;
		CustomBookOverlay.opening = null;
		PlayerRunnable.map = null;
	}

	/**
	 * Returns a global Random Instance
	 *
	 * @return      Random Instance
	 */ 
	public static Random randomizer() {
		return random;
	}
	
	/**
	 * Returns the Instance of CS-CoreLibs main class
	 *
	 * @return      CS-CoreLibs main class
	 */ 
	public static CSCoreLib getLib() {
		return instance;
	}
	
	/**
	 * Returns CS-CoreLib's Config
	 *
	 * @return      CS-CoreLibs Config
	 */ 
	public Config getCfg() {
		return cfg;
	}
	
	/**
	 * Returns a ProtectionManager
	 *
	 * @return      ProtectionManager
	 */ 
	public ProtectionManager getProtectionManager() {
		return this.manager;
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (sender instanceof Player && args.length == 1) {
			PlayerRunnable.run(args[0], (Player) sender);
		}
		return true;
	}
}
