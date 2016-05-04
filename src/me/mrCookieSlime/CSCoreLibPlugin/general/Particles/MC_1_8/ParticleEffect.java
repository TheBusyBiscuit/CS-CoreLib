package me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_8;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;


public enum ParticleEffect {
	
	BARRIER(ParticleType.NORMAL),
	BLOCK_CRACK(ParticleType.CRACK),
	BLOCK_DUST(ParticleType.CRACK),
	CLOUD(ParticleType.NORMAL),
	CRIT(ParticleType.NORMAL),
	CRIT_MAGIC(ParticleType.NORMAL),
	DRIP_LAVA(ParticleType.NORMAL),
	DRIP_WATER(ParticleType.NORMAL),
	ENCHANTMENT_TABLE(ParticleType.NORMAL),
	EXPLOSION_HUGE(ParticleType.NORMAL),
	EXPLOSION_LARGE(ParticleType.NORMAL),
	EXPLOSION_NORMAL(ParticleType.NORMAL),
	FIREWORKS_SPARK(ParticleType.NORMAL),
	FLAME(ParticleType.NORMAL),
	FOOTSTEP(ParticleType.NORMAL),
	HEART(ParticleType.NORMAL),
	ITEM_CRACK(ParticleType.CRACK),
	ITEM_TAKE(ParticleType.NORMAL),
	LAVA(ParticleType.NORMAL),
	MOB_APPEARANCE(ParticleType.NORMAL),
	NOTE(ParticleType.NORMAL),
	PORTAL(ParticleType.NORMAL),
	REDSTONE(ParticleType.COLORED),
	SLIME(ParticleType.NORMAL),
	SMOKE_LARGE(ParticleType.NORMAL),
	SMOKE_NORMAL(ParticleType.NORMAL),
	SNOW_SHOVEL(ParticleType.NORMAL),
	SNOWBALL(ParticleType.NORMAL),
	SPELL(ParticleType.NORMAL),
	SPELL_INSTANT(ParticleType.NORMAL),
	SPELL_MOB(ParticleType.COLORED),
	SPELL_MOB_AMBIENT(ParticleType.COLORED),
	SPELL_WITCH(ParticleType.NORMAL),
	SUSPENDED(ParticleType.NORMAL),
	SUSPENDED_DEPTH(ParticleType.NORMAL),
	TOWN_AURA(ParticleType.NORMAL),
	VILLAGER_ANGRY(ParticleType.NORMAL),
	VILLAGER_HAPPY(ParticleType.NORMAL),
	WATER_BUBBLE(ParticleType.NORMAL),
	WATER_DROP(ParticleType.NORMAL),
	WATER_SPLASH(ParticleType.NORMAL),
	WATER_WAKE(ParticleType.NORMAL),
	DRAGON_BREATH(ParticleType.NORMAL),
	END_ROD(ParticleType.NORMAL),
	DAMAGE_INDICATOR(ParticleType.NORMAL),
	SWEEP_ATTACK(ParticleType.NORMAL);
	
	private Constructor<?> constructor;
	private Object constant;
	private ParticleType type;
	
	ParticleEffect(ParticleType type) {
		try {
			this.type = type;
			this.constructor = ReflectionUtils.getClass("PacketPlayOutWorldParticles").getConstructor(ReflectionUtils.getClass("EnumParticle"), boolean.class, float.class, float.class, float.class, float.class, float.class, float.class, float.class, int.class, int[].class);
			this.constant = ReflectionUtils.getEnumConstant(ReflectionUtils.getClass("EnumParticle"), toString());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
   	 * Displays a Particle assigned with the COLORED/NORMAL Type
	 *
	 * @param  l The Location where the Particle should be displayed
	 * @param  offsetX The Offset on the X-Axis
	 * @param  offsetY The Offset on the Y-Axis
	 * @param  offsetZ The Offset on the Z-Axis
	 * @param  speed The Speed of the Particle
	 * @param  amount The Amount of Particles which should be displayed
   	 */ 
	public void display(Location l, float offsetX, float offsetY, float offsetZ, float speed, int amount) throws Exception {
		this.display(l, offsetX, offsetY, offsetZ, speed, amount, getPlayers(l));
	}
	
	/**
   	 * Displays a Particle assigned with the COLORED/NORMAL Type
	 *
	 * @param  l The Location where the Particle should be displayed
	 * @param  offsetX The Offset on the X-Axis
	 * @param  offsetY The Offset on the Y-Axis
	 * @param  offsetZ The Offset on the Z-Axis
	 * @param  speed The Speed of the Particle
	 * @param  amount The Amount of Particles which should be displayed
	 * @param  players The Player who is supposed to see the Particle
   	 */ 
	public void display(Location l, float offsetX, float offsetY, float offsetZ, float speed, int amount, List<Player> players) throws Exception {
		if (type == ParticleType.CRACK) {
			System.err.println("Effect \"" + toString() + "\" cannot be displayed as its Type mismatches: " + type.toString() + " != " + ParticleType.NORMAL.toString());
			return;
		}
		Object packet = constructor.newInstance(constant, true, (float) l.getX(), (float) l.getY(), (float) l.getZ(), offsetX, offsetY, offsetZ, speed, amount, new int[0]);
    	
		for (Player p: players) {
			ReflectionUtils.sendPacket(p, packet);
    	}
	}
	
	/**
   	 * Displays a Particle assigned with the CRACK type
	 *
	 * @param  l The Location where the Particle should be displayed
	 * @param  data The Item/Block Data which should be used
	 * @param  offsetX The Offset on the X-Axis
	 * @param  offsetY The Offset on the Y-Axis
	 * @param  offsetZ The Offset on the Z-Axis
	 * @param  speed The Speed of the Particle
	 * @param  amount The Amount of Particles which should be displayed
   	 */ 
	@SuppressWarnings("deprecation")
	public void displayCrack(Location l, MaterialData data, float offsetX, float offsetY, float offsetZ, float speed, int amount) throws Exception {
		if (type != ParticleType.CRACK) {
			System.err.println("Effect \"" + toString() + "\" cannot be displayed as its Type mismatches: " + type.toString() + " != " + ParticleType.CRACK.toString());
			return;
		}
		Object packet = constructor.newInstance(constant, true, (float) l.getX(), (float) l.getY(), (float) l.getZ(), offsetX, offsetY, offsetZ, speed, amount, new int[] {data.getItemTypeId() | data.getData() << 12 });
    	
		for (Player p: getPlayers(l)) {
    		Object player = p.getClass().getMethod("getHandle").invoke(p);
    		Object connection = player.getClass().getField("playerConnection").get(player);
    		ReflectionUtils.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
    	}
	}
	
	/**
   	 * Displays a Particle with a certain Color
   	 * Note: This only works with Particles assigned with the
   	 * COLORED type
	 *
	 * @param  l The Location where the Particle should be displayed
	 * @param  color The Color your Particle should have
   	 */ 
	public void displayColoredParticle(Location l, Color color) throws Exception {
		if (type != ParticleType.COLORED) {
			System.err.println("Effect \"" + toString() + "\" cannot be displayed as its Type mismatches: " + type.toString() + " != " + ParticleType.COLORED.toString());
			return;
		}
		display(l, color.getRed() / 255, color.getGreen() / 255, color.getBlue() / 255, 1, 0);
	}
	
	/**
	 * Returns a List of Players who are in Range of the Particle
	 *
	 * @return      All Players in viewable Distance
	 */ 
	private List<Player> getPlayers(Location l) {
		return getPlayers(l, 300);
	}
	
	public static List<Player> getPlayers(Location l, int radius) {
		List<Player> players = new ArrayList<Player>(l.getWorld().getPlayers());
		
		Iterator<Player> iterator = players.iterator();
		while (iterator.hasNext()) {
			Player p = iterator.next();
			if (p.getLocation().distanceSquared(l) > radius) iterator.remove();
		}
		return players;
	}
	
	public void drawLine(Location l1, Location l2, List<Player> players) throws Exception {
		List<Axis> error = new ArrayList<Axis>();
		
		if (l1.getBlockX() != l2.getBlockX()) error.add(Axis.X);
		if (l1.getBlockY() != l2.getBlockY()) error.add(Axis.Y);
		if (l1.getBlockZ() != l2.getBlockZ()) error.add(Axis.Z);
		
		if (error.size() > 1) {
			System.err.println("[CS-CoreLib - Particles] Could not display Particle Effect \"" + toString() + "\" in a Line as more than 1 Axis mismatched");
			return;
		}
		
		switch (error.get(0)) {
		case X: {
			double deltaX = l2.getX() - l1.getX();
			display(new Location(l1.getWorld(), l1.getX() + (deltaX / 2), l1.getY(), l1.getZ()), 0.25F * Math.abs((int) deltaX), 0F, 0F, 0F, Math.abs((int) deltaX) * 6, players);
			break;
		}
		case Y: {
			double deltaY = l2.getY() - l1.getY();
			display(new Location(l1.getWorld(), l1.getX(), l1.getY() + (deltaY / 2), l1.getZ()), 0F, 0.25F * Math.abs((int) deltaY), 0F, 0F, Math.abs((int) deltaY) * 6, players);
			break;
		}
		case Z: {
			double deltaZ = l2.getZ() - l1.getZ();
			display(new Location(l1.getWorld(), l1.getX(), l1.getY(), l1.getZ() + (deltaZ / 2)), 0F, 0F, 0.25F * Math.abs((int) deltaZ), 0F, Math.abs((int) deltaZ) * 6, players);
			break;
		}
		default:
			break;
		}
	}
	
	public void drawLine(Location l1, Location l2) throws Exception {
		this.drawLine(l1, l2, getPlayers(l1));
	}
}
