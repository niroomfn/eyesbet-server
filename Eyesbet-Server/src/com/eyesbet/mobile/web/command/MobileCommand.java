 package com.eyesbet.mobile.web.command;
 
 import com.eyesbet.web.command.Command;
 import javax.servlet.http.HttpServletRequest;
 
 public abstract class MobileCommand extends Command
 {
   protected StringBuilder xmlResponse = new StringBuilder();
 
   public MobileCommand(HttpServletRequest request) { super(request); }
 
 }

/* Location:           C:\Users\farbod.niroomand.cor\Desktop\eyesbetwar\classes\
 * Qualified Name:     com.eyesbet.mobile.web.command.MobileCommand
 * JD-Core Version:    0.6.2
 */