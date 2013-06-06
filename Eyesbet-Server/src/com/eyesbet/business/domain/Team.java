/*    */ package com.eyesbet.business.domain;
/*    */ 
/*    */ public class Team
/*    */ {
/*    */   protected String name;
/*    */   protected int id;
/*    */   protected int score;
/*    */   protected double betScore;
/*    */   protected Bet bet;
/*    */ 
/*    */   public Team(int id, String name)
/*    */   {
/* 14 */     this.id = id;
/* 15 */     this.name = treamName(name);
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 20 */     return this.name;
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 24 */     return this.id;
/*    */   }
/*    */ 
/*    */   public int getScore()
/*    */   {
/* 29 */     return this.score;
/*    */   }
/*    */ 
/*    */   public void setScore(int score)
/*    */   {
/* 34 */     this.score = score;
/*    */   }
/*    */ 
/*    */   public Bet getBet()
/*    */   {
/* 39 */     return this.bet;
/*    */   }
/*    */ 
/*    */   public void setBet(Bet bet)
/*    */   {
/* 44 */     this.bet = bet;
/*    */   }
/*    */ 
/*    */   public void setName(String name)
/*    */   {
/* 49 */     this.name = treamName(name);
/*    */   }
/*    */ 
/*    */   public double getBetScore()
/*    */   {
/* 54 */     return this.betScore;
/*    */   }
/*    */ 
/*    */   public void setBetScore(double betScore)
/*    */   {
/* 59 */     this.betScore = betScore;
/*    */   }
/*    */ 
/*    */   private String treamName(String name)
/*    */   {
/* 65 */     return name.substring(name.lastIndexOf(" ") + 1, name.length());
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Team
 * JD-Core Version:    0.6.2
 */