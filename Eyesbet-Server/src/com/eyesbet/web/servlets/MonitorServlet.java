/*     */ package com.eyesbet.web.servlets;
/*     */ 
/*     */ import com.eyesbet.business.BetComputer;
/*     */ import com.eyesbet.business.Tracker;
/*     */ import com.eyesbet.business.domain.Bet;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.GameBet;
/*     */ import com.eyesbet.business.domain.MonitorBet;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class MonitorServlet extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  27 */   private Logger logger = Logger.getLogger(MonitorServlet.class);
/*  28 */   private Tracker monitor = Tracker.getInstance();
/*     */ 
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  44 */     MonitorBet mbet = (MonitorBet)request.getSession().getAttribute("monitorBet");
/*  45 */     Set<Game> livegames = mbet.getLiveGames();
/*  46 */     Bet bet = mbet.getBet();
/*  47 */     String dt = getDisplayBetType(bet);
/*  48 */     this.logger.info("User monitoring bet type: " + bet.getBetType());
/*     */ 
/*  51 */     StringBuilder xmlResponse = new StringBuilder();
/*     */ 
/*  53 */     GameBet gamebet = null;
/*  54 */     this.logger.debug("number of live games: " + livegames.size());
/*  55 */     for (Game game : livegames)
/*     */     {
/*  57 */       BetComputer.computeLiveGameBet(game);
/*     */     }
/*     */ 
/*  60 */     if ((livegames != null) && (livegames.size() > 0)) {
/*  61 */       BetComputer.computeBetStatus(bet);
/*     */     }
/*  63 */     xmlResponse.append("<bet id='" + bet.getId() + "' dt='" + dt + "'   type='" + bet.getBetType() + "' name='" + bet.getName() + "' s='" + bet.getStatusText() + "' > ");
/*     */ 
/*  66 */     for (Game game : livegames)
/*     */     {
/*  68 */       gamebet = game.getBet();
/*  69 */       if (gamebet.isMoneyline()) {
/*  70 */         createBetResponse(game, xmlResponse, true, ""+gamebet.getId());
/*  71 */         xmlResponse.append("team='" + gamebet.getMoneyline() + "' type='mn'  s='' />");
/*     */       }
/*     */       else
/*     */       {
/*  76 */         if (gamebet.isSpreadPoint()) {
/*  77 */           createBetResponse(game, xmlResponse, true, ""+gamebet.getId());
/*  78 */           xmlResponse.append(" points='" + gamebet.getSpreadPoint() + "' team='" + gamebet.getSpreadPointTeam());
/*  79 */           xmlResponse.append("' pointsign='" + gamebet.getSpreadPointAndSign() + "' ")
/*  80 */             .append(" s='' type='sp' bs='" + game.getSpreadPointBetScore() + "' />");
/*     */         }
/*  82 */         if (gamebet.isOverUnder())
/*     */         {
/*  84 */           createBetResponse(game, xmlResponse, true, gamebet.getId() + "ou");
/*  85 */           xmlResponse.append(" type='ou' ");
/*  86 */           xmlResponse.append(" s='' bs='" + game.getOverUnderBetScore() + "' ");
/*     */ 
/*  88 */           if (gamebet.isUnder())
/*     */           {
/*  90 */             xmlResponse.append(" isunder='true'  u='" + gamebet.getUnderPoints() + "' ");
/*     */           }
/*     */           else
/*     */           {
/*  94 */             xmlResponse.append(" isunder='false' o='" + gamebet.getOverPoints() + "'");
/*     */           }
/*     */ 
/*  98 */           xmlResponse.append(" />");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 106 */     List<Game> games = bet.getNonLiveGames();
/* 107 */     for (Game game : games)
/*     */     {
/* 110 */       gamebet = game.getBet();
/* 111 */       if (gamebet.isMoneyline())
/*     */       {
/* 113 */         createBetResponse(game, xmlResponse, false, ""+gamebet.getId());
/* 114 */         xmlResponse.append("team='" + gamebet.getMoneyline() + "' type='mn' s='" + game.getStatusText() + "' />");
/*     */       }
/*     */       else
/*     */       {
/* 118 */         if (gamebet.isSpreadPoint()) {
/* 119 */           createBetResponse(game, xmlResponse, false, ""+gamebet.getId());
/* 120 */           xmlResponse.append(" points='" + gamebet.getSpreadPoint() + "' team='" + gamebet.getSpreadPointTeam() + "' ");
/* 121 */           xmlResponse.append("pointsign='" + gamebet.getSpreadPointAndSign() + "' ")
/* 122 */             .append("s='" + game.getSpreadPointStatusText() + "' type='sp' bs='" + game.getSpreadPointBetScore() + "' />");
/*     */         }
/* 124 */         if (gamebet.isOverUnder())
/*     */         {
/* 127 */           createBetResponse(game, xmlResponse, false, gamebet.getId() + "ou");
/*     */ 
/* 129 */           xmlResponse.append(" s='" + game.getOnverUnderStatusText() + "' ");
/*     */ 
/* 131 */           if (gamebet.isUnder())
/*     */           {
/* 133 */             xmlResponse.append(" isunder='true' u='" + gamebet.getUnderPoints() + "' ");
/*     */           }
/*     */           else
/*     */           {
/* 137 */             xmlResponse.append(" isunder='false' o='" + gamebet.getOverPoints() + "'");
/*     */           }
/*     */ 
/* 141 */           xmlResponse.append(" type='ou' bs='" + game.getOverUnderBetScore() + "' />");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 150 */     xmlResponse.append("</bet>");
/*     */ 
/* 154 */     response.setContentType("text/xml");
/* 155 */     response.setHeader("Cache-Control", "no-cache");
/*     */ 
/* 158 */     response.getWriter().write(xmlResponse.toString());
/* 159 */     response.getWriter().close();
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 167 */     doGet(request, response);
/*     */   }
/*     */ 
/*     */   @SuppressWarnings("unused")
			private void updateGameScores(Set<Game> games)
/*     */   {
/* 174 */     for (Game game : games)
/* 175 */       this.monitor.updateGame(game);
/*     */   }
/*     */ 
/*     */   private void createBetResponse(Game game, StringBuilder xmlResponse, boolean live, String betId)
/*     */   {
/* 183 */     xmlResponse.append("<gamebet live='" + live + "' gid='" + game.getGameId() + "' bid='" + betId + "' a='" + game.getAway().getName() + "' h='" + game.getHome().getName() + "' ");
/*     */ 
/* 185 */     xmlResponse.append("as='" + game.getAway().getScore() + "' hs='" + game.getHome().getScore() + "' ");
/*     */ 
/* 187 */     xmlResponse.append("sd='" + game.getStatusDesc() + "' ");
/*     */   }
/*     */ 
/*     */   private String getDisplayBetType(Bet bet)
/*     */   {
/* 199 */     if (bet.isParlay())
/*     */     {
/* 201 */       if (bet.isMoneyline())
/*     */       {
/* 203 */         return "Money Line Parlay";
/*     */       }
/*     */ 
/* 206 */       return "Points Parlay";
/*     */     }
/*     */ 
/* 212 */     if (bet.isMoneyline()) {
/* 213 */       return "Money Line Bet";
/*     */     }
/*     */ 
/* 216 */     return "Points Bet";
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.servlets.MonitorServlet
 * JD-Core Version:    0.6.2
 */