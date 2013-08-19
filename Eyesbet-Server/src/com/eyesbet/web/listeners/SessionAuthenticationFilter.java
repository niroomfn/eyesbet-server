 package com.eyesbet.web.listeners;
 
 import java.io.IOException;
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.log4j.Logger;
 
 public class SessionAuthenticationFilter
   implements Filter
 {
   private String allowedPages;
   private Logger logger = Logger.getLogger(SessionAuthenticationFilter.class);
 
   public void destroy()
   {
   }
 
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
     throws IOException, ServletException
   {
     HttpServletRequest httpRequest = (HttpServletRequest)request;
     String requestedPage = getRequestedPage(httpRequest);
     if ((requestedPage == null) || (this.allowedPages.indexOf(requestedPage) >= 0)
    		 || (requestedPage.indexOf("m.") < 0))
     {
       chain.doFilter(request, response);
     }
     else if (((HttpServletRequest)request).getSession().getAttribute("user") == null) {
       if (requestedPage.indexOf("m.") >= 0) {
         this.logger.info("Mobile session expired");
         ((HttpServletResponse)response).setStatus(400);
         response.getWriter().write("Session expired. Please login again");
       }
 
     } 
     else
     {
       chain.doFilter(request, response);
     }
   }
 
   private String getRequestedPage(HttpServletRequest aHttpRequest)
   {
     String url = aHttpRequest.getRequestURI();
     if (url.indexOf("/") >= 0) {
       return url.substring(url.lastIndexOf("/"), url.length());
     }
     return null;
   }
 
   public void init(FilterConfig config)
     throws ServletException
   {
     this.allowedPages = config.getInitParameter("allowedRequests");
   }
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.web.listeners.SessionAuthenticationFilter
 * JD-Core Version:    0.6.2
 */