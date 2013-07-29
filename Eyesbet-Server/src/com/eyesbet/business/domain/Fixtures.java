 package com.eyesbet.business.domain;
 
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
 
 public class Fixtures
 {
   private static Fixtures me = new Fixtures();
   private FixtureMap map = new FixtureMap();
   private Date dateLoaded;
   
   public enum TimeZones {
	   
	   PT("US/Pacific"), ET ("US/Eastern"), CT("US/Central");
	   
	    private String text;
	    private TimeZone timeZone;
	    private TimeZones(String text) {
		   timeZone = TimeZone.getTimeZone(text);
		   this.text = text;
	   }
	   
	    
	   public String getText() {
		   return text;
	   }
	   
	   public TimeZone getTimeZone() {
		   return timeZone;
	   }
	   
	   
   }
 
   public String[] getLeagues()
   {
     Leagues[] leagues = Leagues.values();
     String[] array = new String[leagues.length];
     for (int i = 0; i < leagues.length; i++) {
       array[i] = leagues[i].toString();
     }
 
     return array;
   }
 
   public static Fixtures getInstance()
   {
     return me;
   }
 
   public Set<Fixture> getLeagueFixtures(Leagues league)
   {
     return this.map.get(league.toString());
   }
   
   
 
   
 
   public void setFixtures(Set<Fixture> list, Leagues league)
   {
     this.map.put(league.toString(), list);
   }
 
   public void setNBAFixture(Set<Fixture> list)
   {
     this.map.put(Leagues.NBA.toString(), list);
   }

			public void appendFixtures(Set<Fixture> list, Leagues league) {
				
				map.get(league.toString()).addAll(list);
				
			}
 
   public void setNFLFixtures(Set<Fixture> list) {
     this.map.put(Leagues.NFL.toString(), list);
   }
 
   public Set<Fixture> getNFLFixtures()
   {
     return this.map.get("NFL");
   }
 
   public Set<Fixture> getNBAFixtures()
   {
     return this.map.get("NBA");
   }
 
   public FixtureMap getAllFixtures()
   {
     return this.map;
   }
 
   public Date getDateLoaded()
   {
     return this.dateLoaded;
   }
 
   public void setDateLoaded(Date dateLoaded)
   {
     this.dateLoaded = dateLoaded;
   }
 
   public  enum Leagues
   {
     NFL("3101004"), NBA("3100973"), MLB(""), NHL("");
 
     private String leagueId;
     private static String[] order = { "NBA", "NFL", "MLB", "NHL" };
 
     private Leagues(String leagueId) { this.leagueId = leagueId; }
 
     public String getLeagueId() {
       return this.leagueId;
     }
 
     public static String[] getOrder()
     {
       return order;
     }
   }
   
   
   
   
   
   
   
 }



 
 