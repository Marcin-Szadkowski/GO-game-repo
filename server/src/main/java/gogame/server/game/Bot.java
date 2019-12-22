package gogame.server.game;

import java.util.LinkedList;

public class Bot implements Playable {
	String color;
	Game game;
	
	public Bot(Game game, String color) {
		this.game = game;
		this.color = color;
	}
	@Override
	public Game getGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void gameStarted() {
		// TODO Auto-generated method stub
		System.out.println("GameStarted() z bota "+ color);
	}

	@Override
	public void opponentMoved(int x, int y) {
		//Jak przeciwnik wykonal ruch to teraz twoja kolej
		//wykonaj ruch
		
	}

	@Override
	public void prisoners(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notYourTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void youMoved(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(LinkedList<Stone> stones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pass() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void victory(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defeat(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tie(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void otherPlayerLeft() {
		// TODO Auto-generated method stub
		
	}
}
