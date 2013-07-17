package com.eyesbet.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartupServlet
 */
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	

    public StartupServlet() {
        super();
        
      
    }

	
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		//String appVersion = request.getParameter("appVersion");
		
		//request.getSession().invalidate();
		
		
		response.setContentType("text/xml");
	     response.setHeader("Cache-Control", "no-cache");
	 
	     response.getWriter().write("<status value='true' />");
	     response.getWriter().close();
		
		
		
		
		
		
	}

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
	}

}
