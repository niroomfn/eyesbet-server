/*     */ package com.eyesbet.web.command;
/*     */ 
/*     */ import com.eyesbet.business.BetComputer;
/*     */ import com.eyesbet.business.ScoreFeed;
/*     */ import com.eyesbet.business.Service;
/*     */ import com.eyesbet.business.domain.Bet;
/*     */ import com.eyesbet.business.domain.Bets;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.TrackerGames;
/*     */ import com.eyesbet.dao.UserDao;
/*     */ import java.util.Iterator;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class DisplayBetsCommand extends Command
/*     */ {
/*  21 */   private boolean updateBetStatus = true;
/*     */ 
/*     */   public DisplayBetsCommand(HttpServletRequest request) {
/*  24 */     super(request);
/*  25 */     this.view = "displayBets.jsp";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  32 */     Bets bets = null;
/*  33 */     if (this.updateBetStatus) {
/*  34 */       UserDao dao = new UserDao();
/*  35 */       bets = dao.getUserBets(getUserId());
/*  36 */       TrackerGames trackGames = new TrackerGames();
/*  37 */       Service service = new Service();
/*     */       Iterator<Game> localIterator2;
/*  38 */       for (Iterator<Bet> localIterator1 = bets.getBets().iterator(); localIterator1.hasNext();)
/*     */       {
/*  38 */         Bet bet = localIterator1.next();
/*     */ 
/*  40 */         localIterator2 = bet.getGames().iterator();
				  while (localIterator2.hasNext()) {
					  Game game = localIterator2.next();
/*  41 */         if (!service.updateGameScores(game))
/*  42 */           trackGames.addGame(game);
/*     */         else {
/*  44 */           trackGames.setHasFinishedGames(true);
/*     */         }
/*     */       }
			  }
/*     */ 
/*  48 */       if (!trackGames.isEmpty()) {
/*  49 */         ScoreFeed feed = new ScoreFeed(trackGames);
/*  50 */         feed.updateAllGamesScore();
/*  51 */         feed.saveFinishedGames();
/*     */       }
/*     */ 
/*  55 */       for (Bet bet : bets.getBets())
/*     */       {
/*  57 */         for (Game game : bet.getGames())
/*     */         {
/*  59 */           if (game.getStatusType().equals("")) {
/*  60 */             Game g = trackGames.getGame(game);
/*  61 */             game.getHome().setScore(g.getHome().getScore());
/*  62 */             game.getAway().setScore(g.getAway().getScore());
/*  63 */             game.setStatusType(g.getStatusType());
/*     */           }
/*  65 */           if (!game.isLive())
/*     */           {
/*  67 */             BetComputer.computGameBet(game);
/*  68 */             if ((game.isFinished()) && (bet.isParlay()) && (game.getStatus() < 100)) {
/*  69 */               bet.setParlayLost(true);
/*  70 */               bet.setStatus(0);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/*  75 */             BetComputer.computeLiveGameBet(game);
/*     */           }
/*     */         }
/*     */ 
/*  79 */         if (!bet.isParlayLost()) {
/*  80 */           BetComputer.computeBetStatus(bet);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  87 */       this.request.getSession().setAttribute("bets", bets);
/*     */     }
/*     */     else
/*     */     {
/*  91 */       bets = (Bets)this.request.getSession().getAttribute("bets");
/*     */     }
/*     */ 
/*  94 */     return this.view;
/*     */   }
/*     */ 
/*     */   public boolean isUpdateBetStatus() {
/*  98 */     return this.updateBetStatus;
/*     */   }
/*     */ 
/*     */   public void setUpdateBetStatus(boolean updateBetStatus) {
/* 102 */     this.updateBetStatus = updateBetStatus;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.DisplayBetsCommand
 * JD-Core Version:    0.6.2
 */