 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.domain.Bet;
 import com.eyesbet.business.domain.Bets;
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.GameBet;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 
 public class BetDetailCommand extends MobileCommand
 {
	
			private int betId;
			
   public BetDetailCommand(HttpServletRequest request)
   {
     super(request);
   }
 
   public String execute()
     throws Exception
   {
	
	 		  String xml = null;
     if (this.request.getSession().getAttribute("betDetail") == null)
     {
      
			   int betId = 0;
			try {
				betId = Integer.parseInt(this.request.getParameter("betId"));
			} catch (Exception e) {
				betId = this.betId;
			}
			   
       Bets bets = (Bets)this.request.getSession().getAttribute("bets");
 
       Bet bet = bets.getBet(betId);
 
        xml = buildXml(bet);
 
       this.request.getSession().setAttribute("betDetail", xml);
 
       
     } else {
 
      xml = (String)this.request.getSession().getAttribute("betDetail");
 
     this.request.getSession().removeAttribute("betDetail");
			}
 
     return xml;
   }
 
   private String buildXml(Bet bet)
   {
     this.xmlResponse.append("<xml name='"+bet.getName()+"' betid='" + bet.getId() + "' type='"+bet.getBetType().getShortName()+"' >");
 
     List<Game> list = bet.getGames();
     GameBet gamebet = null;
     for (Game game : list)
     {
       this.xmlResponse.append("<game a='" + game.getAway().getName() + "'");
       this.xmlResponse.append(" as='" + game.getAway().getScore() + "' h='" + game.getHome().getName() + "' ");
       this.xmlResponse.append(" hs='" + game.getHome().getScore() + "' id='" +game.getGameId()+"'  >");
 
       gamebet = game.getBet();
 
       this.xmlResponse.append("<gamebet ismoneyline='" + gamebet.isMoneyline() + "' isoverunder='" + gamebet.isOverUnder() + "' ");
       this.xmlResponse.append(" ispoints='" + gamebet.isSpreadPoint() + "' moneyline='" + gamebet.getMoneyline() + "' moneylinestt='" + game.getStatusText() + "' ");
       if (gamebet.isSpreadPoint()) {
         this.xmlResponse.append("spreadpointsign='" + gamebet.getSpreadPointAndSign() + "' ");
         this.xmlResponse.append(" spreadpointteam='" + gamebet.getSpreadPointTeam() + "' spreadpointstt='" + game.getSpreadPointStatusText() + "' ");
       }if (gamebet.isOverUnder()) {
         this.xmlResponse.append(" isunder='" + gamebet.isUnder() + "' underpoints='" + gamebet.getUnderPoints() + "' overpoints='" + gamebet.getOverPoints() + "' oustt='" + game.getOnverUnderStatusText() + "' ");
       }
 
       this.xmlResponse.append("/></game>");
     }
 
     this.xmlResponse.append("</xml>");
 
     return this.xmlResponse.toString();
   }
public int getBetId() {
	return betId;
}
public void setBetId(int betId) {
	this.betId = betId;
}

			
 }

