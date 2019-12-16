package gogame.server.lobby.member;

import gogame.server.game.Player;
import gogame.server.lobby.Lobby;

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
			//Sproboj wyslac do przeciwnika OTHER_PLAYER_LEFT
			//Pamietac aby sprawdzic czy wgl gra sie rozpoczela
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
			//Wyslac pass do Player -> gra
			//Pamietac o wylaczeniu tej funkcji zanim gra sie rozpoczela
		}else if(command.startsWith("MOVE")) {
			//Tu wywolac Player.move(int x, int y)
			//Pamietac o wylaczeniu tej funkcji zanim gra sie rozpoczela
			System.out.println("MOVE "+ data.getPlayer().getColor());
		}
	}
	public void gameStarted(Player player) {	
		System.out.println("Wowoluje gameStarted() z Parsera");
	
				//System.out.println("getPlayer() != null");
				String color =  player.getColor();
				if(color != null){
					System.out.println("Wyslano game started z parsera");
					connector.sendMsg("GAME_STARTED " + color);
				}	
																
					
	}
}
