package com.tf414.app.rsseditor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeConv {
	private TimeConv() {
		throw new IllegalAccessError("Tools must not have instances.");
	}
	
	public static void main(String[] args) {
		System.out.println(strToDate("Sun, 01 Sep 2019 08:16:16 GMT"));
	}
	
	public static Date strToDate(String time) {
		time = time.split(", ")[1];
		if (time != null && time.trim().length() > 0) {
            time = time.substring(4,24).replace(" ","/");
            time = time.replace("Jan","01");
            time = time.replace("Feb","02");
            time = time.replace("Mar","03");
            time = time.replace("Apr","04");
            time = time.replace("May","05");
            time = time.replace("Jun","06");
            time = time.replace("Jul","07");
            time = time.replace("Aug","08");
            time = time.replace("Sep","09");
            time = time.replace("Oct","10");
            time = time.replace("Nov","11");
            time = time.replace("Dec","12");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/HH:mm:ss GMT");
            Date date;
			try {
				date = sdf.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
            return date;
        }
        return null;
	}
}
