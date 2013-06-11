 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.business.domain.Bet;
 import com.eyesbet.business.domain.Bets;
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.web.command.DisplayBetsCommand;
 import java.util.List;
 import java.util.Set;
 import javax.servlet.http.HttpServletRequest;
 
 public class DisplayMobileBetsCommand extends MobileCommand
 {
   private DisplayBetsCommand command;
 
   public DisplayMobileBetsCommand(HttpServletRequest request)
   {
     super(request);
     this.command = new DisplayBetsCommand(request);
   }
 
   public String execute()
     throws Exception
   {
     this.command.execute();
 
     Bets bets = (Bets)this.request.getSession().getAttribute("bets");
     buildXml(bets);
 
     return bets.getXml();
   }
 
   private void buildXml(Bets bets)
   {
     List<Bet> list = null;
     Set<String> bettypes = bets.keySet();
     boolean hasLiveGame = false;
     if (this.request.getSession().getAttribute("liveGames") != null)
     {
       hasLiveGame = true;
     }
 
     this.xmlResponse.append("<xml livegame='" + hasLiveGame + "' >");
     for (String type : bettypes) {
       list = bets.get(type);
       this.xmlResponse.append("<bets type='" + type + "' >");
       for (Bet bet : list)
       {
         this.xmlResponse.append("<bet id='").append(bet.getId()).append("' name='" + bet.getName() + "' type='").append(bet.getBetType()).append("' s='" + bet.getStatusText() + "' >");
 
         List<Game> games = bet.getGames();
         for (Game game : games)
         {
           this.xmlResponse.append("<game a='" + game.getAway().getName() + "' h='" + game.getHome().getName() + "'");
           this.xmlResponse.append(" as='" + game.getAway().getScore() + "' hs='" + game.getHome().getScore() + "' stt='" + game.getStatusTypeText() + "' sch='" + game.getSchedule() + "' >");
 
           this.xmlResponse.append("</game>");
         }
 
         this.xmlResponse.append("</bet>");
       }
 
       this.xmlResponse.append("</bets>");
     }
 
     this.xmlResponse.append("</xml>");
 
     bets.setXml(this.xmlResponse.toString());
   }
 }
