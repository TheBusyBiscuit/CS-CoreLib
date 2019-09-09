package me.mrCookieSlime.CSCoreLibPlugin.Configuration;

@Deprecated
public class Variable {
	
	protected String placeholder;
	private String string;
	
	/**
	 * Creates a new Variable Object with the specified Placeholder and string
	 * 
	 * @param  placeholder The String which will get replaced by the Variable
	 * @param  string The String which will replace the placeholder
	 */ 
	public Variable(String placeholder, String string) {
		this.placeholder = placeholder;
		this.string = string;
	}
	
	/**
	 * Returns the specified String with this Variable appplied to it
	 *
	 * @param  string The String which this Variable will be applied to
	 * @return      The applied String
	 */ 
	public String apply(String string) {
		return string.replace(this.placeholder, this.string);
	}
}
