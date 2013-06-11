 package com.eyesbet.business.domain;
 
 public class Team
 {
   protected String name;
   protected int id;
   protected int score;
   protected double betScore;
   protected Bet bet;
 
   public Team(int id, String name)
   {
     this.id = id;
     this.name = treamName(name);
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public int getId() {
     return this.id;
   }
 
   public int getScore()
   {
     return this.score;
   }
 
   public void setScore(int score)
   {
     this.score = score;
   }
 
   public Bet getBet()
   {
     return this.bet;
   }
 
   public void setBet(Bet bet)
   {
     this.bet = bet;
   }
 
   public void setName(String name)
   {
     this.name = treamName(name);
   }
 
   public double getBetScore()
   {
     return this.betScore;
   }
 
   public void setBetScore(double betScore)
   {
     this.betScore = betScore;
   }
 
   private String treamName(String name)
   {
     return name.substring(name.lastIndexOf(" ") + 1, name.length());
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Team
 * JD-Core Version:    0.6.2
 */