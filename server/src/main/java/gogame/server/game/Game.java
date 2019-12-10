package gogame.server.game;

public class Game {
	private int size;
	private Player currentPlayer;
	
	public Game(int size) {
		this.size = size;
	}
	public void setCurPlayer(Player player) {
		currentPlayer = player;
	}
	public synchronized void move(int x, int y, Player player) {
		
	}
	
}
