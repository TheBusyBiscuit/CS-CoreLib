package me.mrCookieSlime.CSCoreLibSetup;

import me.mrCookieSlime.CSCoreLibPlugin.PluginUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Localization;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Variable;

import org.bukkit.plugin.java.JavaPlugin;

// Before you continue reading this
// make sure you have added the CSCoreLibLoader.class to your package
// CSCoreLibLoader.class (https://dl.dropbox.com/s/vqlu8ogh0cgszpx/CSCoreLibLoader.java?dl=0)
// and CS-CoreLib to your Build Path

// Your standard Plugin Setup
public class CSCoreLibExample extends JavaPlugin {
	
	// Your generic onEnable() method
	@Override
	public void onEnable() {
		// Now to make sure your Plugin auto-installs CS-CoreLib if it was not found, create a new Instance of your CSCoreLibLoader
		// You need to specify an Instance of your main class for that, but as you do this in your onEnable() method, simply specify this
		CSCoreLibLoader loader = new CSCoreLibLoader(this);
		// Now to check whether CS-CoreLib was properly installed, do the following statement:
		if (loader.load()) {
			// Now you can start enabling your Plugin right here as CSCoreLib was properly installed.
			// The first thing you will most likely do is setting up the basics of your Plugin like a Config
			// To do that you will need an Instance of PluginUtils for your Plugin
			PluginUtils utils = new PluginUtils(this);
			// Now setting up the config is as easy as the following
			// This will take care of copying your default config.yml in your src folder and loading it
			utils.setupConfig();
			// To access your Config at any time, you can do this
			Config cfg = utils.getConfig();
			// This Config Object also has some useful methods which come along with it, you can play around with those for yourself
			cfg.getLocation("path");
			cfg.setDefaultValue("path", "value");
			cfg.getRandomStringfromList("path");
			cfg.getFloat("path");
			cfg.save();
			cfg.reload();
			cfg.getString("path");
			// And a lot more...

			// And since a lot of people nowadays want more customization, there is also a Localization provided
			utils.setupLocalization();
			// And to access it:
			Localization local = utils.getLocalization();
			// And this Localization Object is a stripped down Version of the Config Object and has some of its Methods
			local.setDefault("key", "You", "can", "specify", "multiple", "messages", "if", "you", "want");
			// This will return the List of strings that are found under this key
			local.getTranslation("key");
			// You can also specify a Prefix if you want, also Color Codes are supported in all of these methods
			local.setDefault("prefix", "&7[Awesome Plugin]");
			// The following will send the Messages to the specified Player (null in this case)
			// The boolean at the end represents whether a Prefix will be attached or not.
			// Note here that you need to specify a Prefix first
			local.sendTranslation(null, "path.to.message", true);
			// Now if your localized Messages contains placeholders like %player%
			// No problem, you can make use of those as well
			local.sendTranslation(null, "path.to.message", true, new Variable("%player%", "replaced text"));
		}
	}
	
}
