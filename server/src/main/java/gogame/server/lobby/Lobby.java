package gogame.server.lobby;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import gogame.server.factories.PlayerFactory;
import gogame.server.game.Game;
import gogame.server.game.Player;

/**
 * Klasa odpowiadajaca za laczenie graczy w spolne gry
 * @author marcin
 * Gracze sa laczeni na podstawie zgodnosci wyboru rozmiaru planszy
 */
public class Lobby implements GamesHandler {
	private volatile static Lobby instance;
	private volatile LinkedList<PlayerInLobby> players= new LinkedList<PlayerInLobby>();
	static ExecutorService pool;
	
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
					pool = Executors.newFixedThreadPool(20);
				}
			}
		}
		return instance;
	}
	@Override
	public synchronized void  findGame() {
		int gracze = players.size();
		for(int i=0; i< gracze; i++) {
			for(int j= i+1; j<gracze; j++) {
				if(comparePlayers(players.get(i), players.get(j)) == true ) {
					matchPlayers(players.get(i), players.get(j));
					return;	
				}							
			}
		}
	}
	private boolean comparePlayers(PlayerInLobby player1, PlayerInLobby player2) {
		if(player1.isReady() != true || player2.isReady() != true)
			return false;
		if(player1.state != "WAITING" || player2.state !="WAITING")
			return false;
		if(player1.gameSize != player2.gameSize)
			return false;
		if(player1.gameType != "MULTI" || player2.gameType != "MULTI")
			return false;
		return true;
	}
	
	/**
	 * Metoda przydzielajaca graczy do jednej gry
	 * @param player1
	 * @param player2
	 */
	@Override
	public void matchPlayers(PlayerInLobby player1, PlayerInLobby player2) {
		System.out.println("Wywolanie matchPlayers");
		//Tworze nowa gre dla dopasowanych graczy
		Game game = new Game(player1.getGameSize());
		//Tu trzeba stowrzyc graczy na podstawie instancji PlayerInLobby
		Player newPlayer1 = PlayerFactory.makePlayer(player1);
		Player newPlayer2 = PlayerFactory.makePlayer(player2);
		newPlayer1.set(game, newPlayer2, "Black");
		newPlayer2.set(game, newPlayer1, "White");
		player1.state = "STARTED";
		player2.state = "STARTED";
		this.deletePlayer(player1);
		this.deletePlayer(player2);
		pool.execute(newPlayer1);
		pool.execute(newPlayer2);
		
	}
	@Override
	public Integer howManyPlayers() {
		return players.size();
	}
	/**
	 * Metoda dodajaca gracza do lobby
	 * @param player
	 */
	public void addPlayer(PlayerInLobby player) {
		if(players != null)	
			players.add(player);
			System.out.println("Dodano gracza");
			new Thread(player).start();
	}
	
	/**
	 * Metoda usuwajaca gracza z lobby
	 * @param player
	 * wywolywana w przypadku polaczenia graczy do gry
	 * lub w przypadku utracenia polaczenia przed dolaczeniem do gry
	 */
	public void deletePlayer(PlayerInLobby player) {
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
	public synchronized void closeLobby() {
		pool.shutdown(); 
		for(PlayerInLobby p: players) {
			p.state = "QUIT";
		}
		try {
			if(!pool.awaitTermination(10, TimeUnit.SECONDS))
				pool.shutdownNow();
		}catch(InterruptedException e) {
			pool.shutdownNow();
			//Thread.currentThread().interrupt();
		}
	}


}
