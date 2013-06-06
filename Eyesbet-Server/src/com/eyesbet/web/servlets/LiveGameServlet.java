/*     */ package com.eyesbet.web.servlets;
/*     */ 
/*     */ import com.eyesbet.business.BetComputer;
/*     */ import com.eyesbet.business.Tracker;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.GameBet;
/*     */ import com.eyesbet.business.domain.MonitorBet;
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ public class LiveGameServlet extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  22 */   private Tracker monitor = Tracker.getInstance();
/*     */ 
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  38 */     MonitorBet mbet = (MonitorBet)request.getSession().getAttribute("monitorBet");
/*  39 */     Set<Game> livegames = mbet.getLiveGames();
/*     */ 
/*  41 */     updateGameScores(livegames);
/*     */ 
/*  43 */     for (Game livegame : livegames)
/*     */     {
/*  46 */       BetComputer.computeLiveGameBet(livegame);
/*     */     }
/*     */ 
/*  49 */     BetComputer.computeBetStatus(mbet.getBet());
/*     */ 
/*  51 */     boolean isMoneyline = isMoneyline(livegames);
/*     */ 
/*  54 */     StringBuilder xml = new StringBuilder("<bet id='" + mbet.getBet().getId() + "' >");
/*     */ 
/*  56 */     if (isMoneyline) {
/*  57 */       for (Game game : livegames)
/*     */       {
/*  60 */         xml.append("<g t='mn' team='" + game.getBet().getMoneyline() + "' bid='" + game.getBet().getId() + "' a='" + game.getAway().getName() + "'  as='" + game.getAway().getScore() + "' ");
/*  61 */         xml.append("hs='" + game.getHome().getScore() + "' h='" + game.getHome().getName() + "' s='" + game.getStatus() + "' />");
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*  67 */       GameBet gamebet = null;
/*  68 */       for (Game game : livegames) {
/*  69 */         gamebet = game.getBet();
/*  70 */         if (gamebet.isSpreadPoint()) {
/*  71 */           xml.append("<g gid='" + game.getGameId() + "' a='" + game.getAway().getName() + "' h='" + game.getHome().getName() + "' as='" + game.getAway().getScore() + "' ");
/*  72 */           xml.append("hs='" + game.getHome().getScore() + "' t='sp' ");
/*  73 */           xml.append(" bid='").append(gamebet.getId()).append("' ");
/*  74 */           xml.append(" team='" + gamebet.getSpreadPointTeam() + "' ");
/*  75 */           xml.append("s='" + game.getSpreadPointStatus() + "' bs='" + game.getSpreadPointBetScore() + "' />");
/*     */         }
/*  77 */         if (gamebet.isOverUnder()) {
/*  78 */           xml.append("<g gid='" + game.getGameId() + "' a='" + game.getAway().getName() + "' h='" + game.getHome().getName() + "' as='" + game.getAway().getScore() + "' ");
/*  79 */           xml.append("hs='" + game.getHome().getScore() + "' ");
/*  80 */           xml.append("bid='").append(gamebet.getId()).append("ou' ");
/*  81 */           xml.append("t='ou' ").append("s='" + game.getOverUnderStatus() + "' bs='" + game.getOverUnderBetScore() + "' />");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  88 */     xml.append("</bet>");
/*     */ 
/*  90 */     response.setContentType("text/xml");
/*  91 */     response.setHeader("Cache-Control", "no-cache");
/*     */ 
/*  94 */     response.getWriter().write(xml.toString());
/*  95 */     response.getWriter().close();
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   private void updateGameScores(Set<Game> games)
/*     */   {
/* 107 */     for (Game game : games)
/* 108 */       this.monitor.updateGame(game);
/*     */   }
/*     */ 
/*     */   private boolean isMoneyline(Set<Game> livegames)
/*     */   {
/* 115 */     for (Game game : livegames)
/*     */     {
/* 117 */       if (game.getBet().isMoneyline())
/*     */       {
/* 119 */         return true;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 125 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.servlets.LiveGameServlet
 * JD-Core Version:    0.6.2
 */