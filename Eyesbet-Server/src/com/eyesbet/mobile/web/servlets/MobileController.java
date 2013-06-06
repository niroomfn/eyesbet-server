/*     */ package com.eyesbet.mobile.web.servlets;
/*     */ 
/*     */ import com.eyesbet.mobile.web.command.BetDetailCommand;
/*     */ import com.eyesbet.mobile.web.command.CancelTrackBetCommand;
/*     */ import com.eyesbet.mobile.web.command.CreateBetCommand;
/*     */ import com.eyesbet.mobile.web.command.DisplayMobileBetsCommand;
/*     */ import com.eyesbet.mobile.web.command.LoginCommand;
/*     */ import com.eyesbet.mobile.web.command.MobileCommand;
/*     */ import com.eyesbet.mobile.web.command.RegisterCommand;
/*     */ import com.eyesbet.mobile.web.command.RemoveBetCommand;
/*     */ import com.eyesbet.mobile.web.command.SaveBetCommand;
/*     */ import com.eyesbet.mobile.web.command.StreamBetCommand;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.log4j.BasicConfigurator;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class MobileController extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  28 */   private Logger logger = Logger.getLogger(MobileController.class);
/*     */ 
/*     */   public MobileController()
/*     */   {
/*  35 */     BasicConfigurator.configure();
/*     */   }
/*     */ 
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*  43 */     String xml = null;
/*  44 */     String contentType = "text/xml";
/*     */     try {
/*  46 */       String uri = request.getRequestURI();
/*  47 */       uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
/*  48 */       MobileCommand command = null;
/*  49 */       if ("m.login".equals(uri)) {
/*  50 */         this.logger.info("User: Signed In....");
/*  51 */         command = new LoginCommand(request);
/*  52 */         xml = command.execute();
/*  53 */       } else if ("m.createBet".equals(uri)) {
/*  54 */         command = new CreateBetCommand(request);
/*  55 */         xml = command.execute();
/*  56 */       } else if ("m.register".equals(uri))
/*     */       {
/*  58 */         command = new RegisterCommand(request);
/*  59 */         xml = command.execute();
/*  60 */       } else if ("m.saveBet".equals(uri))
/*     */       {
/*  62 */         command = new SaveBetCommand(request);
/*  63 */         xml = command.execute();
/*  64 */       } else if ("m.displayBets".equals(uri))
/*     */       {
/*  66 */         command = new DisplayMobileBetsCommand(request);
/*  67 */         xml = command.execute();
/*  68 */       } else if ("m.logout".equals(uri))
/*     */       {
/*  70 */         request.getSession().invalidate();
/*  71 */         this.logger.info("User loged out");
/*     */ 
/*  73 */         xml = "<xml>s</xml>";
/*  74 */       } else if ("m.removeBet".equals(uri)) {
/*  75 */         command = new RemoveBetCommand(request);
/*     */ 
/*  77 */         xml = command.execute();
/*     */       }
/*  79 */       else if ("m.betDetail".equals(uri)) {
/*  80 */         command = new BetDetailCommand(request);
/*  81 */         xml = command.execute();
/*     */       }
/*  83 */       else if ("m.trackBet".equals(uri))
/*     */       {
/*  85 */         command = new StreamBetCommand(request, response);
/*  86 */         xml = command.execute();
/*     */       }
/*  88 */       else if ("m.cancelTrackBet".equals(uri))
/*     */       {
/*  90 */         command = new CancelTrackBetCommand(request);
/*  91 */         xml = command.execute();
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  96 */       this.logger.error("", e);
/*     */     }
/*     */ 
/* 100 */     response.setContentType(contentType);
/* 101 */     response.setHeader("Cache-Control", "no-cache");
/*     */ 
/* 104 */     response.getWriter().write(xml);
/* 105 */     response.getWriter().close();
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 114 */     doGet(request, response);
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.servlets.MobileController
 * JD-Core Version:    0.6.2
 */