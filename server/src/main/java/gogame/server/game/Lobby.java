package gogame.server.game;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import gogame.server.transProtocol.TCP.TcpServer;
/**
 * Klasa odpowiadajaca za laczenie graczy w spolne gry
 * @author marcin
 * Gracze sa laczeni na podstawie zgodnosci wyboru rozmiaru planszy
 */
public class Lobby implements Runnable {
	private boolean execute = true;
	private volatile static Lobby instance;
	private LinkedList<Player> players;
	
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
	@Override
	public void run() {
		this.players = new LinkedList<Player>();
		
		while(execute) {
			
		}
		
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
	}
	/**
	 * Metoda usuwajaca gracza z lobby
	 * @param player
	 * wywolywana w przypadku polaczenia graczy do gry
	 * lub w przypadku utracenia polaczenia przed dolaczeniem do gry
	 */
	public void deletePlayer(Player player) {
		if(players != null) {
			try {
				players.remove(player);
			}catch(NoSuchElementException e) {
				e.printStackTrace();
			}
		}
	}

}
