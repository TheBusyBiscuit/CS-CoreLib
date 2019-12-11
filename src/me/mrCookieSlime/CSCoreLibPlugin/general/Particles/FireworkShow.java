package me.mrCookieSlime.CSCoreLibPlugin.general.Particles;

import java.lang.reflect.InvocationTargetException;

import me.mrCookieSlime.CSCoreLibPlugin.CSCoreLib;
import me.mrCookieSlime.CSCoreLibPlugin.general.Reflection.ReflectionUtils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

@Deprecated
public class FireworkShow {
	
	public static void launchFirework(Location l, Color color) {
		Firework fw = (Firework)l.getWorld().spawnEntity(l, EntityType.FIREWORK);
		FireworkMeta meta = fw.getFireworkMeta();
	    FireworkEffect effect = FireworkEffect.builder().flicker(CSCoreLib.randomizer().nextBoolean()).withColor(color).with(CSCoreLib.randomizer().nextInt(3) + 1 == 1 ? Type.BALL: Type.BALL_LARGE).trail(CSCoreLib.randomizer().nextBoolean()).build();
	    meta.addEffect(effect);
	    meta.setPower(CSCoreLib.randomizer().nextInt(2) + 1);
	    fw.setFireworkMeta(meta);
	}
	
	public static Firework createFirework(Location l, Color color) {
		Firework fw = (Firework)l.getWorld().spawnEntity(l, EntityType.FIREWORK);
		FireworkMeta meta = fw.getFireworkMeta();
	    FireworkEffect effect = FireworkEffect.builder().flicker(CSCoreLib.randomizer().nextBoolean()).withColor(color).with(CSCoreLib.randomizer().nextInt(3) + 1 == 1 ? Type.BALL: Type.BALL_LARGE).trail(CSCoreLib.randomizer().nextBoolean()).build();
	    meta.addEffect(effect);
	    meta.setPower(CSCoreLib.randomizer().nextInt(2) + 1);
	    fw.setFireworkMeta(meta);
	    return fw;
	}
	
	public static void launchRandom(Player p, int amount) {
		for (int i = 0; i < amount; i++) {
			Location l = p.getLocation().clone();
			l.setX(l.getX() + CSCoreLib.randomizer().nextInt(amount));
			l.setX(l.getX() - CSCoreLib.randomizer().nextInt(amount));
			l.setZ(l.getZ() + CSCoreLib.randomizer().nextInt(amount));
			l.setZ(l.getZ() - CSCoreLib.randomizer().nextInt(amount));
			
            launchFirework(l, getColors()[CSCoreLib.randomizer().nextInt(getColors().length)]);
		}
	}
	
	public static Color[] getColors() {
		return new Color[] {Color.AQUA, Color.BLACK, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.MAROON, Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE, Color.RED, Color.SILVER, Color.TEAL, Color.WHITE, Color.YELLOW};
	}
	
	public static void playEffect(Location l, Color color) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Firework fw = l.getWorld().spawn(l, Firework.class);
		Object worldObject = ReflectionUtils.getMethod(l.getWorld().getClass(), "getHandle").invoke(l.getWorld(),(Object[]) null);
		
		FireworkMeta meta = fw.getFireworkMeta();
		meta.addEffect(FireworkEffect.builder().with(Type.BURST).flicker(false).trail(false).withColor(color).withFade(Color.WHITE).build());
		fw.setFireworkMeta(meta);
		
		ReflectionUtils.getMethod(worldObject.getClass(), "broadcastEntityEffect").invoke(worldObject, new Object[] {ReflectionUtils.getMethod(fw.getClass(), "getHandle").invoke(fw, (Object[]) null), (byte) 17});
		fw.remove();
	}
}
