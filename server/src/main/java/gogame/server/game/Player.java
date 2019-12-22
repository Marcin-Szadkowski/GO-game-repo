package gogame.server.game;

import java.util.LinkedList;
import java.util.List;

import gogame.server.lobby.member.*;

/**
 * Klasa będąca reprezantacja Clienta po stronie gry
 * @author marcin
 *
 */
public class Player implements Playable {
	//Kolor gracza
	String color;
	
	Game game;
	Data data;
	
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
	 * Metoda wysylajaca info o wiezniach
	 * @param x
	 * @param y
	 */
	public void prisoners(int x, int y) {
		data.getParser().prisoners(x, y);
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
	@Override
	public void delete(LinkedList<Stone> stones) {
		data.getParser().delete(stones);
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
	 * @param x liczba zdobytych punktow
	 * @param y liczba punktow przeciwnika
	 */
	public void victory(int x, int y) {
		data.getParser().victory(x, y);
	}
	/**
	 * Metoda wywolywana, gdy gra zakonczy sie porazka gracza
	 * @param x liczba zdobytych punktow
	 * @param y liczba punktow przeciwnika
	 */
	public void defeat(int x, int y) {
		data.getParser().defeat(x, y);
	}
	public void tie(int x) {
		data.getParser().tie(x);
	}
	/**
	 * Metoda konczaca gre dla gracza
	 * Wywolywana kiedy od danego Clienta przyjdzie sygnal o zakonczeniu gry
	 */
	public void quit() {
		game.quit(this);
	}

}