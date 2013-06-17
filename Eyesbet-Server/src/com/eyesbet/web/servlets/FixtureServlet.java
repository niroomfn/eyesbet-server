 package com.eyesbet.web.servlets;
 
 import com.eyesbet.business.FixturesLoader;
 import com.eyesbet.business.domain.Fixture;
 import com.eyesbet.business.domain.Fixtures;
import com.eyesbet.business.quartz.FixtureScheduler;
import com.eyesbet.test.FixtureLoaderTest;
 import com.eyesbet.util.DateTime;
 import java.io.IOException;
 import java.util.Date;
 import java.util.Set;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
 import org.apache.log4j.Logger;
 
 public class FixtureServlet extends HttpServlet
 {
   private static final long serialVersionUID = 1L;
   private Logger logger = Logger.getLogger(FixtureServlet.class);
   private Date loadDate;
 
   public FixtureServlet()
   {
     	BasicConfigurator.configure();
	
				FixturesLoader loader = new FixturesLoader(DateTime.getDefaultTimeZone());

				FixtureScheduler scheduler = new FixtureScheduler();
				FixtureLoaderTest testLoader = new FixtureLoaderTest();
				testLoader.loadNBAFixtures();
     try
     {
				//scheduler.schedule();
       this.loadDate = new Date();
     } catch (Exception e) {
       this.logger.error("Error loading fixtures", e);
     }
   }
 
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     StringBuilder xml = null;
 
     String league = request.getParameter("league");
 
     if (league != null)
     {
       Fixtures.Leagues leagues = Fixtures.Leagues.valueOf(league);
 
       xml = convertToXml(leagues);
     }
     else
     {
       String[] order = Fixtures.Leagues.getOrder();
       xml = new StringBuilder("<fixtures>");
       for (String s : order) {
         xml.append("<league n='" + s + "' />");
       }
 
       Set<Fixture> list = Fixtures.getInstance().getLeagueFixtures(Fixtures.Leagues.valueOf(order[0]));
       for (Fixture f : list) {
         xml.append("<fixture id='").append(f.getFixtureId()).append("'");
         xml.append(" h='").append(f.getHome()).append("'");
         xml.append(" a='").append(f.getAway()).append("'");
         xml.append(" s='").append(f.getSchedule()).append("' />");
       }
 
       xml.append("</fixtures>");
     }
 
     response.setContentType("text/xml");
     response.setHeader("Cache-Control", "no-cache");
 
     response.getWriter().write(xml.toString());
     response.getWriter().close();
   }
 
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
   }
 
   private StringBuilder convertToXml(Fixtures.Leagues league)
   {
     StringBuilder xml = new StringBuilder("<fixtures >");
 
     xml.append("<league n='").append(league.toString()).append("' >");
     Set<Fixture> list = Fixtures.getInstance().getLeagueFixtures(league);
     if (list != null) {
     for (Fixture f : list) {
       xml.append("<fixture id='").append(f.getFixtureId()).append("'");
       xml.append(" h='").append(f.getHome()).append("'");
       xml.append(" a='").append(f.getAway()).append("'");
       xml.append(" s='").append(f.getSchedule()).append("' />");
     }
     
     }
 
     xml.append("</league></fixtures>");
 
     return xml;
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.servlets.FixtureServlet
 * JD-Core Version:    0.6.2
 */