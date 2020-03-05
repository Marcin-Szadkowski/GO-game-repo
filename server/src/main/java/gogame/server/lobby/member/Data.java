package gogame.server.lobby.member;

import gogame.server.game.Game;
import gogame.server.game.Player;
import gogame.server.lobby.Lobby;

/**
 * Klasa zawierajaca dane polaczonego clienta.
 * W pewnym sensie jest reprezentacja gracza przebywajacego w lobby.
 * Kiedy client wyszykuje gry dane sa pobierane z tej klasy.
 * @author marcin
 *
 */
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
	/**
	 * Pobierz instancje parsera
	 * @return @see Parser
	 */
	public Parser getParser() {
		//f(parser != null)
			return parser;
		//return null;
	}
	/**
	 * Pobierz instancje gracza
	 * @return @see Player
	 */
	public Player getPlayer() {
		//if(player != null)
			return player;
		//return null;
	}
	/**
	 * Pobierz instancje gry
	 * @return @see Game
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * Ustaw rozmiar gry
	 * @param size
	 */
	public void setGameSize(Integer size) {
		this.gameSize = size;
	}
	/**
	 * Ustaw rozmiar gry
	 * @param type
	 */
	public void setGameType(String type) {
		this.gameType = type;
	}
	/**
	 * Metoda sprawdzajaca czy gracz jest gotowy do gry
	 * @return true kiedy client ustawil rozmiar oraz typ szykanej gry
	 */
	public boolean isReady() {
		if(gameSize != null &&  gameType != null)
			return true;
		return false;
	}
	/**
	 * Pobierz rozmiar gry
	 * @return
	 */
	public Integer getGameSize() {
		if(gameSize != null)
			return gameSize;
		return null;
	}
	/**
	 * Pobierz typ gry
	 * @return
	 */
	public String getGameType() {
		if(gameType != null)
			return gameType;
		return null;
	}
	/**
	 * Dodaj do danych referencje gry
	 * @param game
	 */
	public void addGame(Game game) {
		this.game = game;
	}
	/**
	 * Dodaj do danych referencje do gracza
	 * @param player
	 */
	public void addPlayer(Player player) {
			this.player = player;
	}
}
