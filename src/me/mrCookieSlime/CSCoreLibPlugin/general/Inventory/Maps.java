package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import me.mrCookieSlime.CSCoreLibPlugin.general.World.MenuSounds;

public class Maps {
	
	public Map<UUID, String> inv;
	public Map<UUID, ChestMenu> menus;
	public Map<UUID, MenuSounds> sounds;
	
	public static Maps instance;
	
	public Maps() {
		inv = new HashMap<UUID, String>();
		sounds = new HashMap<UUID, MenuSounds>();
		menus = new HashMap<UUID, ChestMenu>();
		instance = this;
	}

	public static Maps getInstance() {
		return instance;
	}

}
