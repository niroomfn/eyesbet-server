package com.eyesbet.web.listeners;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.eyesbet.app.Constants;

/**
 * Application Lifecycle Listener implementation class EyesbetContextListener
 *
 */
public class EyesbetContextListener implements ServletContextListener {
	
	private String conf_dir;

    /**
     * Default constructor. 
     */
    public EyesbetContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent context) {
        
    	init(context);
    	String mode = context.getServletContext().getInitParameter("mode");
    	String path = null;
    	if (mode.toLowerCase().equals("qa")) {
    		
    		path = conf_dir + "qa.properties";
    		
    	} else {
    		
    		// production environment
    		path = conf_dir + "prod.properties";
    	}
    	
    	
    	
    	Properties props = new Properties();
    	
    	try {
			props.load(new FileInputStream(path));
			
			Constants constants = Constants.getInstance();
			constants.setJndiName(props.getProperty("jndiName"));
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
    	
    	
    
    	
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
    
    private void init(ServletContextEvent context) {
    	
    	//this.conf_dir = context.getServletContext().getInitParameter("conf.dir");
    	 conf_dir = System.getProperty("catalina.home")+"/conf/eyesbet/";
    	
    	System.out.println("Conf.dir="+conf_dir);
    	File file = new File(conf_dir);
        String [] files = file.list();
        for (String f: files) {
        	System.out.println(f);
        }
    	if (conf_dir == null || !file.exists()) {
    		throw new RuntimeException("Unable to find configuration directory: "+file.getAbsolutePath());
    	}
    	
    	
    	
    	
    }
	
}
