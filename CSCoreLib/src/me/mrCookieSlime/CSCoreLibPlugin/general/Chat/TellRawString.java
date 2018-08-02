package me.mrCookieSlime.CSCoreLibPlugin.general.Chat;

public class TellRawString extends TellRawMessage {
	
	private String json;
	
	/**
   	 * Creates a new Instance based on the
   	 * specified JSON String
	 *
	 * @param  json The JSON Message you want to send
   	 */ 
	public TellRawString(String json) {
		this.json = json;
	}
	
	/**
   	 * Overridden Method to ensure it uses
   	 * the previously defined JSON String
   	 * instead of building a new One
   	 * 
   	 * @return      The previously defined JSON String
   	 */ 
	@Override
	public String build() {
		return this.json;
	}

}
