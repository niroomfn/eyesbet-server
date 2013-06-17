 package com.eyesbet.dao;
 
 import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.BetType;
import com.eyesbet.business.domain.DeleteBetResult;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.GameBet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
 
 public class BetDao extends Dao
 {
   private static String insertBet = "insert into bets (user_id,bet_type,bet_name,created_date,modified_date) values(?,?,?,?,?)";
   private static String insertGame = "insert into game (id,bet_id,home,away,league,schedule) values (?,?,?,?,?,?)";
   private static String insertGameBet = "insert into game_bet (game_id,bet_id,bet_type,over_points,under_points,spread_point_team,spread_point,money_line,spread_point_favorite) values (?,?,?,?,?,?,?,?,?)";
 
   public void saveBet(int userId, Bet bet) throws Exception {
     Connection conn = null;
     ResultSet rs = null;
     ResultSet rs2 = null;
     try
     {
       conn = getConnection();
       conn.setAutoCommit(false);
       PreparedStatement prep = conn.prepareStatement(insertBet, 
         1);
       prep.setInt(1, userId);
       prep.setString(2, bet.getBetType().toString());
       prep.setString(3, bet.getName());
       prep.setString(4, "");
       prep.setString(5, " ");
       prep.executeUpdate();
       rs = prep.getGeneratedKeys();
       int betId = 0;
       if (rs.next()) {
         betId = rs.getInt(1);
         List<Game> list = bet.getGames();
         PreparedStatement prep2 = conn.prepareStatement(insertGame);
         PreparedStatement prep3 = conn.prepareStatement(insertGameBet);
         for (Game game : list) {
           prep2.setInt(1, game.getGameId());
           prep2.setInt(2, betId);
           prep2.setString(3, game.getHome().getName());
           prep2.setString(4, game.getAway().getName());
           prep2.setString(5, game.getLeage().toString());
           prep2.setString(6, game.getSchedule());
           prep2.execute();
           GameBet gamebet = game.getBet();
           prep3.setInt(1, game.getGameId());
           prep3.setInt(2, betId);
           prep3.setString(3, "");
           prep3.setString(4, gamebet.getOverPoints());
           prep3.setString(5, gamebet.getUnderPoints());
           prep3.setString(6, gamebet.getSpreadPointTeam());
           prep3.setString(7, gamebet.getSpreadPoint());
           prep3.setString(8, gamebet.getMoneyline());
           prep3.setString(9, gamebet.getSpreadPointFavorite());
           prep3.execute();
         }
 
       }
 
     }
     catch (SQLException e)
     {
       conn.rollback();
       throw new Exception(e);
     }
     finally
     {
       conn.commit();
       closeResultSet(rs);
       closeResultSet(rs2);
       closeConnection(conn);
     }
   }
 
   public void removeBet(int userId, int betId)
     throws Exception
   {
     Connection conn = null;
     try
     {
       conn = getConnection();
       PreparedStatement prep = conn
         .prepareStatement("delete from bets where user_id=? and id=?");
       prep.setInt(1, userId);
       prep.setInt(2, betId);
       prep.execute();
     } finally {
       closeConnection(conn);
     }
   }
 
   public boolean updateGameScores(Game game)
     throws Exception
   {
     Connection conn = null;
     ResultSet rs = null;
     try {
       conn = getConnection();
       PreparedStatement prep = conn
         .prepareStatement("select home_score, away_score from game_result where id=?");
       prep.setInt(1, game.getGameId());
       rs = prep.executeQuery();
       if (rs.next()) {
         game.getHome().setScore(rs.getInt("home_score"));
         game.getAway().setScore(rs.getInt("away_score"));
         game.setStatusType(Game.StatusType.finished.toString());
         game.setStatusDesc(Game.StatusType.finished.toString());
         return true;
       }
     }
     finally {
       closeResultSet(rs);
       closeConnection(conn);
     }
 
     return false;
   }
 
   public void saveGameResult(Set<Game> games)
     throws Exception
   {
     Connection conn = null;
     try {
       conn = getConnection();
       PreparedStatement prep = conn
         .prepareStatement("insert ignore into game_result (id,home,away,home_score,away_score) values (?,?,?,?,?)");
 
       for (Game game : games)
       {
         prep.setInt(1, game.getGameId());
         prep.setString(2, game.getHome().getName());
         prep.setString(3, game.getAway().getName());
         prep.setInt(4, game.getHome().getScore());
         prep.setInt(5, game.getAway().getScore());
         prep.execute();
       }
 
       prep.executeBatch();
     }
     finally {
       closeConnection(conn);
     }
   }
 


		  public void deleteParlayMoneylineBet(DeleteBetResult result) throws Exception {
			  		Connection conn = null;
			       ResultSet rs = null;
			       try {
			         conn = getConnection();
			         PreparedStatement prep = conn
			           .prepareStatement("delete from game where id=?");
			        
					  		  prep.setInt(1, result.getGame().getGameId());
					  		  
					  		  prep.execute();
					  		  
					  		  if (result.isTypeChanged()) {
					  			  
					  			  prep = conn.prepareStatement("update bets set bet_type=? where id=?");
					  			  prep.setString(1, BetType.straightWages.toString());
					  			  prep.setInt(2, result.getGame().getBetId());
					  			  prep.execute();
					  			  
					  		  }
					  
			       }
			       finally {
			         closeResultSet(rs);
			         closeConnection(conn);
			       }
			  
			  
		  }
		  
		  
		  public void deleteSpreadPointsBet(DeleteBetResult result) throws Exception {
			  
			  String sql = null;
			  boolean deleted = result.gameDeleted();
			  if (deleted) { 
				  
				  sql = "delete from game where id=?";
			  } else {
				  
				  
				  sql = "update game_bet set over_points=?, under_points=?, spread_point=?, spread_point_team=?, spread_point_favorite=? where id=?";
				  
			  }
			  Connection conn = null;
			       try {
			        conn = getConnection();
			        PreparedStatement prep = conn.prepareStatement(sql);
			         
			        if (deleted) {
			        	  prep.setInt(1, result.getGame().getGameId());
			        	  prep.execute();
			          
			        } else  {
			        	  	 
			        	  	GameBet gamebet = result.getGame().getBet();
					  		  prep.setString(1, gamebet.getOverPoints());
					  		  prep.setString(2, gamebet.getUnderPoints());
					  		  prep.setString(3, gamebet.getSpreadPoint());
					  		  prep.setString(4, gamebet.getSpreadPointTeam());
					  		  prep.setString(5, gamebet.getSpreadPointFavorite());
					  		  prep.setInt(6, gamebet.getId());
					  		  prep.execute();
			         
			          } 
			        
			         if (result.isTypeChanged()) {
			        	  
			        	  prep = conn.prepareStatement("update bets set bet_type=? where id=?");
			        	  prep.setString(1, BetType.straightWages.toString());
			        	  prep.setInt(2, result.getGame().getBetId());
			        	  prep.execute();
			          }
					  
			       }
			     finally {
			       closeConnection(conn);
			      }
			  
		  }
		  
		  
		  
		  public void changeBetName(int betId, String name) throws Exception {
			  
			  Connection conn = null;
		       try {
		         conn = getConnection();
		         PreparedStatement prep = conn
		           .prepareStatement("update bets set bet_name=? where id=?");
		        
				  		  prep.setString(1, name);
				  		  prep.setInt(2, betId);
				  		  
				  		  prep.execute();
				  		  
				  		
				  
		       }
		       finally {
		         closeConnection(conn);
		       }
			  
			  
		  }

}