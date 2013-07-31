package com.eyesbet.app;

public class Constants {
	
	private String jndiName = "jdbc/eyesbet-qa";
	
	
	private static Constants me = new Constants();
	
	
	
	private Constants() {}
	
	
	
	public static Constants getInstance() {
		
		return me;
	}



	public String getJndiName() {
		return jndiName;
	}



	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}
	
	
	
	
	
	
	
	
	

}
