/*    */ package com.eyesbet.web.listeners;
/*    */ 
/*    */ import com.eyesbet.business.Tracker;
/*    */ import com.eyesbet.business.domain.User;
/*    */ import javax.servlet.http.HttpSessionBindingEvent;
/*    */ import javax.servlet.http.HttpSessionBindingListener;
/*    */ import javax.servlet.http.HttpSessionEvent;
/*    */ import javax.servlet.http.HttpSessionListener;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class SessionListener
/*    */   implements HttpSessionListener, HttpSessionBindingListener
/*    */ {
/* 18 */   private Logger logger = Logger.getLogger(SessionListener.class);
/*    */ 
/*    */   public void valueUnbound(HttpSessionBindingEvent evt)
/*    */   {
/* 30 */     if (evt.getSession() != null)
/*    */     {
/* 32 */       this.logger.info("destroying session: " + evt.getSession().getId());
/* 33 */       Tracker.getInstance().removeUser(((User)evt.getSession().getAttribute("user")).getId());
/*    */     }
/*    */   }
/*    */ 
/*    */   public void valueBound(HttpSessionBindingEvent evt)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void sessionCreated(HttpSessionEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void sessionDestroyed(HttpSessionEvent evt)
/*    */   {
/* 58 */     this.logger.info("destroying session: " + evt.getSession().getId());
/* 59 */     Tracker.getInstance().removeUser(((User)evt.getSession().getAttribute("user")).getId());
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.listeners.SessionListener
 * JD-Core Version:    0.6.2
 */