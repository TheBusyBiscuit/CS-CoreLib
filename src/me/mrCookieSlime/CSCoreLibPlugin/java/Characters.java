package me.mrCookieSlime.CSCoreLibPlugin.java;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

public class Characters {
	
	private static final char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	/**
	 * Returns a random Character
	 *
	 * @return      A Random Character
	 */ 
	public static char getRandomCharacter() {
		return chars[CSCoreLib.randomizer().nextInt(chars.length)];
	}
	
	/**
	 * Generates a random String
	 * 
	 * @param  length The Length of your desired String
	 * @return      A randomly generated String
	 */ 
	public static String getRandomString(int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(getRandomCharacter());
		}
		return builder.toString();
	}
}
