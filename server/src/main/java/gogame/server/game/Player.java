package gogame.server.game;

import gogame.server.lobby.member.*;

/**
 * Klasa będąca reprezantacja Clienta po stronie gry
 * @author marcin
 *
 */
public class Player {
	//Kolor gracza
	String color;
	
	Game game;
	Data data;
	int prisoners;
	
	public Player(Game game, Data data, String color) {
		this.game = game;
		this.color = color;
		this.data = data;
	}

	public Game getGame() {
			return game;
	}
	public String getColor() {
		return color;
	}
	/**
	 * Metoda wysylajaca informacje do Parsera o rozpoczeciu gry
	 * @see Parser#gameStarted(Player)
	 */
	public void gameStarted() {	
		data.getParser().gameStarted(this);
		//System.out.println("game started z Player");
	}
	/**
	 * Metoda wysylajaca do parsera informacje o ruchu przeciwnika
	 * @param x
	 * @param y
	 */
	public void opponentMoved(int x, int y) {
		data.getParser().opponentMoved(x, y);
	}
	/**
	 * Metoda przesylajaca do clienta informacje o niemożności wykonania ruchu
	 * Wywolywana w Game, gdy gracz probuje wykonac ruch podczas nieswojej kolejki
	 */
	public void notYourTurn() {
		data.getParser().notYourTurn();
	}
	/**
	 * Metoda wysylajaca do Game informacje o checi wykonania ruchu 
	 * @param x wspolrzedna x ruchu
	 * @param y wspolrzedna y ruchu
	 * @see Game#move(int, int, Player)
	 */
	public void move(int x, int y) {
		game.move(x, y, this);
	}
	/**
	 * Metoda wysylajaca do Clienta wiadomosc o poprawnie wykonanym ruchu
	 * @param x
	 * @param y
	 */
	public void youMoved(int x, int y) {
		data.getParser().youMoved(x, y);
	}
	/**
	 * Metoda wykonujaca ominiecie kolejki danego gracza
	 * Wywolywana przez sygnal od Clienta w @see Parser#interpret(String)
	 */
	public void pass() {
		game.pass(this);
	}
	/**
	 * Metoda wywolywana, gdy gra sie zakonczy zwyciestwem gracza
	 */
	public void victory() {
		//Tu wyslac info do clienta o zwyciestwie
	}
	/**
	 * Metoda wywolywana, gdy gra zakonczy sie porazka gracza
	 */
	public void defeat() {
		
	}
	/**
	 * Metoda konczaca gre dla gracza
	 * Wywolywana kiedy od danego Clienta przyjdzie sygnal o zakonczeniu gry
	 */
	public void quit() {
		game.quit(this);
	}
}