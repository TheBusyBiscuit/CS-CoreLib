package me.mrCookieSlime.CSCoreLibPlugin.Configuration;

import org.bukkit.entity.Player;

public abstract class IndividualVariable extends Variable {

	/**
	 * Creates a new Variable Object with the specified Placeholder
	 * 
	 * @param  placeholder The String which will get replaced by the Variable
	 */ 
	public IndividualVariable(String placeholder) {
		super(placeholder, "");
	}
	
	/**
	 * Sets the String which will replace the placeholder
	 *
	 * @param  p The Player who this Variable refers to
	 * @return      This Method must be overriden via anonymous inner classes
	 */ 
	public abstract String getVariable(Player p);
	
	/**
	 * Returns the specified String with this Variable appplied to it
	 *
	 * @param  p The Player this Variable refers to
	 * @param  string The String which this Variable will be applied to
	 * @return      The applied String
	 */ 
	public String apply(Player p, String string) {
		return string.replace(this.placeholder, this.getVariable(p));
	}

}
