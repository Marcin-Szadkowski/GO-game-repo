package gogame.server.game;

import java.util.LinkedList;
import java.util.List;

public class Bot implements Playable {
	String color;
	Game game;
	
	public Bot(Game game, String color) {
		this.game = game;
		this.color = color;
	}
	@Override
	public Game getGame() {
		return game;
	}

	@Override
	public String getColor() {
		return color;
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
		this.move(x, y);
		
	}

	@Override
	public void prisoners(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notYourTurn() {
		System.out.println("NotYourTurn() z bota");
		
	}
	
	/**
	 * Metoda wykonujaca ruch
	 * Znajdz wszystkie grupy przeciwnika. Nastepnie wybierz te ktora ma najmniej oddechow.
	 * Sprobuj przy niej postawic kamien. Jesli sie nie udalo to wybierz jakas inna grupe
	 */
	@Override
	public void move(int x, int y) {
		System.out.println("Wywolanie move() z bota");
		int[] move;
		List<LinkedList<Stone>> groups = BotMethods.findGroups(game);
		LinkedList<Stone> weakGroup = BotMethods.findWeakGroup(groups, game);
		move = BotMethods.choosePlace(weakGroup, game);
		if(move != null) {
			game.move(move[0], move[1], this);
		}else {
			for(LinkedList<Stone> group: groups) {
				move = BotMethods.choosePlace(group, game);
				if(move == null)
					continue;
				game.move(move[0], move[1], this);
				System.out.println("Bot wyslal ruch do gry");
				return;
			}
		}
		
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
