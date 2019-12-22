package gogame.server.lobby.member;

import gogame.server.game.Game;
import gogame.server.game.Player;
import gogame.server.lobby.Lobby;

public class Data {
	protected Game game = null;
	public Player player = null;
	private Parser parser = null;
	private Integer gameSize = null;
	private String gameType = null;
	
	public Data(Parser parser) {
		this.parser = parser;
		Lobby.getInstance().addPlayer(this);
	}
	public Parser getParser() {
		//f(parser != null)
			return parser;
		//return null;
	}
	public Player getPlayer() {
		//if(player != null)
			return player;
		//return null;
	}
	public Game getGame() {
		return game;
	}
	public void setGameSize(Integer size) {
		this.gameSize = size;
	}
	public void setGameType(String type) {
		this.gameType = type;
	}
	public boolean isReady() {
		if(gameSize != null &&  gameType != null)
			return true;
		return false;
	}
	public Integer getGameSize() {
		if(gameSize != null)
			return gameSize;
		return null;
	}
	public String getGameType() {
		if(gameType != null)
			return gameType;
		return null;
	}
	public void addGame(Game game) {
		this.game = game;
	}
	public void addPlayer(Player player) {
			this.player = player;
	}
}
