package gogame.server.game;

import java.util.LinkedList;

public class Bot implements Playable {
	String color;
	Game game;
	
	public Bot(Game game, String color) {
		this.game = game;
		this.color = color;
	}
	public Game getGame() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void gameStarted() {
		// TODO Auto-generated method stub
		System.out.println("GameStarted() z bota "+ color);
	}

	public void opponentMoved(int x, int y) {
		//Jak przeciwnik wykonal ruch to teraz twoja kolej
		//wykonaj ruch
		
	}

	public void prisoners(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void notYourTurn() {
		// TODO Auto-generated method stub
		
	}

	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void youMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void delete(LinkedList<Stone> stones) {
		// TODO Auto-generated method stub
		
	}

	
	public void pass() {
		// TODO Auto-generated method stub
		
	}

	public void victory(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void defeat(int x, int y) {
		// TODO Auto-generated method stub
		
	}


	public void tie(int x) {
		// TODO Auto-generated method stub
		
	}


	public void quit() {
		// TODO Auto-generated method stub
		
	}

	public void otherPlayerLeft() {
		// TODO Auto-generated method stub
		
	}
}
