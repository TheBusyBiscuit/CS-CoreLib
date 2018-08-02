package me.mrCookieSlime.CSCoreLibPlugin.events.Listeners;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.CustomBookOverlay;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class CustomBookOverlay1_9 implements Listener {
	
	@EventHandler
	public void onDrop(PlayerSwapHandItemsEvent e) {
		if (CustomBookOverlay.opening.contains(e.getPlayer().getUniqueId())) e.setCancelled(true);
	}

}
