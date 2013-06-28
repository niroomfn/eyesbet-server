 package com.eyesbet.business.domain;
 
 public class Game
 {
   protected int id = 0;
   private GameBet bet;
   private int betId;
   protected String schedule;
   protected Team home;
   protected Team away;
   protected Fixtures.Leagues league;
   protected int status = 0;
   protected int overUnderStatus;
   protected int spreadPointStatus;
   protected StatusType statusType;
   protected String statusDesc;
 
   public Game()
   {
   }
 
   public Game(Team home, Team away, Fixtures.Leagues league)
   {
     this.home = home;
     this.away = away;
     this.league = league;
   }
 
   public GameBet getBet()
   {
     return this.bet;
   }
 
  
 
   public void setId(int id)
   {
     this.id = id;
   }
 
   public void updateScores(String home, String away)
   {
     this.home.setScore(Integer.parseInt(home));
     this.away.setScore(Integer.parseInt(away));
   }
 
   public boolean equals(Object obj)
   {
     if ((obj == null) || (!(obj instanceof Game))) {
       return false;
     }
     Game game = (Game)obj;
     if (game.getGameId() == getGameId())
     {
       return true;
     }
 
     return false;
   }
 
   public int hashCode()
   {
     return new Integer(this.id).hashCode();
   }
 
   public String getGameBetName()
   {
     return this.betId + "_" + this.home.getName() + "_" + this.away.getName();
   }
 
   public void setBet(GameBet bet)
   {
     this.bet = bet;
   }
 
   public String getSchedule()
   {
     return this.schedule;
   }
 
   public void setSchedule(String schedule)
   {
     this.schedule = schedule;
   }
 
   public boolean isLive()
   {
     if (this.statusType == StatusType.inprogress) {
       return true;
     }
     return false;
   }
 
   public boolean notStarted()
   {
     if (this.statusType == StatusType.notstarted) {
       return true;
     }
     return false;
   }
 
   public boolean isTightScore() {
     if ((this.home.getScore() == this.away.getScore()) && (!notStarted())) {
       return true;
     }
     return false;
   }
 
   public int getStatus()
   {
     return this.status;
   }
 
   public void setStatus(int status)
   {
     this.status = status;
   }
 
   public boolean isFinished()
   {
     if (this.statusType == StatusType.finished) {
       return true;
     }
 
     return false;
   }
 
   public Team getHome()
   {
     return this.home;
   }
 
   public void setHome(Team home)
   {
     this.home = home;
   }
 
   public Team getAway()
   {
     return this.away;
   }
 
   public void setAway(Team away)
   {
     this.away = away;
   }
 
   public Team getWinner()
   {
     if (this.home.getScore() > this.away.getScore())
       return this.home;
     if (this.home.getScore() < this.away.getScore()) {
       return this.away;
     }
 
     return null;
   }
 
   public LiveGame toLiveGame()
   {
     LiveGame game = new LiveGame(this.home, this.away, this.league);
     game.setId(this.id);
     game.setSchedule(this.schedule);
     game.setStatus(this.status);
     return game;
   }
 
   public Fixtures.Leagues getLeage()
   {
     return this.league;
   }
 
   public String getStatusType()
   {
     if (this.statusType == null) {
       return "";
     }
     return this.statusType.toString();
   }
 
   public String getStatusTypeText()
   {
     if (this.statusType == null) {
       return "unkown";
     }
 
     return this.statusType.getText();
   }
 
   public void setStatusType(String statusType)
   {
     try {
       this.statusType = StatusType.valueOf(statusType);
     }
     catch (Exception localException)
     {
     }
   }
 
   public String getStatusDesc()
   {
     return this.statusDesc;
   }
 
   public void setStatusDesc(String statusDesc)
   {
     this.statusDesc = statusDesc.toUpperCase();
   }
 
   public String getDate()
   {
     if (this.schedule == null) return "";
     return this.schedule.substring(0, this.schedule.indexOf(" "));
   }
 
   public String toString()
   {
     return this.away.getName() + ": " + this.away.getScore() + " @ " + this.home.getName() + ": " + this.home.getScore() + " STATUS: " + this.statusType;
   }
 
   public int getBetId() {
     return this.betId;
   }
 
   public void setBetId(int betId) {
     this.betId = betId;
   }
 
   public int getOverUnderStatus()
   {
     return this.overUnderStatus;
   }
 
   public void setOverUnderStatus(int overUnderStatus) {
     this.overUnderStatus = overUnderStatus;
   }
 
   public int getSpreadPointStatus() {
     return this.spreadPointStatus;
   }
 
   public void setSpreadPointStatus(int spreadPointStatus)
   {
     this.spreadPointStatus = spreadPointStatus;
   }
   public String getStatusText() {
     return getStatusText(this.status);
   }
 
   public String getOnverUnderStatusText() {
     return getStatusText(this.overUnderStatus);
   }
 
   public String getSpreadPointStatusText()
   {
     return getStatusText(this.spreadPointStatus);
   }
 
   private String getStatusText(int status) {
     String text = "";
     if (isFinished())
     {
       if (status == 1) text = "Tight";
       else if (status == 100) text = "WON";
       else if ((status < 100) && (status != 1)) text = "LOST";
 
     }
     else if (isLive())
     {
       text = "Pending...";
     }
     else text = "Unknown";
 
     return text;
   }
 
   public int getGameId() {
     return this.id;
   }
 
   public int getMoneylineBetScore()
   {
     if (this.home.getName().equals(this.bet.getMoneyline())) {
       return this.home.getScore();
     }
 
     return this.away.getScore();
   }
 
   public String getSpreadPointBetScore()
   {
	   
	   String str = "";
	   
	   if (this.home.getName().equals(this.bet.getSpreadPointTeam()))
     {
    	 
    	 
       str = this.home.getBetScore() + "";
     } else {
 
       str = this.away.getBetScore() + "";
     }
       int index = 0;
       if (( index = str.indexOf(".0")) > 0) {
    	   return str.substring(0, index);
       } else {
    	   
    	   return str;
       }
     
   }
 
   public int getOverUnderBetScore()
   {
     return this.away.getScore() + this.home.getScore();
   }
   
   
   
   public void updateBet(GameBet bet) {
	   
	   
	  this.bet.setMoneyline(bet.getMoneyline());
	  this.bet.setName(bet.getName());
	  this.bet.setOverPoints(bet.getOverPoints());
	  this.bet.setSpreadPoint(bet.getSpreadPoint());
	  this.bet.setSpreadPointFavorite(bet.getSpreadPointFavorite());
	  this.bet.setSpreadPointTeam(bet.getSpreadPointTeam());
	  this.bet.setTeams(bet.getTeams());
	  this.bet.setUnderPoints(bet.getUnderPoints());
	   
	   
   }
   
 
   public static enum StatusType
   {
     inprogress("In Progress"), finished("Finished"), notstarted("Not Started");
 
     private String text;
 
     private StatusType(String text) { this.text = text; }
 
     public String getText()
     {
       return this.text;
     }
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.domain.Game
 * JD-Core Version:    0.6.2
 */