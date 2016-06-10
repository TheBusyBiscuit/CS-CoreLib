package me.mrCookieSlime.CSCoreLibPlugin.general.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.mrCookieSlime.CSCoreLibPlugin.general.ListUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReflectionUtils {
	
	private static Method handle_player, handle_world, handle_entity, handle_animals, sendPacket;
	private static Field player_connection;
	private static final Map<Class<?>, Class<?>> conversion = new HashMap<Class<?>, Class<?>>();
	
	static {
		conversion.put(Byte.class, Byte.TYPE);
	    conversion.put(Short.class, Short.TYPE);
	    conversion.put(Integer.class, Integer.TYPE);
	    conversion.put(Long.class, Long.TYPE);
	    conversion.put(Character.class, Character.TYPE);
	    conversion.put(Float.class, Float.TYPE);
	    conversion.put(Double.class, Double.TYPE);
	    conversion.put(Boolean.class, Boolean.TYPE);
	    
	    try {
			handle_world = getClass(PackageName.OBC, "CraftWorld").getMethod("getHandle");
			handle_player = getClass(PackageName.OBC, "entity.CraftPlayer").getMethod("getHandle");
			handle_entity = getClass(PackageName.OBC, "entity.CraftEntity").getMethod("getHandle");
			handle_animals = getClass(PackageName.OBC, "entity.CraftAnimals").getMethod("getHandle");
			player_connection = getClass(PackageName.NMS, "EntityPlayer").getField("playerConnection");
			sendPacket = getMethod(getClass(PackageName.NMS, "PlayerConnection"), "sendPacket");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Returns a certain Method in the specified Class
	 *
	 * @param  c The Class in which the Method is in
	 * @param  method The Method you are looking for
	 * @return      The found Method
	 */ 
	public static Method getMethod(Class<?> c, String method) {
		for (Method m : c.getMethods()) {
			if (m.getName().equals(method)) return m;
		}
        return null;
	}
	
	/**
	 * Returns the Method with certain Parameters
	 *
	 * @param  c The Class in which the Method is in
	 * @param  method The Method you are looking for
	 * @param  paramTypes The Types of the Parameters
	 * @return      The found Method
	 */ 
	public static Method getMethod(Class<?> c, String method, Class<?>... paramTypes) {
	    Class<?>[] t = toPrimitiveTypeArray(paramTypes);
	    for (Method m : c.getMethods()) {
	      Class<?>[] types = toPrimitiveTypeArray(m.getParameterTypes());
	      if ((m.getName().equals(method)) && (equalsTypeArray(types, t)))
	        return m;
	    }
	    return null;
	}
	
	/**
	 * Returns the Field of a Class
	 *
	 * @param  c The Class conating this Field
	 * @param  field The name of the Field you are looking for
	 * @return      The found Field
	 * 
	 * @throws Exception
	 */ 
	public static Field getField(Class<?> c, String field) throws Exception {
		return c.getDeclaredField(field);
	}
	
	/**
	 * Modifies a Field in an Object
	 *
	 * @param  object The Object containing the Field
	 * @param  field The Name of that Field
	 * @param  value The Value for that Field
	 */ 
	public static void setFieldValue(Object object, String field, Object value) throws Exception {
		Field f = getField(object.getClass(), field);
		f.setAccessible(true);
		f.set(object, value);
	}
	
	/**
	 * Returns the Value of a Field in an Object
	 *
	 * @param  object The Object containing the Field
	 * @param  field The Name of that Field
	 * @return      The Value of a Field
	 */ 
	public static Object getFieldValue(Object object, String field) throws Exception {
	    Field f = getField(object.getClass(), field);
	    f.setAccessible(true);
	    return f.get(object);
	}
	
	/**
	 * Converts the Classes to a Primitive Type Array
	 * in order to be used as paramaters
	 *
	 * @param  classes The Types you want to convert
	 * @return      An Array of primitive Types
	 */ 
	public static Class<?>[] toPrimitiveTypeArray(Class<?>... classes) {
		int a = classes != null ? classes.length : 0;
	    Class<?>[] types = new Class[a];
	    for (int i = 0; i < a; i++) {
	    	types[i] = conversion.containsKey(classes[i]) ? conversion.get(classes[i]): classes[i];
	    }
	    return types;
	}
	
	/**
	 * Converts the Classes of the specified Objects
	 *  to a Primitive Type Array
	 * in order to be used as paramaters
	 *
	 * @param  objects The Types you want to convert
	 * @return      An Array of primitive Types
	 */
	public static Class<?>[] toPrimitiveTypeArray(Object... objects) {
	    int a = objects != null ? objects.length : 0;
	    Class<?>[] types = new Class[a];
	    for (int i = 0; i < a; i++)
	    	types[i] = conversion.containsKey(objects[i].getClass()) ? conversion.get(objects[i].getClass()): objects[i].getClass();
	    return types;
	}
	
	/**
	 * Returns the Constructor of a Class with the specified Parameters
	 *
	 * @param  c The Class containing the Constructor
	 * @param  paramTypes The Parameters for that Constructor
	 * @return      The Constructor for that Class
	 */
	public static Constructor<?> getConstructor(Class<?> c, Class<?>... paramTypes) {
	    Class<?>[] t = toPrimitiveTypeArray(paramTypes);
	    for (Constructor<?> con : c.getConstructors()) {
	    	Class<?>[] types = toPrimitiveTypeArray(con.getParameterTypes());
	    	if (equalsTypeArray(types, t)) return con;
	    }
	    return null;
	}
	
	/**
	 * Shortcut for NMS Classes
	 *
	 * @param  name The Name of the Class you are looking for
	 * @return      The Class with that name in the NMS Package
	 */
	public static Class<?> getClass(String name) throws Exception {
	    return getClass(PackageName.NMS, name);
	}
	
	/**
	 * Returns an Instance of the NMS class for your Object
	 *
	 * @param  type The Type of NMS Class you want to get
	 * @param  object The Object you want to get the Handle of
	 * @return      An Instance of the NMS class of your Object
	 * @throws Exception 
	 */
	public static Object getHandle(CraftObject type, Object object) throws Exception {
	    switch(type) {
		case PLAYER:
		    return handle_player.invoke(object);
		case WORLD:
		    return handle_world.invoke(object);
		case ENTITY:
		    return handle_entity.invoke(object);
		case ANIMALS:
		    return handle_animals.invoke(object);
		default:
		    return null;
	    }
	}
	
	/**
	 * Sends a Packet to the specified Player
	 *
	 * @param  p The Player you want to send the Packet to
	 * @param  packet The Packet you want to send
	 * @throws Exception 
	 */
	public static void sendPacket(Player p, Object packet) throws Exception {
	    try {
	    	sendPacket.invoke(player_connection.get(getHandle(CraftObject.PLAYER, p)), packet);
	    } catch(NullPointerException x) {
	    	x.printStackTrace();
	    	System.err.println("Packet Method: " + sendPacket);
	    	System.err.println("Player Connection: " + player_connection);
	    	System.err.println("Player Handle: " + getHandle(CraftObject.PLAYER, p));
	    	System.err.println("Packet: " + packet);
	    }
	}
	
	/**
	 * Returns a Class inside a Class
	 *
	 * @param  path The Package in which the Class can be found in
	 * @param  name The Name of the Class your Inner class is located in
	 * @param  subname The Name of the inner Class you are looking for
	 * @return      The Class in your specified Class
	 */
	public static Class<?> getInnerClass(PackageName path, String name, String subname) throws Exception {
		return getClass(path, name + "$" + subname);
	}
	
	/**
	 * Returns an OBC/NMS Class via Reflection
	 *
	 * @param  path The Types of the Package you are targeting
	 * @param  name The Name of the Class you are looking for
	 * @return      The Class in that Package
	 */
	public static Class<?> getClass(PackageName path, String name) throws Exception {
	    return Class.forName(new StringBuilder().append(path.toPackage()).append(getVersion()).append(".").append(name).toString());
	}
	
	/**
	 * Returns an OBC/NMS Class via Reflection
	 *
	 * @param  path The Types of the Package you are targeting
	 * @param  names The Names of the Classes you are looking for
	 * @return      The Class in that Package
	 */
	public static Class<?> tryClass(PackageName path, String... names) throws Exception {
	    for (String name: names) {
	    	try {
	    		Class<?> c = Class.forName(new StringBuilder().append(path.toPackage()).append(getVersion()).append(".").append(name).toString());
	    		return c;
	    	} catch(ClassNotFoundException x) {
	    	}
	    }
	    System.err.println("[CS-CoreLib - Reflection] Could not find Class(es): \"" + ListUtils.toString(names) + "\"");
	    return null;
	}
	
	/**
	 * Returns a Class's field via Reflection
	 *
	 * @param  c The class you are targeting
	 * @param  names The Names of the fields you are looking for
	 * @return      The field in that class
	 */
	public static Field tryField(Class<?> c, String... names) throws Exception {
	    for (String name: names) {
	    	try {
	    		Field f = c.getDeclaredField(name);
	    		if (f != null) return f;
	    	} catch(NoSuchFieldException x) {
	    	}
	    }
	    System.err.println("[CS-CoreLib - Reflection] Could not find Field(s): \"" + ListUtils.toString(names) + "\" in Class " + c.getName());
	    return null;
	}
	
	/**
	 * Returns a Class's Method via Reflection
	 *
	 * @param  c The class you are targeting
	 * @param  names The Names of the methods you are looking for
	 * @param  params The Parameters of your Method
	 * @return      The field in that class
	 */
	public static Method tryMethod(Class<?> c, String[] names, Class<?>... params) throws Exception {
	    for (String name: names) {
	    	try {
	    		Method m = getMethod(c, name, params);
	    		if (m != null) return m;
	    	} catch(Exception x) {
	    	}
	    }
	    System.err.println("[CS-CoreLib - Reflection] Could not find Method(s): \"" + ListUtils.toString(names) + "\" in Class " + c.getName());
	    return null;
	}
	
	/**
	 * Returns the formatted Server Version usable for Reflection
	 *
	 * @return      The formatted Server Version
	 */
	public static String getVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(".") + 1);
	}

	/**
	 * Compares multiple Type Arrays
	 *
	 * @param  a The first Array for comparison
	 * @param  o All following Arrays you want to compare
	 * @return      Whether they equal each other
	 */
	private static boolean equalsTypeArray(Class<?>[] a, Class<?>... o) {
	    if (a.length != o.length)
	      return false;
	    for (int i = 0; i < a.length; i++)
	      if ((!a[i].equals(o[i])) && (!a[i].isAssignableFrom(o[i])))
	        return false;
	    return true;
	}
	
	/**
	 * Returns all Enum Constants in an Enum
	 *
	 * @param  c The Enum you are targeting
	 * @return      An ArrayList of all Enum Constants in that Enum
	 */
	public static List<?> getEnumConstants(Class<?> c) {
		return Arrays.asList(c.getEnumConstants());
	}
	
	/**
	 * Returns a specific Enum Constant in an Enum
	 *
	 * @param  c The Enum you are targeting
	 * @param  name The Name of the Constant you are targeting
	 * @return      The found Enum Constant
	 */
	public static Object getEnumConstant(Class<?> c, String name) {
		for (Object o: c.getEnumConstants()) {
			if (o.toString().equals(name)) return o;
		}
		return null;
	}
}
