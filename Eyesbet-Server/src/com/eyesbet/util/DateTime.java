/*     */ package com.eyesbet.util;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ public class DateTime
/*     */ {
/*  12 */   private static SimpleDateFormat usOnlyDateFormat = new SimpleDateFormat("MM/dd/yyyy");
/*  13 */   private static SimpleDateFormat usFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
/*  14 */   private static SimpleDateFormat feedFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/*  15 */   private static Calendar calendar = Calendar.getInstance();
/*     */ 
/*     */   public static Date convertToUSOnlyDate(String date)
/*     */   {
/*     */     try
/*     */     {
/*  25 */       return usOnlyDateFormat.parse(date);
/*     */     }
/*     */     catch (ParseException e) {
/*  28 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  31 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date convertToUSDate(String date)
/*     */   {
/*     */     try
/*     */     {
/*  38 */       return usFormat.parse(date);
/*     */     }
/*     */     catch (ParseException e) {
/*  41 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean before(String date)
/*     */   {
/*     */     try
/*     */     {
/*  51 */       Date d = usOnlyDateFormat.parse(date);
/*  52 */       Date now = usOnlyDateFormat.parse(usOnlyDateFormat.format(new Date()));
/*  53 */       if (d.before(now)) return true; 
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/*  56 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean after(String date)
/*     */   {
/*     */     try
/*     */     {
/*  67 */       Date d = usOnlyDateFormat.parse(date);
/*  68 */       Date now = usOnlyDateFormat.parse(usOnlyDateFormat.format(new Date()));
/*     */ 
/*  70 */       if (d.after(now)) return true; 
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/*  73 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   public static String convertTodayToFeedDate()
/*     */   {
/*  84 */     Date now = new Date();
/*  85 */     calendar.setTime(now);
/*  86 */     calendar.add(5, 1);
/*     */ 
/*  89 */     return feedFormat.format(calendar.getTime());
/*     */   }
/*     */ 
/*     */   public static String convertDateToFeedDate(Date date)
/*     */   {
/*  95 */     feedFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
/*  96 */     String str = feedFormat.format(date);
/*  97 */     return str.substring(0, str.indexOf(" "));
/*     */   }
/*     */ 
/*     */   public static String convertDateToFeedDate(String date)
/*     */   {
/*     */     try
/*     */     {
/* 104 */       feedFormat.setTimeZone(TimeZone.getTimeZone("GMT+2"));
/* 105 */       String str = feedFormat.format(usFormat.parse(date));
/* 106 */       return str.substring(0, str.indexOf(" "));
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 110 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 113 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getDefaultTimeZone()
/*     */   {
/* 120 */     String timezoneId = "21";
/* 121 */     TimeZone zone = TimeZone.getTimeZone("America/Los_Angeles");
/* 122 */     if (TimeUnit.MILLISECONDS.toHours(zone.getDSTSavings()) == 1L) {
/* 123 */       timezoneId = "20";
/*     */     }
/*     */ 
/* 127 */     return timezoneId;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.util.DateTime
 * JD-Core Version:    0.6.2
 */