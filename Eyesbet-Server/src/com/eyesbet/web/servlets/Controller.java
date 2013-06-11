 package com.eyesbet.web.servlets;
 
 import com.eyesbet.web.command.CloseWindowCommand;
 import com.eyesbet.web.command.Command;
 import com.eyesbet.web.command.CreateBetGameCommand;
 import com.eyesbet.web.command.DisplayBetsCommand;
 import com.eyesbet.web.command.DisplayLeaguesCommand;
 import com.eyesbet.web.command.LoginCommand;
 import com.eyesbet.web.command.RegisterCommand;
 import com.eyesbet.web.command.RemoveBetCommand;
 import com.eyesbet.web.command.SaveBetCommand;
 import com.eyesbet.web.command.TrackBetCommand;
 import java.io.IOException;
 import javax.servlet.RequestDispatcher;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.log4j.Logger;
 
 public class Controller extends HttpServlet
 {
   private static final long serialVersionUID = 1L;
   private Logger logger = Logger.getLogger(Controller.class);
 
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     try
     {
       String uri = request.getRequestURI();
       uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
       Command command = null;
       String view = null;
       if ("login".equals(uri)) {
         command = new LoginCommand(request);
         view = command.execute();
       }
       else if ("createBet".equals(uri))
       {
         command = new CreateBetGameCommand(request);
         view = command.execute();
       }
       else if ("saveBet".equals(uri)) {
         command = new SaveBetCommand(request);
         view = command.execute();
         command = new DisplayBetsCommand(request);
         view = command.execute();
       } else if ("displayBets".equals(uri))
       {
         command = new DisplayBetsCommand(request);
         view = command.execute();
       } else if ("removeBet".equals(uri))
       {
         command = new RemoveBetCommand(request);
         command.execute();
 
         command = new DisplayBetsCommand(request);
         ((DisplayBetsCommand)command).setUpdateBetStatus(false);
         view = command.execute();
       } else if ("trackBets".equals(uri)) {
         command = new TrackBetCommand(request);
         view = command.execute();
       } else if ("register".equals(uri))
       {
         command = new RegisterCommand(request);
         view = command.execute();
       } else if ("closeWindow".equals(uri)) {
         command = new CloseWindowCommand(request);
         view = command.execute();
       }
       else if ("displayLeagues".equals(uri)) {
         command = new DisplayLeaguesCommand(request);
         view = command.execute();
       }
       displayView(request, response, view);
     }
     catch (Exception e) {
       this.logger.error("", e);
     }
   }
 
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     doGet(request, response);
   }
 
   private void displayView(HttpServletRequest request, HttpServletResponse response, String view)
     throws Exception
   {
     if (request.getAttribute("redirect") != null) {
       response.sendRedirect(view);
       return;
     }
 
     if (view != null) {
       forwardMessage(request);
 
       RequestDispatcher rd = request.getRequestDispatcher(view);
 
       rd.forward(request, response);
     }
     else;
   }
 
   private void forwardMessage(HttpServletRequest request)
   {
     request.setAttribute("message", request.getAttribute("message"));
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.servlets.Controller
 * JD-Core Version:    0.6.2
 */