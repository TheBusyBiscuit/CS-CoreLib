package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item;

import java.util.Comparator;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItemSerializer.ItemFlag;

public class ItemFlagComparator implements Comparator<ItemFlag> {

	@Override
	public int compare(ItemFlag flag1, ItemFlag flag2) {
		return flag1.getWeight() - flag2.getWeight();
	}

}
