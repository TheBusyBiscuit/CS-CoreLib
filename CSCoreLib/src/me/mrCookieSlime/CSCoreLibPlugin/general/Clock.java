package me.mrCookieSlime.CSCoreLibPlugin.general;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Clock {
	
	public static Date getCurrentDate() {
		return new Date();
	}
	
	public static String getFormattedTime() {
		return format(getCurrentDate());
	}
	
	public static String format(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(date);
	}
	
	public static Date getFutureDate(int days, int hours, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getCurrentDate());
		calendar.add(Calendar.DATE, days);
		calendar.add(Calendar.HOUR, hours);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

}
