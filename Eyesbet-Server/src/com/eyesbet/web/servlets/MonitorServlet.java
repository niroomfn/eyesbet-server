 package com.eyesbet.web.servlets;
 
 import com.eyesbet.business.BetComputer;
import com.eyesbet.business.Tracker;
import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.GameBet;
import com.eyesbet.business.domain.MonitorBet;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
 
 public class MonitorServlet extends HttpServlet
 {
   private static final long serialVersionUID = 1L;
   private Logger logger = Logger.getLogger(MonitorServlet.class);
   private Tracker monitor = Tracker.getInstance();
 
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     MonitorBet mbet = (MonitorBet)request.getSession().getAttribute("monitorBet");
     Set<Game> livegames = mbet.getLiveGames();
     Bet bet = mbet.getBet();
     String dt = getDisplayBetType(bet);
     this.logger.info("User monitoring bet type: " + bet.getBetType());
 
     StringBuilder xmlResponse = new StringBuilder();
 
     GameBet gamebet = null;
     this.logger.debug("number of live games: " + livegames.size());
     for (Game game : livegames)
     {
       BetComputer.computeLiveGameBet(game);
     }
 
     if ((livegames != null) && (livegames.size() > 0)) {
       BetComputer.computeBetStatus(bet);
       mbet.synchronizeTimestamps(monitor.getTimeStamps());
     }
     xmlResponse.append("<bet id='" + bet.getId() + "' dt='" + dt + "'   type='" + bet.getBetType() + "' name='" + bet.getName() + "' s='" + bet.getStatusText() + "' > ");
     for (Game game : livegames)
     {
       gamebet = game.getBet();
       if (gamebet.isMoneyline()) {
         createBetResponse(game, xmlResponse, true, ""+gamebet.getId());
         xmlResponse.append("team='" + gamebet.getMoneyline() + "' type='mn'  s='' />");
       }
       else
       {
         if (gamebet.isSpreadPoint()) {
           createBetResponse(game, xmlResponse, true, ""+gamebet.getId());
           xmlResponse.append(" points='" + gamebet.getSpreadPoint() + "' team='" + gamebet.getSpreadPointTeam());
           xmlResponse.append("' pointsign='" + gamebet.getSpreadPointAndSign() + "' ")
             .append(" s='' type='sp' bs='" + game.getSpreadPointBetScore() + "' />");
         }
         if (gamebet.isOverUnder())
         {
           createBetResponse(game, xmlResponse, true, gamebet.getId() + "ou");
           xmlResponse.append(" type='ou' ");
           xmlResponse.append(" s='' bs='" + game.getOverUnderBetScore() + "' ");
 
           if (gamebet.isUnder())
           {
             xmlResponse.append(" isunder='true'  u='" + gamebet.getUnderPoints() + "' ");
           }
           else
           {
             xmlResponse.append(" isunder='false' o='" + gamebet.getOverPoints() + "'");
           }
 
           xmlResponse.append(" />");
         }
 
       }
 
     }
 
     List<Game> games = bet.getNonLiveGames();
     for (Game game : games)
     {
       gamebet = game.getBet();
       if (gamebet.isMoneyline())
       {
         createBetResponse(game, xmlResponse, false, ""+gamebet.getId());
         xmlResponse.append("team='" + gamebet.getMoneyline() + "' type='mn' s='" + game.getStatusText() + "' />");
       }
       else
       {
         if (gamebet.isSpreadPoint()) {
           createBetResponse(game, xmlResponse, false, ""+gamebet.getId());
           xmlResponse.append(" points='" + gamebet.getSpreadPoint() + "' team='" + gamebet.getSpreadPointTeam() + "' ");
           xmlResponse.append("pointsign='" + gamebet.getSpreadPointAndSign() + "' ")
             .append("s='" + game.getSpreadPointStatusText() + "' type='sp' bs='" + game.getSpreadPointBetScore() + "' />");
         }
         if (gamebet.isOverUnder())
         {
           createBetResponse(game, xmlResponse, false, gamebet.getId() + "ou");
 
           xmlResponse.append(" s='" + game.getOnverUnderStatusText() + "' ");
 
           if (gamebet.isUnder())
           {
             xmlResponse.append(" isunder='true' u='" + gamebet.getUnderPoints() + "' ");
           }
           else
           {
             xmlResponse.append(" isunder='false' o='" + gamebet.getOverPoints() + "'");
           }
 
           xmlResponse.append(" type='ou' bs='" + game.getOverUnderBetScore() + "' />");
         }
 
       }
 
     }
 
     xmlResponse.append("</bet>");
 
     response.setContentType("text/xml");
     response.setHeader("Cache-Control", "no-cache");
 
     response.getWriter().write(xmlResponse.toString());
     response.getWriter().close();
   }
 
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     doGet(request, response);
   }
 
   @SuppressWarnings("unused")
			private void updateGameScores(Set<Game> games)
   {
     for (Game game : games)
       this.monitor.updateGame(game);
   }
 
   private void createBetResponse(Game game, StringBuilder xmlResponse, boolean live, String betId)
   {
     xmlResponse.append("<gamebet  live='" + live + "' gid='" + game.getGameId() + "' bid='" + betId + "' a='" + game.getAway().getName() + "' h='" + game.getHome().getName() + "' ");
 
     xmlResponse.append("as='" + game.getAway().getScore() + "' hs='" + game.getHome().getScore() + "' ");
 
     xmlResponse.append("sd='" + game.getStatusDesc() + "' ");
     
     if (live) {
    	 
    	 xmlResponse.append(" time='").append(monitor.getTimeStamps().getTimestamp(game.getGameId()));
    	 xmlResponse.append("' ");
     }
     
   }
 
   private String getDisplayBetType(Bet bet)
   {
     if (bet.isParlay())
     {
       if (bet.isMoneyline())
       {
         return "Money Line Parlay";
       }
 
       return "Points Parlay";
     }
 
     if (bet.isMoneyline()) {
       return "Money Line Bet";
     }
 
     return "Points Bet";
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.servlets.MonitorServlet
 * JD-Core Version:    0.6.2
 */