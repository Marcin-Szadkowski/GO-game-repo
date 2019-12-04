package gogame.server.game;

import gogame.server.transProtocol.Input;
import gogame.server.transProtocol.Output;

public class Player implements Runnable {
	String color;
	Player opponent;
	Output output;
	Input input;
	
	public Player(Output output, Input input, String color) {
		this.output = output;
		this.input = input;
		this.color = color;
	}
	private void set() {
		
	}
	
	public void run() {
		
		System.out.println("Gracz" + color);
	}
}
