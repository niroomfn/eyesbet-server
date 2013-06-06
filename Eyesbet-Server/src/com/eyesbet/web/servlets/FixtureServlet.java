/*     */ package com.eyesbet.web.servlets;
/*     */ 
/*     */ import com.eyesbet.business.FixturesLoader;
/*     */ import com.eyesbet.business.domain.Fixture;
/*     */ import com.eyesbet.business.domain.Fixtures;
/*     */ import com.eyesbet.util.DateTime;
/*     */ import java.io.IOException;
/*     */ import java.util.Date;
/*     */ import java.util.Set;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class FixtureServlet extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  23 */   private Logger logger = Logger.getLogger(FixtureServlet.class);
/*     */   private Date loadDate;
/*     */ 
/*     */   public FixtureServlet()
/*     */   {
/*  32 */     FixturesLoader loader = new FixturesLoader(DateTime.getDefaultTimeZone());
/*     */     try
/*     */     {
/*  36 */       this.loadDate = new Date();
/*     */     } catch (Exception e) {
/*  38 */       this.logger.error("Error loading fixtures", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  51 */     StringBuilder xml = null;
/*     */ 
/*  53 */     String league = request.getParameter("league");
/*     */ 
/*  55 */     if (league != null)
/*     */     {
/*  57 */       Fixtures.Leagues leagues = Fixtures.Leagues.valueOf(league);
/*     */ 
/*  59 */       xml = convertToXml(leagues);
/*     */     }
/*     */     else
/*     */     {
/*  64 */       String[] order = Fixtures.Leagues.getOrder();
/*  65 */       xml = new StringBuilder("<fixtures>");
/*  66 */       for (String s : order) {
/*  67 */         xml.append("<league n='" + s + "' />");
/*     */       }
/*     */ 
/*  72 */       Set<Fixture> list = Fixtures.getInstance().getLeagueFixtures(Fixtures.Leagues.valueOf(order[0]));
/*  73 */       for (Fixture f : list) {
/*  74 */         xml.append("<fixture id='").append(f.getFixtureId()).append("'");
/*  75 */         xml.append(" h='").append(f.getHome()).append("'");
/*  76 */         xml.append(" a='").append(f.getAway()).append("'");
/*  77 */         xml.append(" s='").append(f.getSchedule()).append("' />");
/*     */       }
/*     */ 
/*  83 */       xml.append("</fixtures>");
/*     */     }
/*     */ 
/*  96 */     response.setContentType("text/xml");
/*  97 */     response.setHeader("Cache-Control", "no-cache");
/*     */ 
/* 100 */     response.getWriter().write(xml.toString());
/* 101 */     response.getWriter().close();
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*     */   }
/*     */ 
/*     */   private StringBuilder convertToXml(Fixtures.Leagues league)
/*     */   {
/* 144 */     StringBuilder xml = new StringBuilder("<fixtures >");
/*     */ 
/* 146 */     xml.append("<league n='").append(league.toString()).append("' >");
/* 147 */     Set<Fixture> list = Fixtures.getInstance().getLeagueFixtures(league);
/* 148 */     for (Fixture f : list) {
/* 149 */       xml.append("<fixture id='").append(f.getFixtureId()).append("'");
/* 150 */       xml.append(" h='").append(f.getHome()).append("'");
/* 151 */       xml.append(" a='").append(f.getAway()).append("'");
/* 152 */       xml.append(" s='").append(f.getSchedule()).append("' />");
/*     */     }
/*     */ 
/* 157 */     xml.append("</league></fixtures>");
/*     */ 
/* 160 */     return xml;
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.servlets.FixtureServlet
 * JD-Core Version:    0.6.2
 */