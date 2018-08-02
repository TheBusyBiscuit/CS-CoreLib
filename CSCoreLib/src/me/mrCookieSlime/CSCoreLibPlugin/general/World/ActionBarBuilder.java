package me.mrCookieSlime.CSCoreLibPlugin.general.World;

import java.lang.reflect.Constructor;

import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.entity.Player;

public class ActionBarBuilder extends TellRawMessage {
	
	private static Constructor<?> constructor;
	
	static {
		try {
			constructor = ReflectionUtils.getClass("PacketPlayOutChat").getConstructor(ReflectionUtils.getClass("IChatBaseComponent"), byte.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
   	 * Sends the ActionBar Message to the specified
   	 * Players
   	 *
   	 * @param  players All Players who should receive the ActionBar Message
   	 * @throws Exception
   	 */ 
	@Override
	public void send(Player... players) throws Exception {
		Object packet = constructor.newInstance(getSerializedString(), (byte) 2);
    	for (Player p: players) {
    		ReflectionUtils.sendPacket(p, packet);
    	}
	}
}
