package gogame.server.game;

import gogame.server.transProtocol.Input;
import gogame.server.transProtocol.Output;

public class Player implements Runnable {
	String color;
	Game game;
	Integer gameSize;
	Player opponent;
	Output output;
	Input input;
	
	public Player(Output output, Input input) {
		this.output = output;
		this.input = input;
	}
	public void set(Game game, Player opponent, String color) {
		this.game = game;
		this.opponent = opponent;
		this.color = color;
	}
	Integer getGameSize() {
		if(gameSize != null)
			return gameSize;
		return null;
	}
	
	public void run() {
		//output.out("Gracz " + color);
		System.out.println("Gracz" + color);
	}
	
}
