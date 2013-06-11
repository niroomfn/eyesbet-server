 package com.eyesbet.business.domain;
 
 import com.eyesbet.util.DateTime;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 
 public class TeamMonitor
 {
   private int id = 0;
   private boolean home;
   private int score;
   private String sportName;
   private String quater;
   private Date schedule;
   private String name;
   private Set<String> sessionIds = new HashSet<String>();
 
   public TeamMonitor(int id, String name, String schedule, String sessionId) {
     this.id = id;
     this.schedule = DateTime.convertToUSOnlyDate(schedule);
     this.name = name;
     addSessionId(sessionId);
   }
 
   public boolean isHome()
   {
     return this.home;
   }
 
   public void addSessionId(String sessionId) {
     System.out.println("Adding session Id: " + sessionId + "for team: " + getName());
     if (!this.sessionIds.add(sessionId))
     {
       System.out.println(sessionId + " arleady exist not added");
     }
   }
 
   public void setHome(boolean home)
   {
     this.home = home;
   }
 
   public int getScore()
   {
     return this.score;
   }
 
   public void setScore(int score)
   {
     this.score = score;
   }
 
   public String getSportName()
   {
     return this.sportName;
   }
 
   public void setSportName(String sportName)
   {
     this.sportName = sportName;
   }
 
   public String getQuater()
   {
     return this.quater;
   }
 
   public void setQuater(String quater)
   {
     this.quater = quater;
   }
 
   public int getId()
   {
     return this.id;
   }
 
   public Date getSchedule()
   {
     return this.schedule;
   }
 
   public void setSchedule(Date schedule)
   {
     this.schedule = schedule;
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public void setName(String name)
   {
     this.name = name;
   }
 
   public boolean equals(Object obj)
   {
     if ((obj == null) || (!(obj instanceof TeamMonitor))) {
       return false;
     }
     TeamMonitor tm = (TeamMonitor)obj;
     if ((getId() == tm.getId()) && (this.name.equals(tm.getName()))) {
       return true;
     }
 
     return false;
   }
 
   public int hashCode()
   {
     return (this.id + this.name).hashCode();
   }
 
   public boolean removeSessionId(String sessionId)
   {
     return this.sessionIds.remove(sessionId);
   }
 
   public boolean remove()
   {
     if (this.sessionIds.size() == 0) {
       return true;
     }
 
     return false;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.TeamMonitor
 * JD-Core Version:    0.6.2
 */