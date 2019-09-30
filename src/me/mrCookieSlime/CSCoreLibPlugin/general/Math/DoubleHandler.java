package me.mrCookieSlime.CSCoreLibPlugin.general.Math;

import java.text.DecimalFormat;

@Deprecated
public final class DoubleHandler {
	
	private DoubleHandler() {}
	
	public static String getFancyDouble(double d) {
		DecimalFormat format = new DecimalFormat("##.##");
		
		double d2 = d / 1000000000000000d;
		if (d2 > 1) return format.format(d2).replace(",", ".") + "Q";
		
		d2 = d / 1000000000000d;
		if (d2 > 1) return format.format(d2).replace(",", ".") + "T";
		
		d2 = d / 1000000000d;
		if (d2 > 1) return format.format(d2).replace(",", ".") + "B";
		
		d2 = d / 1000000d;
		if (d2 > 1) return format.format(d2).replace(",", ".") + "M";
		
		d2 = d / 1000d;
		if (d2 > 1) return format.format(d2).replace(",", ".") + "K";
		
		return format.format(d).replace(",", ".");
	}
	
	public static double fixDouble(double amount, int digits) {
		if (digits == 0) return (int) amount;
		StringBuilder format = new StringBuilder("##");
		for (int i = 0; i < digits; i++) {
			if (i == 0) format.append(".");
			format.append("#");
		}
		return Double.valueOf(new DecimalFormat(format.toString()).format(amount).replace(",", "."));
	}
	
	public static double fixDouble(double amount) {
		return fixDouble(amount, 2);
	}

}
