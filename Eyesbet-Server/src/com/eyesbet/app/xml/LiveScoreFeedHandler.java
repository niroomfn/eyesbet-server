 package com.eyesbet.app.xml;
 
 import com.eyesbet.business.domain.Fixtures.Leagues;
 import com.eyesbet.business.domain.Game;
 import com.eyesbet.business.domain.Team;
 import java.util.Set;
 import org.xml.sax.Attributes;
 import org.xml.sax.SAXException;
 
 public class LiveScoreFeedHandler extends FixtureHandler
 {
   private Set<Game> games;
   private Game game ;
 
   public LiveScoreFeedHandler(Leagues tournament, Set<Game> games)
   {
     super(tournament);
     this.games = games;
     game = new Game(new Team(0,null), new Team(0,null), this.tournament);
   }
 
   public void startElement(String uri, String localName, String qName, Attributes attributes)
     throws SAXException
   {
     if (qName.equals("tournament"))
     {
 
       for (int i = 0; i < attributes.getLength(); i++)
       {
         if (attributes.getQName(i).equals("tournament_id"))
         {
           this.tournamentId = attributes.getValue(i);
           break;
         }
 
       }
 
     }
 
     if ((qName.equals("game")) && (this.tournamentId.equals(this.tournament.getLeagueId())))
     {
       updateGame(attributes);
     }
   }
   
   /**
    * 
    * @param att
    */
   private void updateGame(Attributes att)
   {
     int size = att.getLength();
     for (int i = 0; i < size; i++) {
       if (att.getQName(i).equals("id")) {
         game.setId(Integer.parseInt(att.getValue(i)));
       }
 
       if (att.getQName(i).equals("guest")) {
         game.getAway().setName(att.getValue(i));
       }
       if (att.getQName(i).equals("host"))
         game.getHome().setName(att.getValue(i));
       if (att.getQName(i).equals("away_total"))
       {
         game.getAway().setScore(Integer.parseInt(att.getValue(i)));
       }if (att.getQName(i).equals("home_total")) {
         game.getHome().setScore(Integer.parseInt(att.getValue(i)));
       }
       if (att.getQName(i).equals("status_type"))
         game.setStatusType(att.getValue(i));
       if (att.getQName(i).equals("status_desc")) {
         game.setStatusDesc(att.getValue(i));
       }
 
     }
     
 
     for (Game g : this.games)
     {
       if ((g.getGameId() == game.getGameId()))
       {
         g.getHome().setScore(game.getHome().getScore());
         g.getAway().setScore(game.getAway().getScore());
         g.setStatusType(game.getStatusType());
         g.setStatusDesc(game.getStatusDesc());
       }
     }
   }
 }

