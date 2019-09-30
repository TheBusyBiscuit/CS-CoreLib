package me.mrCookieSlime.CSCoreLibPlugin.general.Player;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.InvUtils;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class PlayerInventory {
	
	@SuppressWarnings("deprecation")
	public static void consumeItemInHand(Player p) {
		if (p.getGameMode() != GameMode.CREATIVE) {
	 		ItemStack item = p.getItemInHand().clone();
	 		item.setAmount(item.getAmount() - 1);
	  		p.setItemInHand(item.getAmount() > 0 ? item: null);
		}
	}
	
	public static void removeItemIgnoringMetaAndDamage(Player p, Material Item, int Amount) {
		ItemStack[] contents = p.getInventory().getContents();
		
		 for (int i = 0; i < Amount; i++) {
			 for (int j = 0; j < contents.length; j++) {
				 if (contents[j] != null) {
					 if (contents[j].getType() == Item) {
						 if (contents[j].getAmount() > 1) contents[j].setAmount(contents[j].getAmount() - 1);
						 else p.getInventory().removeItem(contents[j]);
						 break;
					 }
				 }
			 }
		 }
	}
	
	public static void removeItemIgnoringMeta(Player p, Material Item, int Amount) {
		ItemStack[] contents = p.getInventory().getContents();
		
		 for (int i = 0; i < Amount; i++) {
			 for (int j = 0; j < contents.length; j++) {
				 if (contents[j] != null) {
					 if (contents[j].getType() == Item && contents[j].getDurability() == 0) {
						 if (contents[j].getAmount() > 1) contents[j].setAmount(contents[j].getAmount() - 1);
						 else p.getInventory().removeItem(contents[j]);
						 break;
					 }
				 }
			 }
		 }
	}
	
	public static void removeItemIgnoringMeta(Player p, Material Item, int Amount, int durability) {
		ItemStack[] contents = p.getInventory().getContents();
		
		 for (int i = 0; i < Amount; i++) {
			 for (int j = 0; j < contents.length; j++) {
				 if (contents[j] != null) {
					 if (contents[j].getType() == Item && contents[j].getDurability() == durability) {
						 if (contents[j].getAmount() > 1) contents[j].setAmount(contents[j].getAmount() - 1);
						 else p.getInventory().removeItem(contents[j]);
						 break;
					 }
				 }
			 }
		 }
	}
	
	public static void removeItem(Player p, ItemStack item, int Amount) {
		ItemStack[] contents = p.getInventory().getContents();
		
		 for (int i = 0; i < Amount; i++) {
			 for (int j = 0; j < contents.length; j++) {
				 if (contents[j] != null) {
					 if (contents[j].getType() == item.getType() && contents[j].getDurability() == item.getDurability()) {
						 if (contents[j].getAmount() > 1) contents[j].setAmount(contents[j].getAmount() - 1);
						 else p.getInventory().removeItem(contents[j]);
						 break;
					 }
				 }
			 }
		 }
	}
	
	@SuppressWarnings("deprecation")
	public static void update(Player p) {
		 p.updateInventory();
		 // TODO: Switch to new updateInventory Method once implemented
	}
	
	@SuppressWarnings("deprecation")
	public static void damageItemInHand(Player p) {
		if (p.getGameMode() != GameMode.CREATIVE) {
			ItemStack item = p.getItemInHand().clone();
			
			item.setDurability((short) (item.getDurability() + 1));
			p.setItemInHand(item.getDurability() < item.getType().getMaxDurability() ? item: null);
		}
	}
	
	public static void giveItem(Player p, ItemStack item) {
		if (InvUtils.fits(p.getInventory(), item)) p.getInventory().addItem(item);
		else p.getWorld().dropItemNaturally(p.getLocation(), item);
	}

}
