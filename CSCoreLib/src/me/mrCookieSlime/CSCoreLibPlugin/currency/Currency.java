package me.mrCookieSlime.CSCoreLibPlugin.currency;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Currency {
	
	public static Map<String, Currency> currencies = new HashMap<String, Currency>();
	
	String dataFolder;
	String name;
	int digits;
	int startBalance;
	Set<TransactionHandler> handlers;
	
	/**
	 * Creates a new Currency
	 *
	 * @param  plugin The Instance of the Plugin which this Currency is made for
	 * @param  name The Name of this Currency
	 * @param  digits How many digits an amount might have
	 * @param  startBalance The Balance which every Player will start with
	 */
	public Currency(Plugin plugin, String name, int digits, int startBalance) {
		this.name = name;
		this.dataFolder = "data-storage/" + plugin.getDescription().getName() + "/" + name + "/";
		this.digits = digits;
		this.startBalance = startBalance;
		this.handlers = new HashSet<TransactionHandler>();
		
		if (!new File("data-storage/" + plugin.getDescription().getName() + "/" + name).exists()) new File("data-storage/" + plugin.getDescription().getName() + "/" + name).mkdirs();
		
		currencies.put(name, this);
	}
	
	/**
	 * Registers a Handler to be listening to an Event
	 *
	 * @param  handler The Handler used upon a Players Balance changing
	 */
	public void registerTransactionHandler(TransactionHandler handler) {
		handlers.add(handler);
	}
	
	/**
	 * Sets the Balance of a Player to the specified Amount
	 *
	 * @param  p The Player whose Balance will be set
	 * @param  balance The new Balance for this Player
	 */
	public void setBalance(Player p, double balance) {
		setBalance(p.getUniqueId(), balance);
	}
	
	/**
	 * Sets the Balance of a Player to the specified Amount
	 *
	 * @param  p The Player whose Balance will be set
	 * @param  balance The new Balance for this Player
	 */
	public void setBalance(UUID uuid, double balance) {
		Config cfg = new Config(dataFolder + uuid + ".yml");
		cfg.setValue("balance", DoubleHandler.fixDouble(balance, digits));
		cfg.save();
		for (TransactionHandler handler: handlers) {
			handler.onBalanceChange(uuid, getBalance(uuid), balance);
		}
	}
	
	/**
	 * Returns the Balance of a Player
	 *
	 * @param  p The Player whose Balance you want to get
	 * @return      The Balance of that Player
	 */ 
	public double getBalance(Player p) {
		return getBalance(p.getUniqueId());
	}
	
	/**
	 * Returns the Balance of a Player
	 *
	 * @param  p The Player whose Balance you want to get
	 * @return      The Balance of that Player
	 */ 
	public double getBalance(UUID uuid) {
		File file = new File(dataFolder + uuid + ".yml");
		if (file.exists()) {
			Config cfg = new Config(dataFolder + uuid + ".yml");
			return cfg.getDouble("balance");
		}
		else {
			setBalance(uuid, startBalance);
			return startBalance;
		}
	}
	
	/**
	 * Adds to the Players Balance
	 *
	 * @param  p The Player whose Balance will be modified
	 * @param  amount The Amount which will be deposited
	 */
	public void addBalance(Player p, double amount) {
		addBalance(p.getUniqueId(), amount);
	}
	
	/**
	 * Adds to the Players Balance
	 *
	 * @param  p The Player whose Balance will be modified
	 * @param  amount The Amount which will be deposited
	 */
	public void addBalance(UUID uuid, double amount) {
		setBalance(uuid, getBalance(uuid) + amount);
	}
	
	/**
	 * Removes from the Players Balance
	 *
	 * @param  p The Player whose Balance will be modified
	 * @param  amount The Amount which will be withdrawn
	 */
	public void removeBalance(Player p, double amount) {
		removeBalance(p.getUniqueId(), amount);
	}
	
	/**
	 * Removes from the Players Balance
	 *
	 * @param  p The Player whose Balance will be modified
	 * @param  amount The Amount which will be withdrawn
	 */
	public void removeBalance(UUID uuid, double amount) {
		setBalance(uuid, getBalance(uuid) - amount);
	}
	
	/**
	 * Resets the Players Balance
	 *
	 * @param  p The Player whose Balance will be reset
	 */
	public void resetBalance(Player p) {
		resetBalance(p.getUniqueId());
	}
	
	/**
	 * Resets the Players Balance
	 *
	 * @param  p The Player whose Balance will be reset
	 */
	public void resetBalance(UUID uuid) {
		setBalance(uuid, startBalance);
	}
	
	/**
	 * Returns whether someone has enough Money to buy something
	 * If he does, the Balance will be removed automatically
	 *
	 * @param  p The Player whose Balance you want to check
	 * @param  amount The Amount the Player should at least have
	 * @return      Whether he does have enough Money or not
	 */ 
	public boolean purchase(Player p, double amount) {
		if (getBalance(p) >= amount) {
			removeBalance(p, amount);
			return true;
		}
		else return false;
	}
	
	/**
	 * Automatically colors the specified Cost in green
	 * or red depending on whether the Player has enough
	 * Money or not
	 *
	 * @param  p The Player whose Balance you want to check
	 * @param  cost The Amount the Player should at least have
	 * @return      A colored String indicating sufficient funds
	 */ 
	public String getPriceTag(Player p, double cost) {
		return ChatColor.translateAlternateColorCodes('&', (getBalance(p) >= cost ? "&a": "&4") + cost);
	}
	
	/**
	 * Returns a registered Currency with that Name
	 *
	 * @param  name The Name of the Currency you are looking for
	 * @return      The Currency registered with that Name
	 */ 
	public static Currency getByName(String name) {
		return currencies.get(name);
	}
	
	/**
	 * Returns all registered Currencies
	 *
	 * @return      All registered Currencies
	 */ 
	public static Collection<Currency> list() {
		return currencies.values();
	}
	
	public interface TransactionHandler {
		
		void onBalanceChange(UUID uuid, double balance, double newBalance);
	}
}
