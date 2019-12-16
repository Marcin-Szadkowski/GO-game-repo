package gogame.server.lobby;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import gogame.server.game.Game;
import gogame.server.game.Player;
import gogame.server.lobby.member.Data;

/**
 * Klasa odpowiadajaca za laczenie graczy w spolne gry
 * @author marcin
 * Gracze sa laczeni na podstawie zgodnosci wyboru rozmiaru planszy
 */
public class Lobby implements GamesHandler {
	private volatile static Lobby instance;
	private volatile LinkedList<Data> players= new LinkedList<Data>();

	
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
	public synchronized void  findGame(Data data) {
		System.out.println("Wywolano findGame()");
		//W tym momencie mozna sprawdzic czy gracz szuka MULTI czy SINGLE
		//Jak single to daj mu od razu bota, a jak MULTI to rob to co ponizej
		int gracze = players.size();
		int indeks = players.indexOf(data);
		
		for(int j= 0; j<gracze; j++) {
			if(j!= indeks) {
				if(comparePlayers(data, players.get(j)) == true ) {
					matchPlayers(data, players.get(j));
					return;	
				}	
			}
									
		}		
	}
	/**
	 * Metoda porownojaca graczy oczekujacych na gre. 
	 * @param data1 Pierwszy gracz
	 * @param data2 Drugi gracz
	 * @return Gdy gracze ustawili takie same preferencje to metoda zwraca true
	 */
	public boolean comparePlayers(Data data1, Data data2) {
		System.out.println("Wywolano comparePlayers()");
		if(data1.isReady() != true || data2.isReady() != true)
			return false;
		if(data1.getGameSize().intValue() != data2.getGameSize().intValue())
			return false;
		if( !data1.getGameType().equals("MULTI") || !data2.getGameType().equals("MULTI"))
			return false;
		return true;
	}
	
	/**
	 * Metoda przydzielajaca graczy do jednej gry
	 * @param player1 Pierwszy gracz
	 * @param player2 Drugi gracz
	 */
	public void matchPlayers(Data data1, Data data2) {
		System.out.println("Wywolano matchPlayers()");
		//Tworze nowa gre dla dopasowanych graczy
		Game game = new Game(data1.getGameSize());
		//Dodaje referencje do gry	
		data1.addGame(game);
		data2.addGame(game);
		
		//Tworzymy obiekty klasy Player
		Player player1 = new Player(game, data1, "black");
		Player player2 = new Player(game, data2, "white");
		//Przypisujemy graczy do gry, po to aby gra mogla sie do nich odnosic
		game.setup(player1, player2);
		
		data1.addPlayer(player1);
		data2.addPlayer(player2);
		//data1.player = player1;
		//data2.player = player2;
		
		//Usuwamy graczy z listy w lobby, poniewaz juz znalezli gre
		deletePlayer(data1);
		deletePlayer(data2);
	
	}
	public Integer howManyPlayers() {
		return players.size();
	}
	/**
	 * Metoda dodajaca gracza do lobby
	 * @param player
	 */
	public void addPlayer(Data data) {
		if(players != null)	
			players.add(data);
			System.out.println("Dodano gracza");
	}
	
	/**
	 * Metoda usuwajaca gracza z lobby
	 * @param player
	 * wywolywana w przypadku polaczenia graczy do gry
	 * lub w przypadku utracenia polaczenia przed dolaczeniem do gry
	 */
	public void deletePlayer(Data data) {
		if(players != null) {
			try {
				players.remove(data);
			}catch(NoSuchElementException e) {
				e.printStackTrace();
			}		
		}
	}
}
