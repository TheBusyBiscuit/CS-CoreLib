package me.mrCookieSlime.CSCoreLibPlugin.general.Particles.MC_1_13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;

@Deprecated
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
	
	private ParticleType type;
	
	ParticleEffect(ParticleType type) {
		try {
			this.type = type;
		} catch (SecurityException e) {
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

		if(toString().equals("REDSTONE")) {
			DustOptions dustOptions = new DustOptions(Color.RED, amount);
			for (Player p: players) {
				p.spawnParticle(Particle.valueOf(toString()), l, amount, offsetX, offsetY, offsetZ, speed, dustOptions);
			}
		} else {
			for (Player p: players) {
				p.spawnParticle(Particle.valueOf(toString()), l, amount, offsetX, offsetY, offsetZ, speed);
			}
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
	public void displayCrack(Location l, Material material, float offsetX, float offsetY, float offsetZ, float speed, int amount) throws Exception {
		if (type != ParticleType.CRACK) {
			System.err.println("Effect \"" + toString() + "\" cannot be displayed as its Type mismatches: " + type.toString() + " != " + ParticleType.CRACK.toString());
			return;
		}
	
		for (Player p: getPlayers(l)) {
			p.spawnParticle(Particle.valueOf(toString()), l, amount,offsetX, offsetY, offsetZ, speed, material.createBlockData());
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
		if(toString().equals("REDSTONE")) {
			DustOptions dustOptions = new DustOptions(color, 1);
			for (Player p: getPlayers(l)) {
				p.spawnParticle(Particle.valueOf(toString()), l, 1, 0, 0, 0, 1, dustOptions);
	    	}
		} else {
			display(l, color.getRed() / 255, color.getGreen() / 255, color.getBlue() / 255, 1, 0);
		}
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
			if (!p.getWorld().getUID().equals(l.getWorld().getUID()) || p.getLocation().distanceSquared(l) > radius) {
				iterator.remove();
			}
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
