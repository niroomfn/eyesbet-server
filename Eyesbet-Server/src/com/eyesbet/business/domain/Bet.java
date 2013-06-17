package com.eyesbet.business.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Bet {
	private BetType betType;
	private List<Game> games = new ArrayList<Game>();
	private String createdDate;
	private String modifiedDate;
	private int id;
	private boolean parlayLost = false;
	private int status;
	private String xml;
	private String name;

	public Bet(BetType betType, int id) {
		this.betType = betType;
		this.id = id;
	}

	public BetType getBetType() {
		return this.betType;
	}

	public boolean isMoneyline() {
		for (Game game : this.games) {
			if (game.getBet().isMoneyline()) {
				return true;
			}

		}

		return false;
	}

	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	public boolean isParlay() {
		if (this.betType == BetType.parlay) {
			return true;
		}
		return false;
	}

	public void addGame(Game game) {
		game.setBetId(this.id);
		this.games.add(game);
	}

	public List<Game> getGames() {
		return this.games;
	}

	public String getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getId() {
		return this.id;
	}

	public boolean equals(Object obj) {
		if ((obj == null) || (!(obj instanceof Bet))) {
			return false;
		}

		Bet bet = (Bet) obj;
		if (bet.getId() == getId()) {
			return true;
		}

		return false;
	}

	public boolean hasLiveGame() {
		for (Game game : this.games) {
			if (game.isLive()) {
				return true;
			}
		}
		return false;
	}

	public Set<Game> getLiveGames() {
		Set<Game> set = new HashSet<Game>();
		for (Game game : this.games) {
			if ((game.isLive()) || (game.getBet().getId() == 193)
					|| (game.getBet().getId() == 190)
					|| (game.getBet().getId() == 195)
					|| (game.getBet().getId() == 206)) {
				set.add(game);
			}

		}

		return set;
	}

	public Game getGame(String home, String away) {
		for (Game game : this.games) {
			if ((home.equals(game.getHome().getName()))
					&& (away.equals(game.getAway().getName()))) {
				return game;
			}

		}

		return null;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Game> getFinishedGames() {
		List<Game> list = new ArrayList<Game>();
		for (Game g : this.games) {
			if (g.isFinished()) {
				list.add(g);
			}
		}

		return list;
	}

	public List<Game> getNonLiveGames() {
		List<Game> list = new ArrayList<Game>();
		for (Game g : this.games) {
			if (!g.isLive()) {
				list.add(g);
			}
		}

		return list;
	}

	public String getStatusText() {
		if (this.parlayLost) {
			return "LOST";
		}
		List<Game> list = getGames();
		for (Game game : list) {
			if ((game.isLive()) || (game.notStarted())) {
				return "Pending...";
			}

		}

		if (this.status == 100) {
			return "WON";
		}
		if ((this.status < 100) && (this.status != 1))
			return "LOST";
		if (this.status == 1) {
			return "Tight Game";
		}
		return "Unkown";
	}

	public boolean isParlayLost() {
		return this.parlayLost;
	}

	public void setParlayLost(boolean parlayLost) {
		this.parlayLost = parlayLost;
	}

	public String getXml() {
		return this.xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasNotStartedGame() {
		for (Game game : this.games) {
			if (game.notStarted()) {
				return true;
			}

		}

		return false;
	}

	/**
	 * 
	 * @param gameId
	 * @return typeChanged
	 */
	public DeleteBetResult deleteGamebet(int gameId, BetType type) {

		DeleteBetResult result = new DeleteBetResult();
		Iterator<Game> itr = games.iterator();
		Game game = null;
		while (itr.hasNext()) {

			game = itr.next();
			result.setGame(game);
			if (game.getGameId() == gameId) {

				GameBet gamebet = game.getBet();

				if (type == BetType.moneyline) {
					result.setBetType(type);
					itr.remove();

					if (games.size() == 1) {
						result.setTypeChanged(true);

					}

					return result;

				} else {

					if (type == BetType.overUnder) {
						result.setBetType(type);
						gamebet.deleteOverUnder();

					} else if (type == BetType.spreadPoint) {
						result.setBetType(type);
						gamebet.deleteSpreadPoint();
					}

					if (gamebet.deleted()) {
						itr.remove();
						if (games.size() == 1) {

							gamebet = games.get(0).getBet();
							if (gamebet.isOverUnder() == false|| gamebet.isSpreadPoint() == false) {
								result.setTypeChanged(true);

							}
						}

						break;
					} else {

						if (games.size() == 1) {
							gamebet = games.get(0).getBet();

							if (gamebet.isOverUnder() == false|| gamebet.isSpreadPoint() == false) {
								result.setTypeChanged(true);

							}

						}

						break;
					}

				}
			}

		}

		return result;

	}

}
