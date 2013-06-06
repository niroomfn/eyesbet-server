/*     */ package com.eyesbet.mobile.web.command;
/*     */ 
/*     */ import com.eyesbet.business.Service;
/*     */ import com.eyesbet.business.Tracker;
/*     */ import com.eyesbet.business.domain.Bet;
/*     */ import com.eyesbet.business.domain.Bets;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.MonitorBet;
/*     */ import com.eyesbet.business.domain.SortedGames;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class StreamBetCommand extends MobileCommand
/*     */ {
/*  21 */   private static Logger logger = Logger.getLogger(StreamBetCommand.class);
/*     */   private HttpServletResponse response;
/*     */ 
/*     */   public StreamBetCommand(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  24 */     super(request);
/*  25 */     this.response = response;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  32 */     Bets bets = (Bets)this.request.getSession().getAttribute("bets");
/*     */ 
/*  36 */     if (this.request.getParameter("betId") == null)
/*     */     {
/*  38 */       if (this.request.getParameter("cmd") == null)
/*     */       {
/*  42 */         this.xmlResponse.append("<bets>");
/*     */ 
/*  44 */         for (Bet bet : bets.getBets())
/*     */         {
/*  46 */           this.xmlResponse.append("<bet n='" + bet.getName() + "' v='" + bet.getId() + "' />");
/*     */         }
/*     */ 
/*  52 */         Service service = new Service();
/*  53 */         SortedGames sgames = service.sortByGameStartTime(bets);
/*  54 */         List<Game> games = sgames.getGames();
/*  55 */         this.xmlResponse.append("<games>");
/*  56 */         for (Game g : games)
/*     */         {
/*  58 */           this.xmlResponse.append("<game g='" + g.getAway().getName() + " @ " + g.getHome().getName() + "' st='" + SortedGames.getStartTime(g) + "' ");
/*  59 */           this.xmlResponse.append("bid='" + g.getBetId() + "' bets='" + sgames.getBetNames(g.getGameId()) + "' />");
/*     */         }
/*     */ 
/*  63 */         this.xmlResponse.append("</games></bets>");
/*     */ 
/*  66 */         return this.xmlResponse.toString();
/*     */       }
/*     */ 
/*  71 */       logger.info("User canceled streamming");
/*  72 */       Tracker tracker = Tracker.getInstance();
/*  73 */       tracker.removeUser(getUserId());
/*  74 */       this.request.getSession().removeAttribute("monitorBet");
/*  75 */       return "<success />";
/*     */     }
/*     */ 
/*  84 */     logger.info("User Streamming bet...");
/*     */ 
/*  86 */     Tracker monitor = Tracker.getInstance();
/*     */ 
/*  88 */     int betId = Integer.parseInt(this.request.getParameter("betId"));
/*     */ 
/*  90 */     Bet bet = bets.getBet(betId);
/*  91 */     if (bet == null) {
/*  92 */       return "";
/*     */     }
/*     */ 
/*  97 */     Set<Game> livegames = bet.getLiveGames();
/*  98 */     logger.debug("Live games: " + livegames.size());
/*     */ 
/* 104 */     MonitorBet monitorBet = new MonitorBet(bet);
/* 105 */     this.request.getSession().setAttribute("monitorBet", monitorBet);
/* 106 */     monitorBet.setLiveGames(livegames);
/* 107 */     if ((livegames != null) && (livegames.size() > 0))
/*     */     {
/* 110 */       monitor.addGames(livegames, getUserId());
/*     */     }
/*     */ 
/* 114 */     this.request.getServletContext().getRequestDispatcher("/monitor").forward(this.request, this.response);
/* 115 */     return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.StreamBetCommand
 * JD-Core Version:    0.6.2
 */