package gogame.server.game;

import gogame.server.lobby.Lobby;
import gogame.server.transProtocol.Input;
import gogame.server.transProtocol.Output;

public class Player implements Runnable {
	String color;
	Game game;
	public volatile Integer gameSize;
	Player opponent;
	Output output;
	Input input;
	
	public Player(Output output, Input input) {
		this.output = output;
		this.input = input;
	}
	public void set(Game game, Player opponent, String color) {
		//System.out.println("Wywolanie metody set()");
		this.game = game;
		this.opponent = opponent;
		this.color = color;
	}
	public Integer getGameSize() {
		if(gameSize != null)
			return gameSize;
		return null;
	}
	public Game getGame() {
		if(game != null)
			return game;
		return null;
	}
	public void run() {
		//output.out("Gracz " + color);
		setSize();
			//Tu dodac sprawdzanie czy gracz podal rozmiar planszy
		//output.out("GAME_READY you are "+ color);						
		System.out.println("Gracz" + color);
		input.closeSocket();
	}
	private void setSize() {
		while(opponent == null) {
			if(input.hasNextLine()) {
				//Tu mozna dodac wypisywanie do swojego Clienta "podaj rozmiar planszy" czy cos takiego
				String command = input.nextLine();
				if(command.startsWith("QUIT")) {
					return;//Opuszczenie metody
				}else if(command.startsWith("SIZE")) {
					this.gameSize = Integer.parseInt(command.substring(5));
					//Jezeli zostanie znaleziona gra o podanym rozmiarze planszy to Lobby przydzieli graczowi
					// przeciwnika i watek opusci metode setSize()
					Lobby.getInstance().findGame();
				}
			}
		}
	}
	
}
