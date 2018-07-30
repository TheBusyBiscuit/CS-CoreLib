package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DroppingChestMenu extends ChestMenu {
	
	public DroppingChestMenu(String title, final int... dropping) {
		super(title);
		this.addMenuCloseHandler(new MenuCloseHandler() {
			
			@Override
			public void onClose(Player p) {
				for (int slot: dropping) {
					ItemStack item = getItemInSlot(slot);
					if (item != null) p.getWorld().dropItemNaturally(p.getEyeLocation(), item);
				}
			}
		});
	}

}
