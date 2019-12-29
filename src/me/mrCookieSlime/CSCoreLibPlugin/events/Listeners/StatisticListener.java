package me.mrCookieSlime.CSCoreLibPlugin.events.Listeners;

import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerStats;
import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerStats.PlayerStat;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

@Deprecated
public class StatisticListener implements Listener {
	
	public StatisticListener(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
	public void onDie(PlayerDeathEvent e) {
		if (e.getEntity().getKiller() != null) {
			PlayerStats.getStats(e.getEntity().getKiller()).addStatistic(PlayerStat.PLAYERS_KILLED, 1);
			PlayerStats.getStats(e.getEntity().getKiller()).addStatistic(PlayerStat.PLAYER_KILLSTREAK, 1);
		}
		PlayerStats.getStats(e.getEntity()).addStatistic(PlayerStat.DEATHS, 1);
		PlayerStats.getStats(e.getEntity()).setStatistic(PlayerStat.PLAYER_KILLSTREAK, 0);
	}
	
	@EventHandler(priority=EventPriority.MONITOR, ignoreCancelled=true)
	public void onBreak(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode() != GameMode.CREATIVE) PlayerStats.getStats(e.getPlayer()).addStatistic(PlayerStat.BLOCKS_BROKEN, 1);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		PlayerStats.unregister(e.getPlayer());
	}

}
