package me.mrCookieSlime.CSCoreLibPlugin.general.World;

import java.lang.reflect.Constructor;

import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.PackageName;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.entity.Player;

@Deprecated
public class TitleBuilder extends TellRawMessage {
	
	private static Constructor<?> constructor;
	private static Object times;
	private static Class<?> titleenum;
	
	static {
		try {
			constructor = ReflectionUtils.getClass("PacketPlayOutTitle").getConstructor(ReflectionUtils.tryClass(PackageName.NMS, "EnumTitleAction", "PacketPlayOutTitle$EnumTitleAction"), ReflectionUtils.getClass("IChatBaseComponent"), int.class, int.class, int.class);
			titleenum = ReflectionUtils.tryClass(PackageName.NMS, "EnumTitleAction", "PacketPlayOutTitle$EnumTitleAction");
			times = ReflectionUtils.getEnumConstant(titleenum, "TIMES");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	int fadeIn, fadeOut, stay;
	
	/**
   	 * Creates a new TitleBuilder Instance with the given timings
   	 *
   	 * @param  fadeIn The Time it takes to fade in (in Ticks)
   	 * @param  stay  The Time it stays on the Screen (in Ticks)
   	 * @param  fadeOut The Time it takes to fade out (in Ticks)
   	 */ 
	public TitleBuilder(int fadeIn, int stay, int fadeOut) {
		this.fadeIn = fadeIn;
		this.stay = stay;
		this.fadeOut = fadeOut;
	}
	
	public enum TitleType {
		TITLE,
		SUBTITLE;
	}
	
	/**
   	 * Sends Title to the specified
   	 * Players
   	 *
   	 * @param  players All Players who should receive the Title
   	 * @throws Exception
   	 */ 
	public void send(TitleType type, Player... players) throws Exception {
		Object packet = constructor.newInstance(times, null, fadeIn, stay, fadeOut);
    	Object packet2 = constructor.newInstance(ReflectionUtils.getEnumConstant(titleenum, type.toString()), getSerializedString(), -1, -1, -1);
    	
    	for (Player p: players) {
    		ReflectionUtils.sendPacket(p, packet);
    		ReflectionUtils.sendPacket(p, packet2);
    	}
    }
}
