package me.mrCookieSlime.CSCoreLibPlugin.general.Player;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class PlayerStats {
	
	private static Set<PlayerStats> stats = new HashSet<PlayerStats>();
	private static Map<UUID, PlayerStats> map = new HashMap<UUID, PlayerStats>();
	
	UUID uuid;
	Config cfg;
	Map<PlayerStat, Long> statistics;
	
	public PlayerStats(UUID uuid) {
		this.uuid = uuid;
		File file = new File("data-storage/CS-CoreLib/PlayerStats/" + uuid + ".stats");
		cfg = new Config(file);
		statistics = new HashMap<PlayerStat, Long>();
		
		if (cfg.contains("stat.LOGIN")) cfg.setValue("stat.LOGIN", String.valueOf(cfg.getLong("stat.LOGIN") + 1));
		else cfg.setValue("stat.LOGIN", "1");
		
		if (file.exists()) {
			for (String key: cfg.getKeys("stat")) {
				statistics.put(PlayerStat.valueOf(key), cfg.getLong("stat." + key));
			}
		}
		
		stats.add(this);
		map.put(uuid, this);
	}
	
	public static PlayerStats getStats(Player p) {
		if (map.containsKey(p.getUniqueId())) return map.get(p.getUniqueId());
		else return new PlayerStats(p.getUniqueId());
	}
	
	public long getStatistic(PlayerStat stat) {
		switch (stat) {
			case PLAY_TIME_MS: {
				return Long.valueOf(String.valueOf(Bukkit.getPlayer(uuid).getStatistic(Statistic.PLAY_ONE_TICK) * 50));
			}
			default: {
				if (statistics.containsKey(stat)) return statistics.get(stat);
				else return 0;
			}
		}
	}
	
	public void setStatistic(PlayerStat stat, long value) {
		statistics.put(stat, value);
	}
	
	public void addStatistic(PlayerStat stat, long addition) {
		setStatistic(stat, getStatistic(stat) + addition);
	}
	
	private void save() {
		for (PlayerStat stat: statistics.keySet()) {
			cfg.setValue("stat." + stat.toString(), String.valueOf(getStatistic(stat)));
		}
		cfg.save();
	}
	
	public enum PlayerStat {
		
		LOGIN,
		BLOCKS_BROKEN,
		PLAYERS_KILLED,
		DEATHS,
		PLAYER_KILLSTREAK,
		PLAY_TIME_MS;
		
	}
	
	public static void unregister(Player p) {
		if (map.containsKey(p.getUniqueId())) {
			PlayerStats ps = map.get(p.getUniqueId());
			ps.save();
			
			stats.remove(ps);
			map.remove(p.getUniqueId());
		}
	}
}