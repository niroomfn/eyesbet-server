 package com.eyesbet.business.domain;
 
 import java.util.HashSet;
 import java.util.Set;
 
 public class LiveGame extends Game
 {
   private Set<Integer> users = new HashSet<Integer>();
 
   public LiveGame(Team home, Team away, Fixtures.Leagues league) {
     super(home, away, league);
   }
 
   public boolean userGame(int userId)
   {
     return this.users.contains(userId);
   }
 
   public void addUser(int userId)
   {
     this.users.add(Integer.valueOf(userId));
   }
 
   public boolean removeUser(int userId)
   {
     return this.users.remove(Integer.valueOf(userId));
   }
 
   public boolean remove()
   {
     if ((this.users.size() == 0) || (!isLive())) {
       return true;
     }
 
     return false;
   }
 
   public boolean hasUsers()
   {
     if (this.users.size() > 0) return true;
 
     return false;
   }
 
   public boolean hasOtherUsers(int userId)
   {
     if ((this.users.size() == 1) && (this.users.contains(Integer.valueOf(userId))))
     {
       return false;
     }
 
     return true;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.LiveGame
 * JD-Core Version:    0.6.2
 */