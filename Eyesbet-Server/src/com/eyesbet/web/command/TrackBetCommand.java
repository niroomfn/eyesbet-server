 package com.eyesbet.web.command;
 
 import com.eyesbet.business.Tracker;
import com.eyesbet.business.domain.Game;

 import java.util.Set;
 import javax.servlet.http.HttpServletRequest;
 
 public class TrackBetCommand extends Command
 {
   public TrackBetCommand(HttpServletRequest request)
   {
     super(request);
     this.view = "betTrackerWindow.jsp";
   }
 
   @SuppressWarnings("unchecked")
public String execute()
     throws Exception
   {
     Tracker monitor = Tracker.getInstance();
 
     Set<Game> liveGames = (Set<Game>)this.request.getSession().getAttribute("liveGames");
 
     if ((liveGames != null) && (liveGames.size() > 0))
     {
       monitor.addGames(liveGames, getUserId());
     }
 
     return this.view;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.command.TrackBetCommand
 * JD-Core Version:    0.6.2
 */