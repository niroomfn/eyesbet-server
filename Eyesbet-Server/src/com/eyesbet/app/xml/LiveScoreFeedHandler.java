/*    */ package com.eyesbet.app.xml;
/*    */ 
/*    */ import com.eyesbet.business.domain.Fixtures.Leagues;
/*    */ import com.eyesbet.business.domain.Game;
/*    */ import com.eyesbet.business.domain.Team;
/*    */ import java.util.Set;
/*    */ import org.xml.sax.Attributes;
/*    */ import org.xml.sax.SAXException;
/*    */ 
/*    */ public class LiveScoreFeedHandler extends FixtureHandler
/*    */ {
/*    */   private Set<Game> games;
/*    */ 
/*    */   public LiveScoreFeedHandler(Leagues tournament, Set<Game> games)
/*    */   {
/* 17 */     super(tournament);
/* 18 */     this.games = games;
/*    */   }
/*    */ 
/*    */   public void startElement(String uri, String localName, String qName, Attributes attributes)
/*    */     throws SAXException
/*    */   {
/* 26 */     if (qName.equals("tournament"))
/*    */     {
/* 28 */       int size = attributes.getLength();
/*    */ 
/* 30 */       for (int i = 0; i < size; i++)
/*    */       {
/* 32 */         if (attributes.getQName(i).equals("tournament_id"))
/*    */         {
/* 35 */           this.tournamentId = attributes.getValue(i);
/* 36 */           break;
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 46 */     if ((qName.equals("game")) && (this.tournamentId.equals(this.tournament.getLeagueId())))
/*    */     {
/* 48 */       updateGame(attributes);
/*    */     }
/*    */   }
/*    */ 
/*    */   private void updateGame(Attributes att)
/*    */   {
/* 59 */     int size = att.getLength();
/* 60 */     Game game = new Game(new Team(0, null), new Team(0, null), this.tournament);
/* 61 */     for (int i = 0; i < size; i++) {
/* 62 */       if (att.getQName(i).equals("id")) {
/* 63 */         game.setId(Integer.parseInt(att.getValue(i)));
/*    */       }
/*    */ 
/* 66 */       if (att.getQName(i).equals("guest")) {
/* 67 */         game.getAway().setName(att.getValue(i));
/*    */       }
/* 69 */       if (att.getQName(i).equals("host"))
/* 70 */         game.getHome().setName(att.getValue(i));
/* 71 */       if (att.getQName(i).equals("away_total"))
/*    */       {
/* 73 */         game.getAway().setScore(Integer.parseInt(att.getValue(i)));
/* 74 */       }if (att.getQName(i).equals("home_total")) {
/* 75 */         game.getHome().setScore(Integer.parseInt(att.getValue(i)));
/*    */       }
/* 77 */       if (att.getQName(i).equals("status_type"))
/* 78 */         game.setStatusType(att.getValue(i));
/* 79 */       if (att.getQName(i).equals("status_desc")) {
/* 80 */         game.setStatusDesc(att.getValue(i));
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 86 */     for (Game g : this.games)
/*    */     {
/* 88 */       if ((g.getGameId() == game.getGameId()) && (g.getHome().getName().equals(game.getHome().getName())) && 
/* 89 */         (g.getAway().getName().equals(game.getAway().getName())))
/*    */       {
/* 91 */         g.getHome().setScore(game.getHome().getScore());
/* 92 */         g.getAway().setScore(game.getAway().getScore());
/* 93 */         g.setStatusType(game.getStatusType());
/* 94 */         g.setStatusDesc(game.getStatusDesc());
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.app.xml.LiveScoreFeedHandler
 * JD-Core Version:    0.6.2
 */