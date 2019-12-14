package gogame.server.lobby.member;

import gogame.server.game.Game;
import gogame.server.game.Player;
import gogame.server.lobby.Lobby;

public class Data {
	protected Game game;
	protected Player player;
	private Parser parser;
	private Integer gameSize;
	private String gameType;
	protected String state; //To mozna zamienic na enum w przyszlosci
	
	public Data(Parser parser) {
		this.parser = parser;
		this.state = "waiting";
		Lobby.getInstance().addPlayer(this);
	}
	public Parser getParser() {
		if(parser != null)
			return parser;
		return null;
	}
	protected void setGameSize(Integer size) {
		this.gameSize = size;
	}
	protected void setGameType(String type) {
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
		if(game == null)
			this.game = game;
	}
	public void addPlayer(Player player) {
		if(player == null)
			this.player = player;
	}
}
