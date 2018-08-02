package me.mrCookieSlime.CSCoreLibPlugin.general.Math;

import java.text.DecimalFormat;

import org.bukkit.Location;

public class Calculator {
	
	public static int formToLine(int i) {
		int lines = 1;
		
		if (i > 9) lines++;
		if (i > 9*2) lines++;
		if (i > 9*3) lines++;
		if (i > 9*4) lines++;
		if (i > 9*5) lines++;
		if (i > 9*6) lines++;
		
		return lines;
	}
	
	public static Location centerPosition(Location l ) {
		
		double x = l.getX();
		double z = l.getZ();
		
		String[] rawX = String.valueOf(x).split(".");
		String[] rawZ = String.valueOf(z).split(".");
		
		String newX = rawX[0] + ".5";
		String newZ = rawZ[0] + ".5";
		
		l.setX(Double.parseDouble(newX));
		l.setZ(Double.parseDouble(newZ));
		
		return l;
	}
	
	@Deprecated
	public static double fixDouble(double amount, int digits) {
		if (digits == 0) return (int) amount;
		StringBuilder format = new StringBuilder("##");
		for (int i = 0; i < digits; i++) {
			if (i == 0) format.append(".");
			format.append("#");
		}
		return Double.valueOf(new DecimalFormat(format.toString()).format(amount).replace(",", "."));
	}
	
	@Deprecated
	public static double fixDouble(double amount) {
		return fixDouble(amount, 2);
	}

}
