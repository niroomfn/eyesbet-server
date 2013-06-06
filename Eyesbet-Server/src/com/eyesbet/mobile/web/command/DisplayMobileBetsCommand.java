/*    */ package com.eyesbet.mobile.web.command;
/*    */ 
/*    */ import com.eyesbet.business.domain.Bet;
/*    */ import com.eyesbet.business.domain.Bets;
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.web.command.DisplayBetsCommand;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class DisplayMobileBetsCommand extends MobileCommand
/*    */ {
/*    */   private DisplayBetsCommand command;
/*    */ 
/*    */   public DisplayMobileBetsCommand(HttpServletRequest request)
/*    */   {
/* 20 */     super(request);
/* 21 */     this.command = new DisplayBetsCommand(request);
/*    */   }
/*    */ 
/*    */   public String execute()
/*    */     throws Exception
/*    */   {
/* 29 */     this.command.execute();
/*    */ 
/* 31 */     Bets bets = (Bets)this.request.getSession().getAttribute("bets");
/* 32 */     buildXml(bets);
/*    */ 
/* 34 */     return bets.getXml();
/*    */   }
/*    */ 
/*    */   private void buildXml(Bets bets)
/*    */   {
/* 41 */     List<Bet> list = null;
/* 42 */     Set<String> bettypes = bets.keySet();
/* 43 */     boolean hasLiveGame = false;
/* 44 */     if (this.request.getSession().getAttribute("liveGames") != null)
/*    */     {
/* 46 */       hasLiveGame = true;
/*    */     }
/*    */ 
/* 49 */     this.xmlResponse.append("<xml livegame='" + hasLiveGame + "' >");
/* 50 */     for (String type : bettypes) {
/* 51 */       list = bets.get(type);
/* 52 */       this.xmlResponse.append("<bets type='" + type + "' >");
/* 53 */       for (Bet bet : list)
/*    */       {
/* 55 */         this.xmlResponse.append("<bet id='").append(bet.getId()).append("' name='" + bet.getName() + "' type='").append(bet.getBetType()).append("' s='" + bet.getStatusText() + "' >");
/*    */ 
/* 57 */         List<Game> games = bet.getGames();
/* 58 */         for (Game game : games)
/*    */         {
/* 60 */           this.xmlResponse.append("<game a='" + game.getAway().getName() + "' h='" + game.getHome().getName() + "'");
/* 61 */           this.xmlResponse.append(" as='" + game.getAway().getScore() + "' hs='" + game.getHome().getScore() + "' stt='" + game.getStatusTypeText() + "' sch='" + game.getSchedule() + "' >");
/*    */ 
/* 63 */           this.xmlResponse.append("</game>");
/*    */         }
/*    */ 
/* 66 */         this.xmlResponse.append("</bet>");
/*    */       }
/*    */ 
/* 70 */       this.xmlResponse.append("</bets>");
/*    */     }
/*    */ 
/* 74 */     this.xmlResponse.append("</xml>");
/*    */ 
/* 77 */     bets.setXml(this.xmlResponse.toString());
/*    */   }
/*    */ }
