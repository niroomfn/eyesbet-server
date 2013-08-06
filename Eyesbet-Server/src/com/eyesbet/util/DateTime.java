 package com.eyesbet.util;
 
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.TimeZone;
 import java.util.concurrent.TimeUnit;
 
 public class DateTime
 {
  
	 public final static String dateFormat = "EEEE MMM dd yyyy hh:mm a";
   private static SimpleDateFormat usOnlyDateFormat = new SimpleDateFormat("MM/dd/yyyy");
   private static SimpleDateFormat usFormat = new SimpleDateFormat(dateFormat);
   private static SimpleDateFormat feedFormat = new SimpleDateFormat("yyyy-MM-dd");
   
   static {
	   
	   usFormat.setTimeZone(TimeZone.getTimeZone("US/Pacific"));
	   feedFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
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
 
   
 
 
  
 
   public static String convertDateToFeedDate(Date date)
   {
     return feedFormat.format(date);
   }
 
   public static String convertDateToFeedDate(String date)
   {
     try
     {
       return feedFormat.format(usFormat.parse(date));
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

