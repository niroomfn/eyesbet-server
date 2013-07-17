 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.Service;
import com.eyesbet.business.Tracker;
import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.Bets;
import com.eyesbet.business.domain.Game;
import com.eyesbet.business.domain.MonitorBet;
import com.eyesbet.business.domain.SortedGames;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
 
 @SuppressWarnings("unused")
public class StreamBetCommand extends MobileCommand
 {
   private static Logger logger = Logger.getLogger(StreamBetCommand.class);
   private HttpServletResponse response;
 
   public StreamBetCommand(HttpServletRequest request, HttpServletResponse response)
   {
     super(request);
     this.response = response;
   }
 
   public String execute()
     throws Exception
   {
     Bets bets = (Bets)this.request.getSession().getAttribute("bets");
 
     if (this.request.getParameter("betId") == null)
     {
       if (this.request.getParameter("cmd") == null)
       {
         this.xmlResponse.append("<bets>");
 
         for (Bet bet : bets.getBets())
         {
           this.xmlResponse.append("<bet n='" + bet.getName() + "' v='" + bet.getId() + "' />");
         }
 
        /* Service service = new Service();
         SortedGames sgames = service.sortByGameStartTime(bets);
         List<Game> games = sgames.getGames();
         this.xmlResponse.append("<games>");
         for (Game g : games)
         {
           this.xmlResponse.append("<game g='" + g.getAway().getName() + " @ " + g.getHome().getName() + "' st='" + SortedGames.getStartTime(g) + "' ");
           this.xmlResponse.append("bets='" + sgames.getBetNames(g.getGameId()) + "' />");
         }*/
 
         this.xmlResponse.append("</bets>");
 
         return this.xmlResponse.toString();
       }
 
       logger.info("User canceled streamming");
       Tracker tracker = Tracker.getInstance();
       tracker.removeUser(getUserId());
       this.request.getSession().removeAttribute("monitorBet");
       return "<success />";
     }
 
     logger.info("User Streamming bet...");
 
     Tracker monitor = Tracker.getInstance();
 
     int betId = Integer.parseInt(this.request.getParameter("betId"));
 
     Bet bet = bets.getBet(betId);
     if (bet == null) {
       return "";
     }
 
     Set<Game> livegames = bet.getLiveGames();
     logger.debug("Live games: " + livegames.size());
 
     MonitorBet monitorBet = new MonitorBet(bet);
     this.request.getSession().setAttribute("monitorBet", monitorBet);
     monitorBet.setLiveGames(livegames);
     if ((livegames != null) && (livegames.size() > 0))
     {
       monitor.addGames(livegames, getUserId());
     }
 
     this.request.getServletContext().getRequestDispatcher("/monitor").forward(this.request, this.response);
     return "";
   }
 }

