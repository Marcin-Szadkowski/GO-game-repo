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
		System.out.println("Wywolanie metody set()");
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
		if(opponent != null){
			output.out("GAME_STARTED");
			processCommands();
			
		}				
		if(opponent != null && opponent.output != null) {
			//opponent.output.out("OTHER_PLAYER_LEFT");
		}
		//output.out("GAME_READY you are "+ color);						
		System.out.println("Gracz" + color);
		input.closeSocket();
	}
	private void processCommands(){
		while(input.hasNextLine()) {
			String line = input.nextLine();
			String[] commands = line.split(" ", 2);
			String command = commands[0];
			
			switch(command) {
			case "MOVE":
				//Wywolaj metode move z game
				break;
			case "PASS":
				//Przekaz wiadomosc do gry
				break;
			case "QUIT":
				return;
			}
			
		}
	}

	
}
