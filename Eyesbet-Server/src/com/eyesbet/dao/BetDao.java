/*     */ package com.eyesbet.dao;
/*     */ 
/*     */ import com.eyesbet.business.domain.Bet;
/*     */ import com.eyesbet.business.domain.Game;
/*     */ import com.eyesbet.business.domain.GameBet;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class BetDao extends Dao
/*     */ {
/*  19 */   private static String insertBet = "insert into bets (user_id,bet_type,bet_name,created_date,modified_date) values(?,?,?,?,?)";
/*  20 */   private static String insertGame = "insert into game (id,bet_id,home,away,league,schedule) values (?,?,?,?,?,?)";
/*  21 */   private static String insertGameBet = "insert into game_bet (game_id,bet_id,bet_type,over_points,under_points,spread_point_team,spread_point,money_line,spread_point_favorite) values (?,?,?,?,?,?,?,?,?)";
/*     */ 
/*     */   public void saveBet(int userId, Bet bet) throws Exception {
/*  24 */     Connection conn = null;
/*  25 */     ResultSet rs = null;
/*  26 */     ResultSet rs2 = null;
/*     */     try
/*     */     {
/*  29 */       conn = getConnection();
/*  30 */       conn.setAutoCommit(false);
/*  31 */       PreparedStatement prep = conn.prepareStatement(insertBet, 
/*  32 */         1);
/*  33 */       prep.setInt(1, userId);
/*  34 */       prep.setString(2, bet.getBetType().toString());
/*  35 */       prep.setString(3, bet.getName());
/*  36 */       prep.setString(4, "");
/*  37 */       prep.setString(5, " ");
/*  38 */       prep.executeUpdate();
/*  39 */       rs = prep.getGeneratedKeys();
/*  40 */       int betId = 0;
/*  41 */       if (rs.next()) {
/*  42 */         betId = rs.getInt(1);
/*  43 */         List<Game> list = bet.getGames();
/*  44 */         PreparedStatement prep2 = conn.prepareStatement(insertGame);
/*  45 */         PreparedStatement prep3 = conn.prepareStatement(insertGameBet);
/*  46 */         for (Game game : list) {
/*  47 */           prep2.setInt(1, game.getGameId());
/*  48 */           prep2.setInt(2, betId);
/*  49 */           prep2.setString(3, game.getHome().getName());
/*  50 */           prep2.setString(4, game.getAway().getName());
/*  51 */           prep2.setString(5, game.getLeage().toString());
/*  52 */           prep2.setString(6, game.getSchedule());
/*  53 */           prep2.execute();
/*  54 */           GameBet gamebet = game.getBet();
/*  55 */           prep3.setInt(1, game.getGameId());
/*  56 */           prep3.setInt(2, betId);
/*  57 */           prep3.setString(3, "");
/*  58 */           prep3.setString(4, gamebet.getOverPoints());
/*  59 */           prep3.setString(5, gamebet.getUnderPoints());
/*  60 */           prep3.setString(6, gamebet.getSpreadPointTeam());
/*  61 */           prep3.setString(7, gamebet.getSpreadPoint());
/*  62 */           prep3.setString(8, gamebet.getMoneyline());
/*  63 */           prep3.setString(9, gamebet.getSpreadPointFavorite());
/*  64 */           prep3.execute();
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/*  72 */       conn.rollback();
/*  73 */       throw new Exception(e);
/*     */     }
/*     */     finally
/*     */     {
/*  77 */       conn.commit();
/*  78 */       closeResultSet(rs);
/*  79 */       closeResultSet(rs2);
/*  80 */       closeConnection(conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeBet(int userId, int betId)
/*     */     throws Exception
/*     */   {
/*  89 */     Connection conn = null;
/*     */     try
/*     */     {
/*  92 */       conn = getConnection();
/*  93 */       PreparedStatement prep = conn
/*  94 */         .prepareStatement("delete from bets where user_id=? and id=?");
/*  95 */       prep.setInt(1, userId);
/*  96 */       prep.setInt(2, betId);
/*  97 */       prep.execute();
/*     */     } finally {
/*  99 */       closeConnection(conn);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean updateGameScores(Game game)
/*     */     throws Exception
/*     */   {
/* 107 */     Connection conn = null;
/* 108 */     ResultSet rs = null;
/*     */     try {
/* 110 */       conn = getConnection();
/* 111 */       PreparedStatement prep = conn
/* 112 */         .prepareStatement("select home_score, away_score from game_result where id=?");
/* 113 */       prep.setInt(1, game.getGameId());
/* 114 */       rs = prep.executeQuery();
/* 115 */       if (rs.next()) {
/* 116 */         game.getHome().setScore(rs.getInt("home_score"));
/* 117 */         game.getAway().setScore(rs.getInt("away_score"));
/* 118 */         game.setStatusType(Game.StatusType.finished.toString());
/* 119 */         game.setStatusDesc(Game.StatusType.finished.toString());
/* 120 */         return true;
/*     */       }
/*     */     }
/*     */     finally {
/* 124 */       closeResultSet(rs);
/* 125 */       closeConnection(conn);
/*     */     }
/*     */ 
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */   public void saveGameResult(Set<Game> games)
/*     */     throws Exception
/*     */   {
/* 135 */     Connection conn = null;
/*     */     try {
/* 137 */       conn = getConnection();
/* 138 */       PreparedStatement prep = conn
/* 139 */         .prepareStatement("insert ignore into game_result (id,home,away,home_score,away_score) values (?,?,?,?,?)");
/*     */ 
/* 141 */       for (Game game : games)
/*     */       {
/* 143 */         prep.setInt(1, game.getGameId());
/* 144 */         prep.setString(2, game.getHome().getName());
/* 145 */         prep.setString(3, game.getAway().getName());
/* 146 */         prep.setInt(4, game.getHome().getScore());
/* 147 */         prep.setInt(5, game.getAway().getScore());
/* 148 */         prep.execute();
/*     */       }
/*     */ 
/* 153 */       prep.executeBatch();
/*     */     }
/*     */     finally {
/* 156 */       closeConnection(conn);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.dao.BetDao
 * JD-Core Version:    0.6.2
 */