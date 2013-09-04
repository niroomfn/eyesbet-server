 package com.eyesbet.web.servlets;
 
 import com.eyesbet.business.BetComputer;
import com.eyesbet.business.Tracker;
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
 
 public class LiveGameServlet extends HttpServlet
 {
   private static final long serialVersionUID = 1L;
   private Tracker monitor = Tracker.getInstance();
 
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     MonitorBet mbet = (MonitorBet)request.getSession().getAttribute("monitorBet");
     Set<Game> livegames = mbet.getLiveGames();
 
     //updateGameScores(livegames);
     
    List<Game> updatedGames =  mbet.synchronizeTimestamps(monitor.getTimeStamps());
     
     
 
     for (Game livegame : updatedGames)
     {
       BetComputer.computeLiveGameBet(livegame);
     }
 
     BetComputer.computeBetStatus(mbet.getBet());
 
     boolean isMoneyline = isMoneyline(livegames);
 
     StringBuilder xml = new StringBuilder("<bet id='" + mbet.getBet().getId() + "' >");
 
     if (isMoneyline) {
       for (Game game : livegames)
       {
         xml.append("<g t='mn' team='" + game.getBet().getMoneyline() + "' bid='" + game.getBet().getId() + "' a='" + game.getAway().getName() + "'  as='" + game.getAway().getScore() + "' ");
         xml.append("hs='" + game.getHome().getScore() + "' h='" + game.getHome().getName() + "' s='" + game.getStatus() + "' />");
       }
 
     }
     else
     {
       GameBet gamebet = null;
       for (Game game : livegames) {
         gamebet = game.getBet();
         if (gamebet.isSpreadPoint()) {
           xml.append("<g gid='" + game.getGameId() + "' a='" + game.getAway().getName() + "' h='" + game.getHome().getName() + "' as='" + game.getAway().getScore() + "' ");
           xml.append("hs='" + game.getHome().getScore() + "' t='sp' ");
           xml.append(" bid='").append(gamebet.getId()).append("' ");
           xml.append(" team='" + gamebet.getSpreadPointTeam() + "' ");
           xml.append("s='" + game.getSpreadPointStatus() + "' bs='" + game.getSpreadPointBetScore() + "' />");
         }
         if (gamebet.isOverUnder()) {
           xml.append("<g gid='" + game.getGameId() + "' a='" + game.getAway().getName() + "' h='" + game.getHome().getName() + "' as='" + game.getAway().getScore() + "' ");
           xml.append("hs='" + game.getHome().getScore() + "' ");
           xml.append("bid='").append(gamebet.getId()).append("ou' ");
           xml.append("t='ou' ").append("s='" + game.getOverUnderStatus() + "' bs='" + game.getOverUnderBetScore() + "' />");
         }
 
       }
 
     }
 
     xml.append("</bet>");
 
     response.setContentType("text/xml");
     response.setHeader("Cache-Control", "no-cache");
 
     response.getWriter().write(xml.toString());
     response.getWriter().close();
   }
 
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
   }
 
   private void updateGameScores(Set<Game> games)
   {
     for (Game game : games)
       this.monitor.updateGame(game);
   }
 
   private boolean isMoneyline(Set<Game> livegames)
   {
     for (Game game : livegames)
     {
       if (game.getBet().isMoneyline())
       {
         return true;
       }
 
     }
 
     return false;
   }
 }

