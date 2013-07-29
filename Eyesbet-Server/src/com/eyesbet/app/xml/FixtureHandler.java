 package com.eyesbet.app.xml;
 
 import com.eyesbet.business.domain.Fixture;
 import com.eyesbet.business.domain.Fixtures.Leagues;
 import java.util.Set;
 import java.util.TreeSet;
 import org.xml.sax.Attributes;
 import org.xml.sax.SAXException;
 import org.xml.sax.helpers.DefaultHandler;
 
 public class FixtureHandler extends DefaultHandler
 {
   protected Leagues tournament;
   protected String tournamentId;
   private Set<Fixture> set = new TreeSet<Fixture>();
 
   public FixtureHandler(Leagues tournament) {
     this.tournament = tournament;
   }
 
   public void startElement(String uri, String localName, String qName, Attributes attributes)
     throws SAXException
   {
     if (qName.equals("tournament"))
     {
       int size = attributes.getLength();
 
       for (int i = 0; i < size; i++)
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
       addFixture(attributes);
     }
   }
 
   private void addFixture(Attributes att)
   {
     int size = att.getLength();
     Fixture fixture = new Fixture();
     String time = null;
     String date = null;
     for (int i = 0; i < size; i++) {
       if (att.getQName(i).equals("id")) {
         fixture.setId(Integer.parseInt(att.getValue(i)));
       }
       if (att.getQName(i).equals("guest"))
         fixture.setAway(att.getValue(i));
       if (att.getQName(i).equals("host"))
         fixture.setHome(att.getValue(i));
       if (att.getQName(i).equals("date"))
         date = att.getValue(i);
       if (att.getQName(i).equals("hour")) {
         time = att.getValue(i);
       }
     }
 
     fixture.setSchedule(date, time);
     this.set.add(fixture);
   }
 
   public Set<Fixture> getFixtures()
   {
     return this.set;
   }
 }

