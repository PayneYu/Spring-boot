package com.beike.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static void main(String[] args) throws Exception {    
        System.out.println(getStartTime());    
        System.out.println(getEndTime());    
    }
	
	private  static final long ONE_SECOND = 1000L;
	
	public static Long getStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(new Date());
		todayStart.add(Calendar.MONTH, -1);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTimeInMillis()/ONE_SECOND;
	}

	public static Long getEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(new Date());
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTimeInMillis()/ONE_SECOND;
	}
}
