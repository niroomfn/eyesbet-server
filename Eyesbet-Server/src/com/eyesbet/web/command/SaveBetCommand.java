/*     */ package com.eyesbet.web.command;
/*     */ 
/*     */ import com.eyesbet.business.domain.Bet;
/*     */ import com.eyesbet.business.domain.BetType;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.GameBet;
/*     */ import com.eyesbet.business.domain.Team;
/*     */ import com.eyesbet.dao.BetDao;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public class SaveBetCommand extends Command
/*     */ {
/*     */   public SaveBetCommand(HttpServletRequest request)
/*     */   {
/*  16 */     super(request);
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  23 */     String betType = this.request.getParameter("betType");
/*     */ 
/*  25 */     Bet bet = (Bet)this.request.getSession().getAttribute("bet");
/*  26 */     List<Game> list = bet.getGames();
/*     */     Iterator localIterator;
/*  28 */     if (bet.getBetType() == BetType.straightWages) {
/*  29 */       GameBet gamebet = null;
/*     */       Game game = null;
/*  30 */       if (betType.equals(BetType.moneyline.toString())) {
/*  31 */         String userInput = null;
/*  32 */         for (localIterator = list.iterator(); localIterator.hasNext(); ) { game = (Game)localIterator.next();
/*     */ 
/*  34 */           userInput = getGameMoneylineParameter(game.getGameBetName());
/*  35 */           gamebet = new GameBet(0);
/*  36 */           gamebet.setMoneyline(userInput);
/*  37 */           game.setBet(gamebet); }
/*     */       }
/*  39 */       else if (betType.equals(BetType.points.toString()))
/*     */       {
/*  41 */         for (Game g : list) {
/*  42 */           gamebet = setupPointsBet(g);
/*  43 */           game.setBet(gamebet);
/*     */         }
/*     */       }
/*     */     }
/*  47 */     else if (bet.getBetType() == BetType.parlay)
/*     */     {
/*  49 */       GameBet gamebet = null;
/*     */       Game game = null;
/*  50 */       if (betType.equals(BetType.moneyline.toString())) {
/*  51 */         String userInput = null;
/*  52 */         for (localIterator = list.iterator(); localIterator.hasNext(); ) { game = (Game)localIterator.next();
/*     */ 
/*  54 */           userInput = getGameMoneylineParameter(game.getGameBetName());
/*  55 */           gamebet = new GameBet(0);
/*  56 */           gamebet.setMoneyline(userInput);
/*  57 */           game.setBet(gamebet); }
/*     */       }
/*  59 */       else if (betType.equals(BetType.points.toString()))
/*     */       {
/*  61 */         for (Game g : list) {
/*  62 */           gamebet = setupPointsBet(g);
/*  63 */           game.setBet(gamebet);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  71 */     BetDao dao = new BetDao();
/*  72 */     dao.saveBet(getUserId(), bet);
/*  73 */     return this.view;
/*     */   }
/*     */ 
/*     */   private GameBet setupPointsBet(Game game)
/*     */   {
/*  79 */     GameBet gamebet = new GameBet(0);
/*     */     try {
/*  81 */       double p1 = Double.parseDouble(this.request.getParameter(game.getHome().getName() + "_spreadPoint"));
/*  82 */       gamebet.setSpreadPointTeam(game.getHome().getName());
/*  83 */       gamebet.setSpreadPoint(Double.toString(p1));
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException)
/*     */     {
/*     */     }
/*     */     try {
/*  89 */       double p2 = Double.parseDouble(this.request.getParameter(game.getAway().getName() + "_spreadPoint"));
/*  90 */       gamebet.setSpreadPointTeam(game.getAway().getName());
/*  91 */       gamebet.setSpreadPoint(Double.toString(p2));
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException1)
/*     */     {
/*     */     }
/*     */ 
/*  97 */     if (gamebet.isSpreadPoint()) {
/*  98 */       String value = this.request.getParameter(gamebet.getSpreadPointTeam() + "_fu");
/*  99 */       if ("-".equals(value)) {
/* 100 */         gamebet.setSpreadPointFavorite(gamebet.getSpreadPointTeam());
/*     */       }
/* 104 */       else if (!gamebet.getSpreadPointTeam().equals(game.getHome().getName()))
/* 105 */         gamebet.setSpreadPointFavorite(game.getHome().getName());
/*     */       else {
/* 107 */         gamebet.setSpreadPointFavorite(game.getAway().getName());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 114 */     gamebet.setOverPoints(this.request.getParameter(game.getGameBetName() + "_over"));
/* 115 */     gamebet.setUnderPoints(this.request.getParameter(game.getGameBetName() + "_under"));
/*     */ 
/* 124 */     return gamebet;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.SaveBetCommand
 * JD-Core Version:    0.6.2
 */