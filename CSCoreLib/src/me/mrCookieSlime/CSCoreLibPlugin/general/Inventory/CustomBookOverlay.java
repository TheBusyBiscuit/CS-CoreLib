package me.mrCookieSlime.CSCoreLibPlugin.general.Inventory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.events.Listeners.CustomBookOverlay1_9;
import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage;
import me.mrCookieSlime.CSCoreLibPlugin.general.Player.PlayerInventory;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.CraftObject;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.PackageName;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class CustomBookOverlay {

	public static Set<UUID> opening = new HashSet<UUID>();

	private static Method openBook, copyBook;

	private static Object const_mainhand;

	static {
		try {
			if (ReflectionUtils.getVersion().startsWith("v1_9_")) {
				Class<?> enumhand = ReflectionUtils.getClass(PackageName.NMS, "EnumHand");
				openBook = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.NMS, "EntityPlayer"), "a", ReflectionUtils.getClass(PackageName.NMS, "ItemStack"), enumhand);
				const_mainhand = ReflectionUtils.getEnumConstant(enumhand, "MAIN_HAND");
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_10_")) {
				Class<?> enumhand = ReflectionUtils.getClass(PackageName.NMS, "EnumHand");
				openBook = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.NMS, "EntityPlayer"), "a", ReflectionUtils.getClass(PackageName.NMS, "ItemStack"), enumhand);
				const_mainhand = ReflectionUtils.getEnumConstant(enumhand, "MAIN_HAND");
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_11_")) {
				Class<?> enumhand = ReflectionUtils.getClass(PackageName.NMS, "EnumHand");
				openBook = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.NMS, "EntityPlayer"), "a", ReflectionUtils.getClass(PackageName.NMS, "ItemStack"), enumhand);
				const_mainhand = ReflectionUtils.getEnumConstant(enumhand, "MAIN_HAND");
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_12_")) {
				Class<?> enumhand = ReflectionUtils.getClass(PackageName.NMS, "EnumHand");
				openBook = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.NMS, "EntityPlayer"), "a", ReflectionUtils.getClass(PackageName.NMS, "ItemStack"), enumhand);
				const_mainhand = ReflectionUtils.getEnumConstant(enumhand, "MAIN_HAND");
			}
			else if (ReflectionUtils.getVersion().startsWith("v1_13_")) {
				Class<?> enumhand = ReflectionUtils.getClass(PackageName.NMS, "EnumHand");
				openBook = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.NMS, "EntityPlayer"), "a", ReflectionUtils.getClass(PackageName.NMS, "ItemStack"), enumhand);
				const_mainhand = ReflectionUtils.getEnumConstant(enumhand, "MAIN_HAND");
			}
			else {
				openBook = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.NMS, "EntityPlayer"), "openBook", ReflectionUtils.getClass(PackageName.NMS, "ItemStack"));
			}
			copyBook = ReflectionUtils.getMethod(ReflectionUtils.getClass(PackageName.OBC, "inventory.CraftItemStack"), "asNMSCopy", ItemStack.class);
		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	public static void load(CSCoreLib plugin) {
		if (ReflectionUtils.getVersion().startsWith("v1_9_")) {
			plugin.getServer().getPluginManager().registerEvents(new CustomBookOverlay1_9(), plugin);
		}
		else if (ReflectionUtils.getVersion().startsWith("v1_10_")) {
			plugin.getServer().getPluginManager().registerEvents(new CustomBookOverlay1_9(), plugin);
		}
		else if (ReflectionUtils.getVersion().startsWith("v1_11_")) {
			plugin.getServer().getPluginManager().registerEvents(new CustomBookOverlay1_9(), plugin);
		}
		else if (ReflectionUtils.getVersion().startsWith("v1_12_")) {
			plugin.getServer().getPluginManager().registerEvents(new CustomBookOverlay1_9(), plugin);
		}
		else if (ReflectionUtils.getVersion().startsWith("v1_13_")) {
			plugin.getServer().getPluginManager().registerEvents(new CustomBookOverlay1_9(), plugin);
		}

		plugin.getServer().getPluginManager().registerEvents(new Listener() {

			@EventHandler
			public void onDrop(PlayerDropItemEvent e) {
				if (opening.contains(e.getPlayer().getUniqueId())) e.setCancelled(true);
			}

		}, plugin);
	}

	ItemStack book;

	public CustomBookOverlay(String title, String author, TellRawMessage... pages) {
		this.book = new ItemStack(Material.WRITTEN_BOOK);

		BookMeta meta = (BookMeta) this.book.getItemMeta();
		meta.setTitle(title);
		meta.setAuthor(author);

		for (TellRawMessage page: pages) {
			try {
				addPage(meta, page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.book.setItemMeta(meta);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addPage(BookMeta meta, TellRawMessage page) throws Exception {
		Field field = ReflectionUtils.getField(ReflectionUtils.getClass(PackageName.OBC, "inventory.CraftMetaBook"), "pages");
		field.setAccessible(true);

		List pages = (List) field.get(meta);

		pages.add(ReflectionUtils.getClass(PackageName.NMS, "IChatBaseComponent").cast(page.getSerializedString()));
		field.setAccessible(false);
	}

	public void open(final Player p) {
		if (opening.contains(p.getUniqueId())) return;

		final int slot = p.getInventory().getHeldItemSlot();

		opening.add(p.getUniqueId());
		final ItemStack item = p.getInventory().getItem(slot);
		p.getInventory().setItem(slot, this.book);

		Bukkit.getScheduler().scheduleSyncDelayedTask(CSCoreLib.getLib(), new Runnable() {

			@Override
			public void run() {
				try {
					Object handle = ReflectionUtils.getHandle(CraftObject.PLAYER, p);
					Object copy = copyBook.invoke(null, book);

					if (ReflectionUtils.getVersion().startsWith("v1_8_")) {
						openBook.invoke(handle, copy);
					}
					else {
						openBook.invoke(handle, copy, const_mainhand);
					}
					p.getInventory().setItem(slot, item);
					PlayerInventory.update(p);
					opening.remove(p.getUniqueId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 1L);
	}

	public ItemStack toItemStack() {
		return this.book.clone();
	}

}
