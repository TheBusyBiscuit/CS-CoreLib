package me.mrCookieSlime.CSCoreLibPlugin.general;

import java.util.List;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

@Deprecated
public final class ListUtils {
	
	private ListUtils() {}
	
	/**
	 * Returns an amount of random Entries from
	 * a List without returning any Entries more
	 * than once
	 *
	 * @param  list The List you want the entries to be taken from
	 * @param  random How many Entries should be returned
	 * @return      List of random Entries from the List
	 */ 
	public static <T> List<T> getRandomEntries(List<T> list, int random) {
		final int size = list.size();
		for (int i = 0; i < (size - random); i++) {
			list.remove(CSCoreLib.randomizer().nextInt(list.size()));
		}
		return list;
	}
	
	/**
	 * Forms the StringList into a normal sentence with Spaces
	 *
	 * @param  list The List you want to convert
	 * @return      Converted String
	 */ 
	public static String toString(List<String> list) {
		return toString(list.toArray(new String[list.size()]));
	}
	
	/**
	 * Forms the String Array into a normal sentence with Spaces
	 *
	 * @param  list The List you want to convert
	 * @return      Converted String
	 */ 
	public static String toString(String... list) {
		return toString(0, list);
	}
	
	/**
	 * Forms the String Array into a normal sentence with Spaces
	 * and excludes the first X entries
	 *
	 * @param  list The List you want to convert
	 * @param  excluded The Start Index for the String Array
	 * @return      Converted String
	 */ 
	public static String toString(int excluded, String... list) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = excluded; i < list.length; i++) {
			if (i > excluded) builder.append(" ");
			builder.append(list[i]);
		}
		
		return builder.toString();
	}

}
