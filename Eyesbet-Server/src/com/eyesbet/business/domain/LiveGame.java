/*    */ package com.eyesbet.business.domain;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class LiveGame extends Game
/*    */ {
/* 12 */   private Set<Integer> users = new HashSet<Integer>();
/*    */ 
/*    */   public LiveGame(Team home, Team away, Fixtures.Leagues league) {
/* 15 */     super(home, away, league);
/*    */   }
/*    */ 
/*    */   public boolean userGame(int userId)
/*    */   {
/* 24 */     return this.users.contains(userId);
/*    */   }
/*    */ 
/*    */   public void addUser(int userId)
/*    */   {
/* 31 */     this.users.add(Integer.valueOf(userId));
/*    */   }
/*    */ 
/*    */   public boolean removeUser(int userId)
/*    */   {
/* 36 */     return this.users.remove(Integer.valueOf(userId));
/*    */   }
/*    */ 
/*    */   public boolean remove()
/*    */   {
/* 41 */     if ((this.users.size() == 0) || (!isLive())) {
/* 42 */       return true;
/*    */     }
/*    */ 
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean hasUsers()
/*    */   {
/* 51 */     if (this.users.size() > 0) return true;
/*    */ 
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean hasOtherUsers(int userId)
/*    */   {
/* 60 */     if ((this.users.size() == 1) && (this.users.contains(Integer.valueOf(userId))))
/*    */     {
/* 62 */       return false;
/*    */     }
/*    */ 
/* 66 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.LiveGame
 * JD-Core Version:    0.6.2
 */