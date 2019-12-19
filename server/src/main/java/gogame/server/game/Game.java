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
	/**
	 * Metoda wykonujaca ruch na planszy
	 * @param x wspolrzedna x ruchu
	 * @param y wspolrzedna y ruchu
	 * @param player gracz wykonujacy ruch
	 */
	public synchronized void move(int x, int y, Player player) {
		if(player == currentPlayer) {
			player.youMoved(x, y);
			if(player == player1) {
				currentPlayer = player2;
				player2.opponentMoved(x, y);
			}else {
				currentPlayer = player1;
				player1.opponentMoved(x, y);
			}
		}else {
			player.notYourTurn();
		}
	}
	/**
	 * Metoda wysylajaca do odpowiedniego gracza wiadomsoc o ruchu przeciwnika
	 * @param player ten gracz ma dostac informacje o ruchu przeciwnika
	 * @param x
	 * @param y 
	 */
	public void opponentMoved(Player player, int x, int y) {
		player.opponentMoved(x, y);
	}

	/**
	 * Metoda wysylajaca do graczy informacje o rozpoczeciu gry
	 * Wywolywana w Lobby po dolaczeniu graczy do gry
	 */
	private void gameStarted() {
		player1.gameStarted();
		player2.gameStarted();
	}
	/**
	 * Metoda wysylajaca do gracza informacje o stanie wiezniow
	 * @param x
	 * @param y
	 */
	public void prisoners(int x, int y) {
		//Wyslij do gracza info o stanie wiezniow
	}
	/**
	 * Metoda informujaca gracza o poprawnie wykonanym ruchu
	 * @param x
	 * @param y
	 * @see Player#youMoved(int, int)
	 */
	public void youMoved(Player player, int x, int y) {
		player.youMoved(x, y);
	}	
	/**
	 * Gracz pomija swoj ruch
	 * @param player gracz pomijajacy ruch
	 */
	public void pass(Player player) {
		//Sprawdzic czy to wgl kolej tego gracza na spasowanie
		if(player == currentPlayer) {
			
		}
	}
	/**
	 * Metoda wysylajaca do przeciwnika wiadomosc o odejsciu gracza
	 * @param gracz, ktory opuszcza gre
	 */
	public void quit(Player player) {
		//Sprobuj wyslac do graczy QUIT
	}
	
}
