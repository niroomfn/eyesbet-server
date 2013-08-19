 package com.eyesbet.mobile.web.servlets;
 
 import com.eyesbet.mobile.web.command.AddBetCommand;
import com.eyesbet.mobile.web.command.BetDetailCommand;
import com.eyesbet.mobile.web.command.CancelTrackBetCommand;
import com.eyesbet.mobile.web.command.CreateBetCommand;
import com.eyesbet.mobile.web.command.DisplayMobileBetsCommand;
import com.eyesbet.mobile.web.command.EditBetCommand;
import com.eyesbet.mobile.web.command.LoginCommand;
import com.eyesbet.mobile.web.command.MobileCommand;
import com.eyesbet.mobile.web.command.RegisterCommand;
import com.eyesbet.mobile.web.command.RemoveBetCommand;
import com.eyesbet.mobile.web.command.ResetPasswordCommand;
import com.eyesbet.mobile.web.command.SaveBetCommand;
import com.eyesbet.mobile.web.command.StreamBetCommand;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
 
 public class MobileController extends HttpServlet
 {
   private static final long serialVersionUID = 1L;
   private Logger logger = Logger.getLogger(MobileController.class);
 
   public MobileController()
   {
     BasicConfigurator.configure();
   }
 
   protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
     
	 String xml = null;
     String contentType = "text/xml";
     try {
       String uri = request.getRequestURI();
       uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
       MobileCommand command = null;
       
       if ("m.login".equals(uri)) {
         this.logger.info("User: Signed In....");
         command = new LoginCommand(request);
         xml = command.execute();
       } else if ("m.createBet".equals(uri)) {
         command = new CreateBetCommand(request);
         xml = command.execute();
       } else if ("m.register".equals(uri))
       {
         command = new RegisterCommand(request);
         xml = command.execute();
       } else if ("m.saveBet".equals(uri))
       {
         command = new SaveBetCommand(request);
         xml = command.execute();
       } else if ("m.displayBets".equals(uri))
       {
         command = new DisplayMobileBetsCommand(request);
         xml = command.execute();
       } else if ("m.logout".equals(uri))
       {
         request.getSession().invalidate();
         this.logger.info("User loged out");
 
         xml = "<xml>s</xml>";
       } else if ("m.removeBet".equals(uri)) {
         command = new RemoveBetCommand(request);
 
         xml = command.execute();
       }
       else if ("m.betDetail".equals(uri)) {
         command = new BetDetailCommand(request);
         xml = command.execute();
       }
       else if ("m.trackBet".equals(uri))
       {
         command = new StreamBetCommand(request, response);
         xml = command.execute();
       }
       else if ("m.cancelTrackBet".equals(uri))
       {
         command = new CancelTrackBetCommand(request);
         xml = command.execute();
       }
				
		else if ("m.editBet".equals(uri)) {
					
			command = new EditBetCommand(request);
			xml = command.execute();
		} else if ("m.addBet".equals(uri)) {
			
			command = new AddBetCommand(request);
			xml = command.execute();
			
		} else if ("m.resetPassword".equals(uri)) {
			
			command = new ResetPasswordCommand(request);
			xml = command.execute();
			
		}
     }
     catch (Exception e)
     {
       this.logger.error("", e);
        xml = "Could not process your request. Please try again";
        contentType = "text/html";
     }
 
     response.setContentType(contentType);
     response.setHeader("Cache-Control", "no-cache");
 
     response.getWriter().write(xml);
     response.getWriter().flush();
     response.getWriter().close();
   }
 
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     doGet(request, response);
   }
 }

