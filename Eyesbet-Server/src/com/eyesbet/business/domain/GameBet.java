 package com.eyesbet.business.domain;
 
 public class GameBet
 {
   private int id;
   private String name;
   private String teams;
   private String moneyline = "";
   private String overPoints = "";
   private String underPoints = "";
   private String spreadPoint = "";
   private String spreadPointTeam = "";
   private String spreadPointFavorite = "";
 
   public GameBet(int id)
   {
     this.id = id;
   }
 
   public int getId()
   {
     return this.id;
   }
 
   public String getName()
   {
     return this.name;
   }
 
   public void setName(String name)
   {
     this.name = name;
   }
 
   public String getTeams()
   {
     return this.teams;
   }
 
   public void setTeams(String teams)
   {
     this.teams = teams;
   }
 
   public String getMoneyline()
   {
     return this.moneyline;
   }
 
   public void setMoneyline(String moneyline)
   {
     this.moneyline = treamTeamName(moneyline);
   }
 
   public String getOverPoints()
   {
     return this.overPoints;
   }
 
   public void setOverPoints(String overPoints)
   {
     this.overPoints = overPoints;
   }
 
   public String getUnderPoints()
   {
     return this.underPoints;
   }
 
   public void setUnderPoints(String underPoints)
   {
     this.underPoints = underPoints;
   }
 
   public String getSpreadPoint()
   {
     return this.spreadPoint;
   }
 
   public void setSpreadPoint(String spreadPoint)
   {
     this.spreadPoint = spreadPoint;
   }
 
   public String getSpreadPointTeam()
   {
     return this.spreadPointTeam;
   }
 
   public void setSpreadPointTeam(String spreadPointTeam)
   {
     this.spreadPointTeam = treamTeamName(spreadPointTeam);
   }
 
   public boolean isMoneyline()
   {
     if ((this.moneyline == null) || ("".equals(this.moneyline))) {
       return false;
     }
 
     return true;
   }
 
   public boolean isSpreadPoint()
   {
     if ((this.spreadPointTeam == null) || ("".equals(this.spreadPointTeam)) || ("".equals(this.spreadPoint))) {
       return false;
     }
 
     return true;
   }
 
   public boolean isOverUnder()
   {
     if ((isOver()) || (isUnder())) {
       return true;
     }
     return false;
   }
 
   public boolean isUnder() {
     try {
       Integer.parseInt(this.underPoints);
     } catch (NumberFormatException e) {
       return false;
     }
     return true;
   }
 
   public boolean isOver()
   {
     try
     {
       Integer.parseInt(this.overPoints);
     } catch (NumberFormatException e) {
       return false;
     }
     return true;
   }
 
   public String getSpreadPointFavorite()
   {
     return this.spreadPointFavorite;
   }
 
   public void setSpreadPointFavorite(String spreadPointFavorite)
   {
     this.spreadPointFavorite = treamTeamName(spreadPointFavorite);
   }
 
   public String getSpreadPointAndSign()
   {
	   
	  String str = "";
     if (this.spreadPointFavorite.equals(this.spreadPointTeam)) {
       str =  "-" + this.spreadPoint;
     } else {
       str = "+" + this.spreadPoint;
     }
     
     int index = 0;
     if ((index = str.indexOf(".0")) > 0) {
    	 
    	 str = str.substring(0,index );
     }
     
     
     return str;

   }
 
   private String treamTeamName(String team)
   {
     return team.substring(team.lastIndexOf(" ") + 1, team.length());
   }
 
   public String getType()
   {
     if (isMoneyline()) {
       return BetType.moneyline.toString();
     }
 
     return BetType.points.toString();
   }


			public void deleteOverUnder() {
				
				overPoints = "";
				underPoints = "";
				
			}
			
			public void deleteSpreadPoint() {
				
				this.spreadPoint = "";
				this.spreadPointFavorite = "";
				this.spreadPointTeam = "";
				
				
			}
			
			
			public boolean deleted() {
				
				if (this.isMoneyline() == false && this.isOverUnder() == false && this.isSpreadPoint() == false) {
					
					return true;
				}
				
				return false;
			}
			
			
 }

