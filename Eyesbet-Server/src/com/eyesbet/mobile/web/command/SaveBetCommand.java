/*     */ package com.eyesbet.mobile.web.command;
/*     */ 
/*     */ import com.eyesbet.business.domain.Bet;
/*     */ import com.eyesbet.business.domain.BetType;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.GameBet;
/*     */ import com.eyesbet.dao.BetDao;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SaveBetCommand extends MobileCommand
/*     */ {
/*     */   public SaveBetCommand(HttpServletRequest request)
/*     */   {
/*  16 */     super(request);
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/*  24 */     String betType = this.request.getParameter("betType");
/*     */ 
/*  26 */     Bet bet = (Bet)this.request.getSession().getAttribute("bet");
/*  27 */     String name = this.request.getParameter("betName");
/*  28 */     if (name == null) name = betType;
/*  29 */     bet.setName(name);
/*  30 */     List<Game> list = bet.getGames();
/*     */     Iterator<Game> localIterator;
/*  32 */     if (bet.getBetType() == BetType.straightWages) {
/*  33 */       GameBet gamebet = null;
/*     */       Game game = null;
/*  34 */       if (betType.equals(BetType.moneyline.toString())) {
/*  35 */         String userInput = null;
/*  36 */         for (localIterator = list.iterator(); localIterator.hasNext(); ) { 
					game = (Game)localIterator.next();
/*     */ 
/*  38 */           userInput = getGameMoneylineParameter(""+game.getGameId());
/*  39 */           gamebet = new GameBet(0);
/*  40 */           gamebet.setMoneyline(userInput);
/*  41 */           game.setBet(gamebet); }
/*     */       }
/*  43 */       else if (betType.equals(BetType.points.toString()))
/*     */       {
/*  45 */         for (Game g : list) {
/*  46 */           gamebet = setupPointsBet(g);
/*  47 */           g.setBet(gamebet);
/*     */         }
/*     */       }
/*     */     }
/*  51 */     else if (bet.getBetType() == BetType.parlay)
/*     */     {
/*  53 */       GameBet gamebet = null;
/*     */       Game game = null;
/*  54 */       if (betType.equals(BetType.moneyline.toString())) {
/*  55 */         String userInput = null;
/*  56 */         for (localIterator = list.iterator(); localIterator.hasNext(); ) { 
				game = (Game)localIterator.next();
/*     */ 
/*  58 */           userInput = getGameMoneylineParameter(""+game.getGameId());
/*  59 */           gamebet = new GameBet(0);
/*  60 */           gamebet.setMoneyline(userInput);
/*  61 */           game.setBet(gamebet); }
/*     */       }
/*  63 */       else if (betType.equals(BetType.points.toString()))
/*     */       {
/*  65 */         for (Game g : list) {
/*  66 */           gamebet = setupPointsBet(g);
/*  67 */           g.setBet(gamebet);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  75 */     BetDao dao = new BetDao();
/*  76 */     dao.saveBet(getUserId(), bet);
/*  77 */     this.xmlResponse.append("<success />");
/*  78 */     return this.xmlResponse.toString();
/*     */   }
/*     */ 
/*     */   private GameBet setupPointsBet(Game game)
/*     */   {
/*  84 */     GameBet gamebet = new GameBet(0);
/*     */     try {
/*  86 */       double p1 = Double.parseDouble(this.request.getParameter(game.getHome().getName() + "_spreadPoint"));
/*  87 */       gamebet.setSpreadPointTeam(game.getHome().getName());
/*  88 */       gamebet.setSpreadPoint(Double.toString(p1));
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException)
/*     */     {
/*     */     }
/*     */     try {
/*  94 */       double p2 = Double.parseDouble(this.request.getParameter(game.getAway().getName() + "_spreadPoint"));
/*  95 */       gamebet.setSpreadPointTeam(game.getAway().getName());
/*  96 */       gamebet.setSpreadPoint(Double.toString(p2));
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException1)
/*     */     {
/*     */     }
/*     */ 
/* 102 */     if (gamebet.isSpreadPoint()) {
/* 103 */       System.out.println("spreadpointTeam: " + gamebet.getSpreadPointTeam());
/* 104 */       String value = this.request.getParameter(gamebet.getSpreadPointTeam() + "_fu");
/*     */ 
/* 107 */       if ("-".equals(value))
/*     */       {
/* 109 */         gamebet.setSpreadPointFavorite(gamebet.getSpreadPointTeam());
/*     */       }
/* 113 */       else if (!gamebet.getSpreadPointTeam().equals(game.getHome().getName()))
/* 114 */         gamebet.setSpreadPointFavorite(game.getHome().getName());
/*     */       else {
/* 116 */         gamebet.setSpreadPointFavorite(game.getAway().getName());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 123 */     gamebet.setOverPoints(this.request.getParameter(game.getGameId() + "_over"));
/* 124 */     gamebet.setUnderPoints(this.request.getParameter(game.getGameId() + "_under"));
/*     */ 
/* 133 */     return gamebet;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.SaveBetCommand
 * JD-Core Version:    0.6.2
 */