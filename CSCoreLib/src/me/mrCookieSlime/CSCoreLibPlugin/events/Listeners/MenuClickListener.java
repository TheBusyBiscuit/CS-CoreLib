package me.mrCookieSlime.CSCoreLibPlugin.events.Listeners;

import me.mrCookieSlime.CSCoreLibPlugin.events.MenuClickEvent;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Maps;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class MenuClickListener implements Listener {
	
	public MenuClickListener(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (Maps.getInstance().menus.containsKey(e.getWhoClicked().getUniqueId())) {
			ChestMenu menu = Maps.getInstance().menus.get(e.getWhoClicked().getUniqueId());
			if (e.getRawSlot() < e.getInventory().getSize()) {
				MenuClickHandler handler = menu.getMenuClickHandler(e.getSlot());
				if(handler == null) {
					e.setCancelled(!menu.isEmptySlotsClickable() && (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR));
				} else {
					if (handler instanceof AdvancedMenuClickHandler) {
						e.setCancelled(!((AdvancedMenuClickHandler) handler).onClick(e, (Player) e.getWhoClicked(), e.getSlot(), e.getCursor(), new ClickAction(e.isRightClick(), e.isShiftClick())));
					}
					else e.setCancelled(!handler.onClick((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), new ClickAction(e.isRightClick(), e.isShiftClick())));
				}
			}
			else e.setCancelled(!menu.getPlayerInventoryClickHandler().onClick((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), new ClickAction(e.isRightClick(), e.isShiftClick())));
		}
		else if (Maps.getInstance().inv.containsKey(e.getWhoClicked().getUniqueId())) {
			e.setCancelled(true);
			if (e.getCurrentItem() != null) {
				Bukkit.getPluginManager().callEvent(new MenuClickEvent((Player) e.getWhoClicked(), e.getInventory(), new ClickAction(e.isRightClick(), e.isShiftClick()), e.getSlot(), e.getCurrentItem(), e.getRawSlot()));
			}
		}
	}

}
