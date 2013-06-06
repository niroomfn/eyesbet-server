/*     */ package com.eyesbet.business;
/*     */ 
/*     */ import com.eyesbet.business.domain.Bet;
/*     */ import com.eyesbet.business.domain.Bets;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class BetComputer
/*     */ {
/*  15 */   private static BetComputer moneylineComputer = new MoneylineComputer();
/*  16 */   private static BetComputer overunderComputer = new OverunderComputer();
/*  17 */   private static BetComputer spreadpointComputer = new SpreadpointComputer();
/*     */ 
/*     */   public abstract int computeLiveGameStatus(Game paramGame)
/*     */     throws Exception;
/*     */ 
/*     */   public abstract int computeGameStatus(Game paramGame)
/*     */     throws Exception;
/*     */ 
/*     */   public static int computeLiveGameBet(Game game)
/*     */   {
/*  30 */     int counter = 0;
/*  31 */     int overunderStatus = 0;
/*  32 */     int spreadpointStatus = 0;
/*  33 */     if (game.getBet().isMoneyline())
/*     */       try
/*     */       {
/*  36 */         game.setStatus(moneylineComputer.computeLiveGameStatus(game));
/*     */       }
/*     */       catch (Exception e) {
/*  39 */         e.printStackTrace();
/*     */       }
/*  41 */     if (game.getBet().isOverUnder()) {
/*  42 */       counter++;
/*     */       try {
/*  44 */         overunderStatus = overunderComputer.computeLiveGameStatus(game);
/*  45 */         game.setOverUnderStatus(overunderStatus);
/*  46 */         game.setStatus(overunderStatus);
/*     */       }
/*     */       catch (Exception e) {
/*  49 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  52 */     if (game.getBet().isSpreadPoint()) {
/*  53 */       counter++;
/*     */       try {
/*  55 */         spreadpointStatus = spreadpointComputer.computeLiveGameStatus(game);
/*  56 */         game.setSpreadPointStatus(spreadpointStatus);
/*  57 */         game.setStatus(spreadpointStatus);
/*     */       }
/*     */       catch (Exception e) {
/*  60 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*  64 */     if (counter == 2)
/*     */     {
/*  66 */       game.setStatus((spreadpointStatus + overunderStatus) / 2);
/*     */     }
/*     */ 
/*  71 */     return game.getStatus();
/*     */   }
/*     */ 
/*     */   public static int computGameBet(Game game)
/*     */   {
/*     */     try
/*     */     {
/*  78 */       if (game.getBet().isMoneyline())
/*     */       {
/*  80 */         game.setStatus(moneylineComputer.computeGameStatus(game));
/*     */       }
/*     */       else {
/*  83 */         int count = 0;
/*  84 */         int spreadStatus = 0;
/*  85 */         int overunderStatus = 0;
/*  86 */         if (game.getBet().isOverUnder()) {
/*  87 */           count++;
/*  88 */           overunderStatus = overunderComputer.computeGameStatus(game);
/*  89 */           game.setOverUnderStatus(overunderStatus);
/*  90 */           game.setStatus(overunderStatus);
/*     */         }
/*  92 */         if (game.getBet().isSpreadPoint()) {
/*  93 */           count++;
/*  94 */           spreadStatus = spreadpointComputer.computeGameStatus(game);
/*  95 */           game.setSpreadPointStatus(spreadStatus);
/*  96 */           game.setStatus(spreadStatus);
/*     */         }
/*     */ 
/* 100 */         if (count == 2)
/*     */         {
/* 102 */           game.setStatus((spreadStatus + overunderStatus) / 2);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 109 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 113 */     return game.getStatus();
/*     */   }
/*     */ 
/*     */   public static String computeLiveBetStatus(Bets bets) {
/* 117 */     StringBuilder sb = new StringBuilder("<bets>");
/* 118 */     List<Game> games = null;
/* 119 */     int sum = 0;
/* 120 */     int count = 0;
/* 121 */     for (Bet bet : bets.getBets()) {
/* 122 */       sum = 0;
/* 123 */       games = bet.getGames();
/* 124 */       count = 0;
/* 125 */       for (Game game : games) {
/* 126 */         if (game.getStatus() != 1) {
/* 127 */           count++;
/* 128 */           sum += game.getStatus();
/*     */         }
/*     */       }
/* 131 */       if (count == 0) bet.setStatus(1);
/* 132 */       else if (count > 0) {
/* 133 */         bet.setStatus(sum / count);
/*     */       }
/* 135 */       sb.append("<bet id='" + bet.getId() + "' s='" + bet.getStatus() + "'/>");
/*     */     }
/* 137 */     sb.append("</bets></data>");
/* 138 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static Bet computeBetStatus(Bet bet)
/*     */   {
/* 143 */     int sum = 0;
/* 144 */     List<Game> games = bet.getGames();
/* 145 */     int count = 0;
/* 146 */     for (Game game : games) {
/* 147 */       if (game.getStatus() != 1) {
/* 148 */         count++;
/* 149 */         sum += game.getStatus();
/*     */       }
/*     */     }
/*     */ 
/* 153 */     if (count == 0) bet.setStatus(1);
/* 154 */     else if (count > 0) {
/* 155 */       bet.setStatus(sum / count);
/*     */     }
/* 157 */     return bet;
/*     */   }
/*     */ 
/*     */   public static enum TightStatus
/*     */   {
/*  24 */     tight;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.business.BetComputer
 * JD-Core Version:    0.6.2
 */