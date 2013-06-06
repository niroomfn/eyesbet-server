/*     */ package com.eyesbet.web.servlets;
/*     */ 
/*     */ import com.eyesbet.web.command.CloseWindowCommand;
/*     */ import com.eyesbet.web.command.Command;
/*     */ import com.eyesbet.web.command.CreateBetGameCommand;
/*     */ import com.eyesbet.web.command.DisplayBetsCommand;
/*     */ import com.eyesbet.web.command.DisplayLeaguesCommand;
/*     */ import com.eyesbet.web.command.LoginCommand;
/*     */ import com.eyesbet.web.command.RegisterCommand;
/*     */ import com.eyesbet.web.command.RemoveBetCommand;
/*     */ import com.eyesbet.web.command.SaveBetCommand;
/*     */ import com.eyesbet.web.command.TrackBetCommand;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.RequestDispatcher;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class Controller extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  29 */   private Logger logger = Logger.getLogger(Controller.class);
/*     */ 
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/*     */     try
/*     */     {
/*  47 */       String uri = request.getRequestURI();
/*  48 */       uri = uri.substring(uri.lastIndexOf("/") + 1, uri.length());
/*  49 */       Command command = null;
/*  50 */       String view = null;
/*  51 */       if ("login".equals(uri)) {
/*  52 */         command = new LoginCommand(request);
/*  53 */         view = command.execute();
/*     */       }
/*  56 */       else if ("createBet".equals(uri))
/*     */       {
/*  58 */         command = new CreateBetGameCommand(request);
/*  59 */         view = command.execute();
/*     */       }
/*  61 */       else if ("saveBet".equals(uri)) {
/*  62 */         command = new SaveBetCommand(request);
/*  63 */         view = command.execute();
/*  64 */         command = new DisplayBetsCommand(request);
/*  65 */         view = command.execute();
/*  66 */       } else if ("displayBets".equals(uri))
/*     */       {
/*  68 */         command = new DisplayBetsCommand(request);
/*  69 */         view = command.execute();
/*  70 */       } else if ("removeBet".equals(uri))
/*     */       {
/*  72 */         command = new RemoveBetCommand(request);
/*  73 */         command.execute();
/*     */ 
/*  75 */         command = new DisplayBetsCommand(request);
/*  76 */         ((DisplayBetsCommand)command).setUpdateBetStatus(false);
/*  77 */         view = command.execute();
/*  78 */       } else if ("trackBets".equals(uri)) {
/*  79 */         command = new TrackBetCommand(request);
/*  80 */         view = command.execute();
/*  81 */       } else if ("register".equals(uri))
/*     */       {
/*  83 */         command = new RegisterCommand(request);
/*  84 */         view = command.execute();
/*  85 */       } else if ("closeWindow".equals(uri)) {
/*  86 */         command = new CloseWindowCommand(request);
/*  87 */         view = command.execute();
/*     */       }
/*  89 */       else if ("displayLeagues".equals(uri)) {
/*  90 */         command = new DisplayLeaguesCommand(request);
/*  91 */         view = command.execute();
/*     */       }
/*  93 */       displayView(request, response, view);
/*     */     }
/*     */     catch (Exception e) {
/*  96 */       this.logger.error("", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response)
/*     */     throws ServletException, IOException
/*     */   {
/* 108 */     doGet(request, response);
/*     */   }
/*     */ 
/*     */   private void displayView(HttpServletRequest request, HttpServletResponse response, String view)
/*     */     throws Exception
/*     */   {
/* 116 */     if (request.getAttribute("redirect") != null) {
/* 117 */       response.sendRedirect(view);
/* 118 */       return;
/*     */     }
/*     */ 
/* 121 */     if (view != null) {
/* 122 */       forwardMessage(request);
/*     */ 
/* 124 */       RequestDispatcher rd = request.getRequestDispatcher(view);
/*     */ 
/* 126 */       rd.forward(request, response);
/*     */     }
/*     */     else;
/*     */   }
/*     */ 
/*     */   private void forwardMessage(HttpServletRequest request)
/*     */   {
/* 136 */     request.setAttribute("message", request.getAttribute("message"));
/*     */   }
/*     */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.servlets.Controller
 * JD-Core Version:    0.6.2
 */