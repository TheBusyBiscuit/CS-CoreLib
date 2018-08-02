package me.mrCookieSlime.CSCoreLibPlugin.general.World;

import java.lang.reflect.Constructor;

import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.entity.Player;

public class TabMessage extends TellRawMessage {
	
	private static Constructor<?> constructor;
	
	static {
		try {
			constructor = ReflectionUtils.getClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(ReflectionUtils.getClass("IChatBaseComponent"));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
   	 * Sends Title to the specified
   	 * Players
   	 *
   	 * @param  players All Players who should receive the Title
   	 * @throws Exception
   	 */ 
	public void send(TabMessage footer, Player... players) throws Exception {
		Object packet = constructor.newInstance(getSerializedString());
		if (footer != null) ReflectionUtils.setFieldValue(packet, "b", footer.getSerializedString());
    	for (Player p: players) {
    		ReflectionUtils.sendPacket(p, packet);
    	}
    }
	
	public enum TabType {
		HEADER,
		FOOTER;
	}

}
