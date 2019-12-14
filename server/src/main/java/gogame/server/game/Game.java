package gogame.server.game;

public class Game {
	private int size;
	private Player player1, player2;
	private Player currentPlayer;
	
	public Game(int size) {
		this.size = size;
	}
	/**
	 * Metoda przypisujaca graczy do powstalej gry
	 * Ponadto metoda ustawia pierwszego gracza jako gracza, ktory zaczyna gre
	 * @param player1
	 * @param player2
	 */
	public void setup(Player player1, Player player2) {
		currentPlayer = player1;
		this.player1 = player1;
		this.player2 = player2;
		gameStarted();
		
	}
	public synchronized void move(int x, int y, Player player) {
		
	}
	private void gameStarted() {
		player1.gameStarted();
		player2.gameStarted();
	}
	
}
