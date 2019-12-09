package gogame.server.game;

public class Game {
	private int size;
	private Player currentPlayer;
	
	public Game(Integer size) {
		this.size = size;
	}
	public void setCurPlayer(Player player) {
		currentPlayer = player;
	}
	
}
