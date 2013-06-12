package com.eyesbet.mobile.web.command;


import javax.servlet.http.HttpServletRequest;
import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.BetType;
import com.eyesbet.business.domain.Bets;
import com.eyesbet.business.domain.DeleteBetResult;
import com.eyesbet.dao.BetDao;

public class EditBetCommand extends MobileCommand {
	private BetDao dao= new BetDao();

	public EditBetCommand(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		
		
		String cmd = request.getParameter("cmd");
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		int betId = Integer.parseInt(request.getParameter("betId"));
		String type = request.getParameter("type");
		BetType betType = null;

		if ("ou".equals(type)) betType = BetType.overUnder;
		else if ("sp".equals(type)) betType = BetType.spreadPoint;
		else if ("mn".equals(type)) betType = BetType.moneyline;
		
		
		
		if ("deleteParlay".equals(cmd)) {
			
		  deleteParlayBet(betId,gameId, betType);
		
		}
		
		else if (cmd.indexOf("add") >= 0) {
			 if ("addStraightWages".equals(cmd)) {
					
					if (betType == BetType.moneyline) {
						
						
					}else {
						
						
					}
					
				} else if ("addParlayMoneyline".equals(cmd)) {
					
					
				} else if ("addParlayPoints".equals(cmd)) {
					
					
				}
			
		}
		
		
		
		BetDetailCommand detail = new BetDetailCommand(request);
		detail.removeSession();
		detail.execute();
		return "<success />";
		
		
		
	}
	
	
	private void deleteParlayBet(int betId, int gameId, BetType betType) throws Exception {
		
		Bets bets = (Bets)request.getSession().getAttribute("bets");
		
		Bet bet = bets.getBet(betId);
		
		DeleteBetResult result = bet.deleteGamebet(gameId, betType);
					
		if (result.isTypeChanged()) {
			System.out.println("******type changed**********");
			bet.setBetType(BetType.straightWages);
			bets.resetBet(bet);
		}
		
		
		if (betType == BetType.moneyline) {
			
			dao.deleteParlayMoneylineBet(result);
			
		} else {
			
			dao.deleteSpreadPointsBet(result);
				
			
		}
		
	}

}
