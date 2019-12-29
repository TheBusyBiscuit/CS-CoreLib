package me.mrCookieSlime.CSCoreLibPlugin.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class ItemUseEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	PlayerInteractEvent e;
	Player p;
	ItemStack i;
	Block b;
	boolean cancelled;
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	@Deprecated
	public ItemUseEvent(Player p, ItemStack item, Block clicked) {
		this.p = p;
		this.i = item;
		this.b = clicked;
		this.e = null;
	}
	
	public ItemUseEvent(PlayerInteractEvent e, Block clicked) {
		this.p = e.getPlayer();
		this.i = e.getItem();
		this.b = clicked;
		this.e = e;
	}
	public ItemUseEvent(PlayerInteractEvent e, ItemStack item, Block clicked) {
		this.p = e.getPlayer();
		this.i = item;
		this.b = clicked;
		this.e = e;
	}
	
	public Player getPlayer() {
		return this.p;
	}
	
	public ItemStack getItem() {
		return this.i;
	}
	
	public Block getClickedBlock() {
		return this.b;
	}
	
	public PlayerInteractEvent getParentEvent() {
		return this.e;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

}
