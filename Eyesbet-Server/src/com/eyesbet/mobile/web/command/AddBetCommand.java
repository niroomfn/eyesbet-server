package com.eyesbet.mobile.web.command;

import javax.servlet.http.HttpServletRequest;


public class AddBetCommand extends MobileCommand {

	public AddBetCommand(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		
		
		
		
		
		
		
	      
	     CreateBetCommand command = new CreateBetCommand(request);
	     command.setEditBet(true);
	     return command.execute();
		
		
	}
	
	
	

}
