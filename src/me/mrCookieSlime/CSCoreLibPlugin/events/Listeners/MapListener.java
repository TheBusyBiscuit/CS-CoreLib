package me.mrCookieSlime.CSCoreLibPlugin.events.Listeners;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Maps;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;

@Deprecated
public class MapListener implements Listener {
	
	public MapListener(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (Maps.getInstance().menus.containsKey(e.getPlayer().getUniqueId())) {
			Maps.getInstance().menus.get(e.getPlayer().getUniqueId()).getMenuCloseHandler().onClose((Player) e.getPlayer());
			Maps.getInstance().menus.remove(e.getPlayer().getUniqueId());
		}
		if (Maps.getInstance().inv.containsKey(e.getPlayer().getUniqueId())) Maps.getInstance().inv.remove(e.getPlayer().getUniqueId());
		if (Maps.getInstance().sounds.containsKey(e.getPlayer().getUniqueId())) {
			((Player) e.getPlayer()).playSound(e.getPlayer().getLocation(), Maps.getInstance().sounds.get(e.getPlayer().getUniqueId()).getClosingSound(), 1F, 1F);
			Maps.getInstance().sounds.remove(e.getPlayer().getUniqueId());
		}
	}

}
