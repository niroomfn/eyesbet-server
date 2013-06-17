package com.eyesbet.dao;

import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.BetType;
import com.eyesbet.business.domain.Bets;
import com.eyesbet.business.domain.Fixtures.Leagues;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.GameBet;
import com.eyesbet.business.domain.Team;
import com.eyesbet.business.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import org.apache.commons.lang.time.DateFormatUtils;

public class UserDao extends Dao
{
  private static String selectBets = "select * from bets where user_id=?";
  private static String selectGames = "select * from game where bet_id=?";
  private static String selectGameBet = "select * from game_bet where game_id=? and bet_id=?";
  private static String register = "insert into user_account (first_name,last_name,username,password,city,email) VALUES (?,?,?,SHA1(?),?,?)";
  private static String login = "select id, username, first_name, last_name, last_login_date from user_account where username=? and password=SHA1(?)";

  public User login(String username, String password) throws Exception
  {
    Connection conn = null;

    ResultSet rs = null;
    try {
      conn = getConnection();
      PreparedStatement prep = conn.prepareStatement(login, 1004, 1008);
      prep.setString(1, username);
      prep.setString(2, password);
      rs = prep.executeQuery();
      if (rs.next()) {
        User user = new User(rs.getInt("id"));

        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));

        rs.updateString("last_login_date", DateFormatUtils.format(new Date(), "MM/dd/yyyy hh:mm:ss"));
        rs.updateRow();
        return user;
      }

      return null;
    }
    finally {
      closeConnection(conn);
    }
  }

  public Bets getUserBets(int userId)
    throws Exception
  {
    Connection conn = null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    ResultSet rs3 = null;
    Bet bet = null;
    Bets bets = new Bets();
    Game game = null;
    GameBet gamebet = null;
    try {
      conn = getConnection();
      PreparedStatement prep = conn.prepareStatement(selectBets);
      PreparedStatement prep2 = conn.prepareStatement(selectGames);
      PreparedStatement prep3 = conn.prepareStatement(selectGameBet);
      prep.setInt(1, 1);
      rs = prep.executeQuery();
      while (rs.next())  
     
      {
        bet = new Bet(BetType.valueOf(rs.getString("bet_type")), rs.getInt("id"));
        bet.setCreatedDate(rs.getString("created_date"));
        bet.setModifiedDate(rs.getString("modified_date"));
        bet.setName(rs.getString("bet_name"));
        bets.addBet(bet);

        prep2.setInt(1, bet.getId());
        rs2 = prep2.executeQuery();
        while (rs2.next()) {
	        game = new Game(new Team(0, rs2.getString("home")), new Team(0, rs2.getString("away")),
	        		
	       Leagues.valueOf(rs2.getString("league")));
	        
	        
	        game.setId(rs2.getInt("id"));
	        game.setSchedule(rs2.getString("schedule"));
	        bet.addGame(game);
	
	        prep3.setInt(1, game.getGameId());
	        prep3.setInt(2, bet.getId());
	        rs3 = prep3.executeQuery();
	        while (rs3.next()) {
	          gamebet = new GameBet(rs3.getInt("id"));
	          gamebet.setMoneyline(rs3.getString("money_line"));
	          gamebet.setOverPoints(rs3.getString("over_points"));
	          gamebet.setUnderPoints(rs3.getString("under_points"));
	          gamebet.setSpreadPoint(rs3.getString("spread_point"));
	          gamebet.setSpreadPointTeam(rs3.getString("spread_point_team"));
	          gamebet.setSpreadPointFavorite(rs3.getString("spread_point_favorite"));
	          game.setBet(gamebet);
	        }
       }

      }

    
    }
    finally
    {
      closeResultSet(rs);
      closeResultSet(rs3);
      closeResultSet(rs2);
      closeConnection(conn);
    }

    return bets;
  }

  public void register(User user) throws Exception
  {
    Connection conn = null;
    try
    {
      conn = getConnection();
      PreparedStatement prep = conn.prepareStatement(register);
      prep.setString(1, user.getFirstName());
      prep.setString(2, user.getLastName());
      prep.setString(3, user.getUsername());
      prep.setString(4, user.getPassword());
      prep.setString(5, user.getCity());
      prep.setString(6, user.getEmail());
      prep.execute();
    }
    finally {
      closeConnection(conn);
    }
  }
  
  
  public boolean validateResetPassword(String username, String email) throws Exception {
	  
	   Connection conn = null;
	   ResultSet rs = null;
	    try
	    {
	      conn = getConnection();
	      PreparedStatement prep = conn.prepareStatement("select username, email from user_account where username=? and email=?");
	      prep.setString(1, username);
	      prep.setString(2, email);
	      rs = prep.executeQuery();
	      int count = 0;
	      while (rs.next()) {
	    	  
	    	  count++;
	    	  
	      }
	      
	      if (count == 1) return true;
	      else return false;
	      
	      
	      
	    }
	    finally {
	      this.closeResultSet(rs);
	      closeConnection(conn);
	    }
	  
  }
  
  
  public void resetPassword(String username, String email, String password) throws Exception {
	  
	  
	  Connection conn = null;
	    try
	    {
	      conn = getConnection();
	      PreparedStatement prep = conn.prepareStatement("update user_account set password=SHA1(?) where username=? and email=?");
	      prep.setString(1, password);
	      prep.setString(2, username);
	      prep.setString(3, email);
	      prep.executeUpdate();
	      
	      
	      
	    }
	    finally {
	      closeConnection(conn);
	    }
	  
	  
  }
  
  
}