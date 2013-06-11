 package com.eyesbet.business.domain;
 
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.TimeZone;
 
 public class Fixture
   implements Comparable<Fixture>
 {
   private int id;
   private String home;
   private String away;
   private String schedule;
   private String timezone = "America/Los_Angeles";
   private static SimpleDateFormat usDateFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm aaa");
   private static SimpleDateFormat noneUSFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 
   public String getHome()
   {
     return this.home;
   }
 
   public void setId(int id)
   {
     this.id = id;
   }
 
   public void setHome(String home)
   {
     this.home = getTeamName(home.trim());
   }
 
   public String getAway()
   {
     return this.away;
   }
 
   public void setAway(String away)
   {
     this.away = getTeamName(away.trim());
   }
 
   public void setSchedule(String date, String time)
   {
     try
     {
       Date d = noneUSFormat.parse(date + " " + time);
       this.schedule = usDateFormat.format(d);
     }
     catch (ParseException e) {
       e.printStackTrace();
     }
   }

			public void setSchedule(String schedule) {
				
				this.schedule = schedule;
			}
 
   public String getSchedule()
   {
     return this.schedule;
   }
 
   public String displayFixture()
   {
     return this.away + " @ " + this.home;
   }
 
   public String getId()
   {
     return this.away + "@" + this.home + "@" + this.id;
   }
 
   public String getTimezone()
   {
     return this.timezone;
   }
 
   public void setTimezone(String timezone)
   {
     this.timezone = timezone;
 
     usDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
   }
 
   public boolean equals(Object obj)
   {
     if (!(obj instanceof Fixture)) {
       return false;
     }
 
     Fixture f = (Fixture)obj;
     if (this.id == f.getFixtureId())
     {
       return true;
     }
 
     return false;
   }
 
   public int hashCode()
   {
     return (""+id).hashCode();
   }
 
   public int getFixtureId()
   {
     return this.id;
   }
 
   public int compareTo(Fixture f)
   {
     try
     {
       Date date1 = usDateFormat.parse(getSchedule());
       Date date2 = usDateFormat.parse(f.getSchedule());
 
       return date1.compareTo(date2);
     }
     catch (ParseException e)
     {
       e.printStackTrace();
     }
 
     return 0;
   }
 
   private String getTeamName(String team)
   {
     return team.substring(team.lastIndexOf(" ") + 1, team.length());
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Fixture
 * JD-Core Version:    0.6.2
 */