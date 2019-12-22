package gogame.server.lobby.member;

import java.util.LinkedList;
import java.util.List;

import gogame.server.game.Player;
import gogame.server.game.Stone;
import gogame.server.lobby.Lobby;
import gogame.server.game.Stone;

/**
 * Klasa odpowiadajaca za parsowanie polecen przychodzacych od clienta oraz
 * za prasowanie poleceniem wychodzacych do Clienta
 * @author marcin
 *
 */
public class Parser {
	
	private Connector connector;
	private Data data;
	
	public Parser(Connector connector) {
		this.connector = connector;
		this.data = new Data(this);
	}
	public Parser() {};
	
	public Data getData() {
		return data;
	}
	/**
	 * Metoda odpowiadajac za interpretowanie polecen przychodzaych od Clienta
	 * @param command polecenie od Clienta przechwycone przec Connector
	 * @see Connector
	 */
	public void interpret(String command) {
		String[] args;
		if(command.startsWith("QUIT")) {
			if(data.getGame() != null)
				data.getPlayer().quit();
		}else if(command.startsWith("SIZE")) {
			args = command.split(" ");
			data.setGameSize(Integer.parseInt(args[1]));
		}else if(command.startsWith("TYPE")) {
			args = command.split(" ");
			data.setGameType(args[1]);
		}else if(command.startsWith("FIND_GAME")) {
			//No to wywolujemy metody z Lobby
			if(data.isReady())
				Lobby.getInstance().findGame(data);
		}else if(command.startsWith("PASS")) {
			if(data.getGame() != null)
				data.getPlayer().pass();
		}else if(command.startsWith("MOVE")) {
			if(data.getGame() != null) {
				args = command.split(" ");
				try {
					int x, y;
					x = Integer.parseInt(args[1]);
					y = Integer.parseInt(args[2]);
					data.getPlayer().move(x, y);
				}catch(NumberFormatException e) {
					//Nie rob nic
				}
			}
		}
	}
	/**
	 * Metoda wysylajaca do klienta informacje o wiezniach
	 * @param x
	 * @param y
	 */
	public void prisoners(int x, int y) {
		connector.sendMsg("PRISONERS "+ x + " "+ y);
	}
	/**
	 * Metoda wysylajaca informacje o ruchu przeciwnika
	 * @param x
	 * @param y
	 */
	public void opponentMoved(int x, int y) {
		connector.sendMsg("OPPONENT_MOVED "+ x + " " + y);
	}
	/**
	 * Metoda wysylajaca do clienta wiadomosc o niemoznosci wykonania ruchu
	 */
	public void notYourTurn() {
		connector.sendMsg("NOT_YOUR_TURN");
	}
	/**
	 * Metoda wysylajaca do clienta wiadomosc o wykonanym ruchu
	 * @param x
	 * @param y
	 */
	public void youMoved(int x, int y) {
		connector.sendMsg("YOU_MOVED "+ x + " "+ y);
	}	
	/**
	 * Metoda wysylajaca do klienta informacje o bitych kamieniach
	 * @param groups
	 */
	public void delete(LinkedList<Stone> stones) {
		//Tu przerobic bite kamienie na sygnal
		System.out.println("Wywoluje delete() z parsera");
		String msg = "DELETE";
		
		for(Stone stone: stones) {
				msg = msg +" "+ stone.x + " " + stone.y;
		}		
		connector.sendMsg(msg);
	}
	/**
	 * Metoda wysylajaca wiadomosc o zwyciestwie
	 * @param x punkty gracza	
	 * @param y punkty przeciwnika
	 */
	public void victory(int x, int y) {
		connector.sendMsg("VICTORY "+ x + " "+ y);
	}
	/**
	 * Metoda wysylajaca wiadomosc o przegranej
	 * @param x punkty gracza
	 * @param y punkty przeciwnika
	 */
	public void defeat(int x, int y) {
		connector.sendMsg("DEFEAT "+ x + " "+ y);
	}
	/**
	 * Metoda wysylajaca wiadomosc o remisie
	 * @param x punkty graczy
	 */
	public void tie(int x) {
		connector.sendMsg("TIE "+ x);
	}
	/**
	 * Metoda parsujaca funkcje gameStarted() na odpowiedni sygnal
	 * ktory zostanie wyslany do serwera
	 * @param player gracz, do ktorego ma zostal wyslany sygnal
	 */
	public void gameStarted(Player player) {	
		String color =  player.getColor();
		if(color != null)
			connector.sendMsg("GAME_STARTED " + color);																		
	}
}
