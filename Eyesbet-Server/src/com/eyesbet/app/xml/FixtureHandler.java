/*     */ package com.eyesbet.app.xml;
/*     */ 
/*     */ import com.eyesbet.business.domain.Fixture;
/*     */ import com.eyesbet.business.domain.Fixtures.Leagues;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ 
/*     */ public class FixtureHandler extends DefaultHandler
/*     */ {
/*     */   protected Leagues tournament;
/*     */   protected String tournamentId;
/*  17 */   private Set<Fixture> set = new TreeSet<Fixture>();
/*     */ 
/*     */   public FixtureHandler(Leagues tournament) {
/*  20 */     this.tournament = tournament;
/*     */   }
/*     */ 
/*     */   public void startElement(String uri, String localName, String qName, Attributes attributes)
/*     */     throws SAXException
/*     */   {
/*  26 */     if (qName.equals("tournament"))
/*     */     {
/*  28 */       int size = attributes.getLength();
/*     */ 
/*  30 */       for (int i = 0; i < size; i++)
/*     */       {
/*  32 */         if (attributes.getQName(i).equals("tournament_id"))
/*     */         {
/*  34 */           this.tournamentId = attributes.getValue(i);
/*     */ 
/*  38 */           break;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  48 */     if ((qName.equals("game")) && (this.tournamentId.equals(this.tournament.getLeagueId())))
/*     */     {
/*  50 */       addFixture(attributes);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addFixture(Attributes att)
/*     */   {
/*  85 */     int size = att.getLength();
/*  86 */     Fixture fixture = new Fixture();
/*  87 */     String time = null;
/*  88 */     String date = null;
/*  89 */     for (int i = 0; i < size; i++) {
/*  90 */       if (att.getQName(i).equals("id")) {
/*  91 */         fixture.setId(Integer.parseInt(att.getValue(i)));
/*     */       }
/*  93 */       if (att.getQName(i).equals("guest"))
/*  94 */         fixture.setAway(att.getValue(i));
/*  95 */       if (att.getQName(i).equals("host"))
/*  96 */         fixture.setHome(att.getValue(i));
/*  97 */       if (att.getQName(i).equals("date"))
/*  98 */         date = att.getValue(i);
/*  99 */       if (att.getQName(i).equals("hour")) {
/* 100 */         time = att.getValue(i);
/*     */       }
/*     */     }
/*     */ 
/* 104 */     fixture.setSchedule(date, time);
/* 105 */     this.set.add(fixture);
/*     */   }
/*     */ 
/*     */   public Set<Fixture> getFixtures()
/*     */   {
/* 113 */     return this.set;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.app.xml.FixtureHandler
 * JD-Core Version:    0.6.2
 */