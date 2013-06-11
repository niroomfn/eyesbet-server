 package com.eyesbet.web.listeners;
 
 import com.eyesbet.business.Tracker;
 import com.eyesbet.business.domain.User;
 import javax.servlet.http.HttpSessionBindingEvent;
 import javax.servlet.http.HttpSessionBindingListener;
 import javax.servlet.http.HttpSessionEvent;
 import javax.servlet.http.HttpSessionListener;
 import org.apache.log4j.Logger;
 
 public class SessionListener
   implements HttpSessionListener, HttpSessionBindingListener
 {
   private Logger logger = Logger.getLogger(SessionListener.class);
 
   public void valueUnbound(HttpSessionBindingEvent evt)
   {
     if (evt.getSession() != null)
     {
       this.logger.info("destroying session: " + evt.getSession().getId());
       Tracker.getInstance().removeUser(((User)evt.getSession().getAttribute("user")).getId());
     }
   }
 
   public void valueBound(HttpSessionBindingEvent evt)
   {
   }
 
   public void sessionCreated(HttpSessionEvent arg0)
   {
   }
 
   public void sessionDestroyed(HttpSessionEvent evt)
   {
     this.logger.info("destroying session: " + evt.getSession().getId());
     Tracker.getInstance().removeUser(((User)evt.getSession().getAttribute("user")).getId());
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.listeners.SessionListener
 * JD-Core Version:    0.6.2
 */