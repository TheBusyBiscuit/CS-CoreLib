package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@Deprecated
public class MenuHelper implements Listener {
	
	public static Map<UUID, ChatHandler> map = new HashMap<>();
	public static Map<UUID, ChatHandler> map2 = new HashMap<>();
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onChat(final AsyncPlayerChatEvent e) {
		final Player p = e.getPlayer();
		final String message = e.getMessage().replaceAll("\\u00a7", "&");
		if (map.containsKey(p.getUniqueId())) {
			final ChatHandler handler = map.get(p.getUniqueId());
			map.remove(p.getUniqueId());
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(CSCoreLib.getLib(), new Runnable() {
				
				@Override
				public void run() {
					handler.onChat(p, message);
				}
			}, 0L);
			
			e.setCancelled(true);
		}
		else if (map2.containsKey(p.getUniqueId())) {
			final ChatHandler handler = map2.get(p.getUniqueId());
			map2.remove(p.getUniqueId());
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(CSCoreLib.getLib(), new Runnable() {
				
				@Override
				public void run() {
					handler.onChat(p, message);
				}
			}, 0L);
			
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onComamnd(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (map.containsKey(p.getUniqueId())) {
			ChatHandler handler = map.get(p.getUniqueId());
			map.remove(p.getUniqueId());
			handler.onChat(p, e.getMessage());
			
			e.setCancelled(true);
		}
		else if (map2.containsKey(p.getUniqueId())) {
			ChatHandler handler = map2.get(p.getUniqueId());
			map2.remove(p.getUniqueId());
			handler.onChat(p, e.getMessage());
			
			e.setCancelled(true);
		}
	}
	
	public interface ChatHandler {
		
		boolean onChat(Player p, String input);
	}
	
	public static void awaitChatInput(Player p, ChatHandler handler) {
		map.put(p.getUniqueId(), handler);
	}
	
	public static void awaitChatInput(Player p, boolean command, ChatHandler handler) {
		if (command) map2.put(p.getUniqueId(), handler);
		else map.put(p.getUniqueId(), handler);
	}

}
