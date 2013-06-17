package com.eyesbet.mobile.web.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.eyesbet.business.Service;

public class ResetPasswordCommand extends MobileCommand {
	
	
	private Logger logger = Logger.getLogger(ResetPasswordCommand.class);

	public ResetPasswordCommand(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String execute() throws Exception {
		
		logger.info("Start of reset password...");
		
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		Service service = new Service();
		boolean result = service.validateResetPassword(username, email);
		
		if (result == true) {
			
			service.resetUserPassword(username, email, request.getParameter("password"));
			logger.info("success");
			return "<msg value='success' />";
		} else {
			
			logger.info("Failure");
			
			return "<msg value='not verified' />";
			
			
		}
		
		
		
		
	}

}
