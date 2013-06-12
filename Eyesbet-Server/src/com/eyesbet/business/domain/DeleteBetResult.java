package com.eyesbet.business.domain;

public class DeleteBetResult {
	
	private boolean typeChanged = false ;
	private Game game;
	private BetType betType;

	public boolean isTypeChanged() {
		return typeChanged;
	}

	public void setTypeChanged(boolean typeChanged) {
		this.typeChanged = typeChanged;
	}

	public BetType getBetType() {
		return betType;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	public boolean gameDeleted() {
		
		return game.getBet().deleted();
		
	}
	
	

}
