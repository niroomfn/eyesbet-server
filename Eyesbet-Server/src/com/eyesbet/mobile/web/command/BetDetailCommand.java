/*    */ package com.eyesbet.mobile.web.command;
/*    */ 
/*    */ import com.eyesbet.business.domain.Bet;
/*    */ import com.eyesbet.business.domain.Bets;
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.business.domain.GameBet;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class BetDetailCommand extends MobileCommand
/*    */ {
/*    */   public BetDetailCommand(HttpServletRequest request)
/*    */   {
/* 15 */     super(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
	
	 		  String xml = null;
/* 23 */     if (this.request.getSession().getAttribute("betDetail") == null)
/*    */     {
/* 25 */       int betId = Integer.parseInt(this.request.getParameter("betId"));
/* 26 */       Bets bets = (Bets)this.request.getSession().getAttribute("bets");
/*    */ 
/* 28 */       Bet bet = bets.getBet(betId);
/*    */ 
/* 32 */        xml = buildXml(bet);
/*    */ 
/* 34 */       this.request.getSession().setAttribute("betDetail", xml);
/*    */ 
/* 36 */       
/*    */     } else {
/*    */ 
/* 41 */      xml = (String)this.request.getSession().getAttribute("betDetail");
/*    */ 
/* 43 */     this.request.getSession().removeAttribute("betDetail");
			}
/*    */ 
/* 45 */     return xml;
/*    */   }
/*    */ 
/*    */   private String buildXml(Bet bet)
/*    */   {
/* 59 */     this.xmlResponse.append("<xml betid='" + bet.getId() + "' >");
/*    */ 
/* 61 */     List<Game> list = bet.getGames();
/* 62 */     GameBet gamebet = null;
/* 63 */     for (Game game : list)
/*    */     {
/* 66 */       this.xmlResponse.append("<game a='" + game.getAway().getName() + "'");
/* 67 */       this.xmlResponse.append(" as='" + game.getAway().getScore() + "' h='" + game.getHome().getName() + "' ");
/* 68 */       this.xmlResponse.append(" hs='" + game.getHome().getScore() + "' >");
/*    */ 
/* 70 */       gamebet = game.getBet();
/*    */ 
/* 72 */       this.xmlResponse.append("<gamebet ismoneyline='" + gamebet.isMoneyline() + "' isoverunder='" + gamebet.isOverUnder() + "' ");
/* 73 */       this.xmlResponse.append(" ispoints='" + gamebet.isSpreadPoint() + "' moneyline='" + gamebet.getMoneyline() + "' moneylinestt='" + game.getStatusText() + "' ");
/* 74 */       if (gamebet.isSpreadPoint()) {
/* 75 */         this.xmlResponse.append("spreadpointsign='" + gamebet.getSpreadPointAndSign() + "' ");
/* 76 */         this.xmlResponse.append(" spreadpointteam='" + gamebet.getSpreadPointTeam() + "' spreadpointstt='" + game.getSpreadPointStatusText() + "' ");
/* 77 */       }if (gamebet.isOverUnder()) {
/* 78 */         this.xmlResponse.append(" isunder='" + gamebet.isUnder() + "' underpoints='" + gamebet.getUnderPoints() + "' overpoints='" + gamebet.getOverPoints() + "' oustt='" + game.getOnverUnderStatusText() + "' ");
/*    */       }
/*    */ 
/* 81 */       this.xmlResponse.append("/></game>");
/*    */     }
/*    */ 
/* 85 */     this.xmlResponse.append("</xml>");
/*    */ 
/* 87 */     return this.xmlResponse.toString();
/*    */   }
/*    */ }

