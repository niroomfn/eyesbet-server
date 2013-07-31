 package com.eyesbet.util;
 
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.TimeZone;
 import java.util.concurrent.TimeUnit;
 
 public class DateTime
 {
  
	 public final static String dateFormat = "EEEE MMM dd yyyy hh:mm a";
   private static SimpleDateFormat usOnlyDateFormat = new SimpleDateFormat("MM/dd/yyyy");
   private static SimpleDateFormat usFormat = new SimpleDateFormat(dateFormat);
   private static SimpleDateFormat feedFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
   private static Calendar calendar = Calendar.getInstance();
   
   static {
	   
	   usFormat.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
   }
 
   public static Date convertToUSOnlyDate(String date)
   {
     try
     {
       return usOnlyDateFormat.parse(date);
       
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
 
     return null;
   }
 
   public static Date convertToUSDate(String date)
   {
     try
     {
       return usFormat.parse(date);
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
 
     return null;
   }
 
   public static boolean before(String date)
   {
     try
     {
       Date d = usOnlyDateFormat.parse(date);
       Date now = usOnlyDateFormat.parse(usOnlyDateFormat.format(new Date()));
       if (d.before(now)) return true; 
     }
     catch (ParseException e)
     {
       e.printStackTrace();
     }
 
     return false;
   }
 
   public static boolean after(String date)
   {
     try
     {
       Date d = usOnlyDateFormat.parse(date);
       Date now = usOnlyDateFormat.parse(usOnlyDateFormat.format(new Date()));
 
       if (d.after(now)) return true; 
     }
     catch (ParseException e)
     {
       e.printStackTrace();
     }
 
     return false;
   }
 
   public static String convertTodayToFeedDate()
   {
     Date now = new Date();
     calendar.setTime(now);
     calendar.add(5, 1);
 
     return feedFormat.format(calendar.getTime());
   }
 
   public static String convertDateToFeedDate(Date date)
   {
     feedFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
     String str = feedFormat.format(date);
     return str.substring(0, str.indexOf(" "));
   }
 
   public static String convertDateToFeedDate(String date)
   {
     try
     {
       feedFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
       String str = feedFormat.format(usFormat.parse(date));
       return str.substring(0, str.indexOf(" "));
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
 
     return "";
   }
   
   
   
 
   
   
 
   public static String getDefaultTimeZone()
   {
     String timezoneId = "21";
     TimeZone zone = TimeZone.getTimeZone("America/Los_Angeles");
     if (TimeUnit.MILLISECONDS.toHours(zone.getDSTSavings()) == 1L) {
       timezoneId = "20";
     }
 
     return timezoneId;
   }
 }

