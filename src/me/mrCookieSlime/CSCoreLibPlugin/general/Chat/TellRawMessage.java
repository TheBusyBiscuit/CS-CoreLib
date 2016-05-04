package me.mrCookieSlime.CSCoreLibPlugin.general.Chat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.mrCookieSlime.CSCoreLibPlugin.PlayerRunnable;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.PackageName;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public class TellRawMessage {
	
	private static final List<ChatColor> special = Arrays.asList(ChatColor.ITALIC, ChatColor.MAGIC, ChatColor.BOLD, ChatColor.UNDERLINE, ChatColor.STRIKETHROUGH);
	private List<String> text = new ArrayList<String>();
	
	private static Constructor<?> constructor;
	private static Class<?> serializer;
	private static Method method;
	
	static {
		try {
			constructor = ReflectionUtils.getClass("PacketPlayOutChat").getConstructor(ReflectionUtils.getClass("IChatBaseComponent"));
			serializer = ReflectionUtils.tryClass(PackageName.NMS, "ChatSerializer", "IChatBaseComponent$ChatSerializer");
			if (ReflectionUtils.getVersion().startsWith("v1_9_")) {
				method = serializer.getMethod("b", String.class);
			}
			else {
				method = serializer.getMethod("a", String.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public enum ClickAction {
        RUN_COMMAND,
        SUGGEST_COMMAND,
        OPEN_URL,
        CHANGE_PAGE;
    }
	
    public enum HoverAction {
        SHOW_TEXT,
        SHOW_ITEM,
        SHOW_ENTITY,
        SHOW_ACHIEVEMENT;
    }
    
    /**
   	 * Creates a new Instance
   	 */ 
    public TellRawMessage() {
    }
    
    /**
   	 * Creates a new Instance and adds some Text
	 *
	 * @param  text The Text you want to add
   	 */ 
    public TellRawMessage(String text) {
    	addText(text);
    }
    
    /**
	 * Returns a String Object containing the previously
	 * defined JSON fragments
	 *
	 * @return      Converted JSON String
	 */ 
    public String build() {
        String extra = "";
    	if (text.size() > 0) {
    		StringBuilder extras = new StringBuilder();
    		for (String msg: text) {
        		extras.append(msg + ",");
        	}
    		extra = extras.toString().substring(0, extras.toString().length() - 1);
    	}
    	return "{text:\"\",extra:[" + extra + "]}";
    }
    
    /**
	 * Adds the specified Line of Text to your JSON Message
	 *
	 * @param  text The Text you want to add
	 * @return      This Instance
	 */ 
    public TellRawMessage addText(String text) {
        this.text.add("{text:\"" + fixString(text) + "\"}");
        return this;
    }
    
    private String fixString(String text) {
		return JSONObject.escape(text);
	}

	/**
	 * Adds a Text to the Message depending on the Users
	 * Language
	 *
	 * @param  key The Key which is found in the .lang File
	 * @return      This Instance
	 */ 
    public TellRawMessage addTranslation(String key) {
        this.text.add("{translate:" + key + "}");
        return this;
    }
    
    /**
	 * Colors the previously added fragment
	 *
	 * @param  color The Color you want to add
	 * @return      This Instance
	 */ 
    public TellRawMessage color(ChatColor color) {
    	append(special.contains(color) ? color.name().toLowerCase() + ":true" : "color:" + color.name().toLowerCase());
        return this;
    }
    
    /**
	 * Adds a ClickEvent to the previously added
	 * fragment
	 *
	 * @param  action The Action that will be run
	 * @param  value The Value for that Action
	 * @return      This Instance
	 */ 
    public TellRawMessage addClickEvent(ClickAction action, String value) {
    	 append("clickEvent:{action:" + action.toString().toLowerCase() + ",value:\"" + fixString(value) + "\"}");
         return this;
    }
    
    /**
	 * Adds a ClickEvent to the previously added
	 * fragment
	 *
	 * @param  runnable The Runnable that will be run
	 * @return      This Instance
	 */ 
    public TellRawMessage addClickEvent(PlayerRunnable runnable) {
    	 append("clickEvent:{action:run_command,value:\"" + "/cs_triggerinterface " + runnable.getID() + "\"}");
         return this;
    }
    
    /**
	 * Adds a HoverEvent to the previously added
	 * fragment
	 *
	 * @param  action The Action that will be run
	 * @param  value The Value for that Action
	 * @return      This Instance
	 */ 
    public TellRawMessage addHoverEvent(HoverAction action, String value) {
    	 append("hoverEvent:{action:" + action.toString().toLowerCase() + ",value:\"" + fixString(value) + "\"}");
         return this;
    }
    
    /**
   	 * Appends a Color or an Event to the previous fragment
   	 *
   	 * @param  addition The Color or Event that will be appended to the previous fragment
   	 */ 
    private void append(String addition) {
        String last = text.get(text.size() - 1);
        last = last.substring(0, last.length() - 1)  + "," + addition + "}";
        text.remove(text.size() - 1);
        text.add(last);
    }
    
    /**
	 * Prepares the JSON Message to be sent
	 * 
	 * @return      The serialized String
     * @throws Exception 
	 */ 
    public Object getSerializedString() throws Exception {
    	String string = build();
    	try {
    		Object obj = method.invoke(serializer, string);
    		return obj;
    	} catch(Exception x) {
    		System.err.println(string);
    		throw x;
    	}
	}
 
    /**
   	 * Sends the JSON Message to the specified
   	 * Players
   	 *
   	 * @param  players All Players who should receive the Message
   	 * @throws Exception
   	 */ 
    public void send(Player... players) throws Exception {
    	Object packet = constructor.newInstance(getSerializedString());
    	for (Player p: players) {
    		ReflectionUtils.sendPacket(p, packet);
    	}
    }

}
