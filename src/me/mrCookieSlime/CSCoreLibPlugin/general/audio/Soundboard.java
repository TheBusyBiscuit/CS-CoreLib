package me.mrCookieSlime.CSCoreLibPlugin.general.audio;

import org.bukkit.Sound;

@Deprecated
public class Soundboard {
	
	public static Sound getLegacySounds(String... sounds) {
		for (String sound: sounds) {
			try {
				Sound s = Sound.valueOf(sound);
				return s;
			} catch(Exception x) {
			}
		}
		return null;
	}

}
