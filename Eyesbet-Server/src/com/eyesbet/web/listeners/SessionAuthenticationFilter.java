/*    */ package com.eyesbet.web.listeners;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class SessionAuthenticationFilter
/*    */   implements Filter
/*    */ {
/*    */   private String allowedPages;
/* 24 */   private Logger logger = Logger.getLogger(SessionAuthenticationFilter.class);
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 46 */     HttpServletRequest httpRequest = (HttpServletRequest)request;
/* 47 */     String requestedPage = getRequestedPage(httpRequest);
/* 48 */     if ((requestedPage == null) || (this.allowedPages.indexOf(requestedPage) >= 0) || (requestedPage.indexOf("m.") < 0))
/*    */     {
/* 50 */       chain.doFilter(request, response);
/*    */     }
/* 53 */     else if (((HttpServletRequest)request).getSession().getAttribute("user") == null) {
/* 54 */       if (requestedPage.indexOf("m.") >= 0) {
/* 55 */         this.logger.info("Mobile session expired");
/* 56 */         ((HttpServletResponse)response).setStatus(400);
/* 57 */         response.getWriter().write("Session expired. Please login again");
/*    */       }
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 68 */       chain.doFilter(request, response);
/*    */     }
/*    */   }
/*    */ 
/*    */   private String getRequestedPage(HttpServletRequest aHttpRequest)
/*    */   {
/* 80 */     String url = aHttpRequest.getRequestURI();
/* 81 */     if (url.indexOf("/") == 0) {
/* 82 */       return url.substring(1, url.length());
/*    */     }
/* 84 */     return null;
/*    */   }
/*    */ 
/*    */   public void init(FilterConfig config)
/*    */     throws ServletException
/*    */   {
/* 92 */     this.allowedPages = config.getInitParameter("allowedRequests");
/*    */   }
/*    */ }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.listeners.SessionAuthenticationFilter
 * JD-Core Version:    0.6.2
 */