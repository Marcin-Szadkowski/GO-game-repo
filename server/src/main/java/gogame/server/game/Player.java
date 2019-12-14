package gogame.server.game;

import gogame.server.lobby.member.Data;

public class Player {
	String color;
	Game game;
	Data data;
	
	public Player(Game game, Data data, String color) {
		this.game = game;
		this.color = color;
		this.data = data;
	}


	
	public Game getGame() {
		if(game != null)
			return game;
		return null;
	}
	public String getColor() {
		return color;
	}
	public void gameStarted() {
		data.getParser().gameStarted();
	}	
}
