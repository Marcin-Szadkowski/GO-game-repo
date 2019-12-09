package gogame.server.lobby;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import gogame.server.game.Game;
import gogame.server.game.Player;

/**
 * Klasa odpowiadajaca za laczenie graczy w spolne gry
 * @author marcin
 * Gracze sa laczeni na podstawie zgodnosci wyboru rozmiaru planszy
 */
public class Lobby implements GamesHandler {
	private boolean execute = true;
	private volatile static Lobby instance;
	private volatile LinkedList<Player> players= new LinkedList<Player>(); 
	
	/**
	 * Statyczna funkcja zwracajaca instancje klasy Lobby,
	 *  poniewaz Lobby wykorzystuje wzorzec Singleton
	 * @return
	 */
	public static Lobby getInstance() {
		if(instance == null) {
			synchronized (Lobby.class) {
				if(instance == null) {
					instance = new Lobby();				
				}
			}
		}
		return instance;
	}
	public synchronized void  findGame() {
		int gracze = players.size();
		for(int i=0; i< gracze; i++) {
			for(int j= i+1; j<gracze; j++) {
				if(players.get(i).getGameSize() == players.get(j).getGameSize() && players.get(i).getGameSize() != null )
					matchPlayers(players.get(i), players.get(j));
			}
		}
	}
	
	/**
	 * Metoda przydzielajaca graczy do jednej gry
	 * @param player1
	 * @param player2
	 */
	public void matchPlayers(Player player1, Player player2) {
		System.out.println("Wywolanie matchPlayers");
		System.out.println("Rozmiar planszy: " + player1.getGameSize());
		Game game = new Game(player1.getGameSize());
		player1.set(game, player2, "Black");
		player2.set(game, player1, "White");
		this.deletePlayer(player1);
		this.deletePlayer(player2);
	}
	public Integer howManyPlayers() {
		return players.size();
	}
	/**
	 * Metoda konczaca dzialanie lobby
	 * Powinna byc wywolywana przed wylaczeniem serwera
	 */
	public void stop() {
		execute = false;
	}
	/**
	 * Metoda dodajaca gracza do lobby
	 * @param player
	 */
	public void addPlayer(Player player) {
		if(players != null)	
			players.add(player);
			System.out.println("Dodano gracza");								
	}
	
	/**
	 * Metoda usuwajaca gracza z lobby
	 * @param player
	 * wywolywana w przypadku polaczenia graczy do gry
	 * lub w przypadku utracenia polaczenia przed dolaczeniem do gry
	 */
	public void deletePlayer(Player player) {
		if(players != null) {
			synchronized (players) {
				try {
					players.remove(player);
				}catch(NoSuchElementException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
