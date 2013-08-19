package com.eyesbet.mobile.web.command;



import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.eyesbet.business.BetComputer;
import com.eyesbet.business.Service;
import com.eyesbet.business.domain.Bet;
import com.eyesbet.business.domain.BetType;
import com.eyesbet.business.domain.Bets;
import com.eyesbet.business.domain.DeleteBetResult;
import com.eyesbet.business.domain.Game;
import com.eyesbet.dao.BetDao;

public class EditBetCommand extends MobileCommand {
	private BetDao dao= new BetDao();
	private static Logger logger = Logger.getLogger(EditBetCommand.class);
	
	public EditBetCommand(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception {
		
		
		String cmd = request.getParameter("cmd");
		int betId = Integer.parseInt(request.getParameter("betId"));

		
		
		
		
		if ("deleteParlay".equals(cmd)) {
			logger.info("Deleting parlay bet....");
			int gameId = Integer.parseInt(request.getParameter("gameId"));
			String type = request.getParameter("type");
			//logger.debug("bet type: "+type);
			BetType betType = null;
			
			if ("ou".equals(type)) betType = BetType.overUnder;
			else if ("sp".equals(type)) betType = BetType.spreadPoint;
			else if ("mn".equals(type)) betType = BetType.moneyline;
			
			deleteParlayBet(betId,gameId, betType);
			
			refresh(betId);
	
		
			
		} else if ("changeName".equals(cmd)) {
			
			Service service = new Service();
			service.changeBetName(betId, request.getParameter("name"));
			
			Bets bets = (Bets)request.getSession().getAttribute("bets");
			
			Bet bet = bets.getBet(betId);
			
			bet.setName(request.getParameter("name"));
			
			
			
		}
		
		
	
		return "<success />";
		
		
		
	}
	
	
	private void refresh(int betId) throws Exception {
		
		BetDetailCommand detail = new BetDetailCommand(request);
				detail.removeSession();
				detail.setBetId(betId);
				detail.execute();
	}
	
	
	
	private void deleteParlayBet(int betId, int gameId, BetType betType) throws Exception {
		
		Bets bets = (Bets)request.getSession().getAttribute("bets");
		
		Bet bet = bets.getBet(betId);
		
		DeleteBetResult result = bet.deleteGamebet(gameId, betType);
					
		if (result.isTypeChanged()) {
			bet.setBetType(BetType.straightWages);
			bets.resetBet(bet);
		}
		
		
		if (betType == BetType.moneyline) {
			
			dao.deleteParlayMoneylineBet(result);
			
		} else {
			
			dao.deleteSpreadPointsBet(result);
				
			
		}
		
		Game game = bet.getGame(gameId);
		if (game != null) {
			BetComputer.computGameBet(game);
		}
		
		BetComputer.computeBetStatus(bet);
		
	}

}
